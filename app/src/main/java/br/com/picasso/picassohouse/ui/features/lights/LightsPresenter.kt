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
        //TODO: save rooms locally

        rooms = mutableListOf(
                Room("3", "Sala de Estar", RoomType.livingRoom, false),
                Room("1", "Quarto", RoomType.bedRoom, true),
                Room("2", "Quarto 2", RoomType.bedRoom, false),
                Room("4", "Cozinha", RoomType.kitchen, true),
                Room("5", "Garagem", RoomType.garage, false))
        view?.showRooms(rooms)

//        apiService.getRooms()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe { rooms -> view?.showRooms(rooms) }
    }

    // --------------------------------------------------------
    // LightsContract.Presenter methods
    // --------------------------------------------------------

    override fun lightDidChangeStatus(position: Int, toStatus: Boolean) {
        val r = rooms[position]
        val room = Room(r.id, r.title, r.type, toStatus)

//        apiService.updateRoom(room)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe()
    }

}
