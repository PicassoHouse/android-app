package br.com.picasso.picassohouse.ui.features.lights

import br.com.picasso.picassohouse.models.Room
import br.com.picasso.picassohouse.networking.PicassoHouseAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LightsPresenter(val apiService : PicassoHouseAPI?) : LightsContract.Presenter {

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
        apiService!!.getRooms()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { rooms ->
                    view?.showRooms(rooms)
                }
    }

    // --------------------------------------------------------
    // LightsContract.Presenter methods
    // --------------------------------------------------------

    override fun lightDidChangeStatus(position: Int, toStatus: Boolean) {
        //TODO: call API and update light status
        print("" + position + toStatus)
    }

}
