package br.com.picasso.picassohouse.ui.features.lights

import br.com.picasso.picassohouse.models.Room
import br.com.picasso.picassohouse.models.RoomType
import br.com.picasso.picassohouse.networking.PicassoHouseAPI
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class LightsPresenter(val apiService : PicassoHouseAPI) : LightsContract.Presenter {

    var view : LightsContract.View? = null

    lateinit var rooms : List<Room>

    override fun start() {
        loadRooms()
    }

    override fun attachView(view: LightsContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    private fun loadRooms() {
        apiService.getRooms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ rooms ->
                    this.rooms = rooms
                    view?.showRooms(rooms)
                }, ::print )
    }

    // --------------------------------------------------------
    // LightsContract.Presenter methods
    // --------------------------------------------------------

    override fun lightDidChangeStatus(position: Int, toStatus: Boolean) {
        rooms[position].isLightOn = toStatus

        apiService.turnLightOn(toStatus, rooms[position].room_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

}
