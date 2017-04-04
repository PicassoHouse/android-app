package br.com.picasso.picassohouse.ui.features.lights

import br.com.picasso.picassohouse.models.Room
import br.com.picasso.picassohouse.models.RoomType
import java.util.*


class LightsPresenter : LightsContract.Presenter {

    var view : LightsContract.View? = null

    lateinit var rooms : List<Room>

    override fun start() {
        //TODO: load rooms from API
        rooms = Arrays.asList(Room("123", "Quarto", RoomType.bedRoom, true),
                Room("123", "Cozinha", RoomType.kitchen, false),
                Room("123", "Sala de Estar", RoomType.livingRoom, false),
                Room("123", "Garagem", RoomType.garage, true))

        view?.showRooms(rooms)
    }

    override fun attachView(view: LightsContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    // --------------------------------------------------------
    // LightsContract.Presenter methods
    // --------------------------------------------------------

    override fun lightDidChangeStatus(position: Int, toStatus: Boolean) {
        //TODO: call API and update light status
        print("" + position + toStatus)
    }

}