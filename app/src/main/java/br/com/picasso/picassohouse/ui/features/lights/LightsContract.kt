package br.com.picasso.picassohouse.ui.features.lights

import br.com.picasso.picassohouse.models.Room
import br.com.picasso.picassohouse.ui.base.BasePresenter
import br.com.picasso.picassohouse.ui.base.BaseView

interface LightsContract {

    interface View : BaseView {
        fun showRooms(items: List<Room>)
    }

    interface Presenter : BasePresenter<View> {
        fun lightDidChangeStatus(position: Int, toStatus : Boolean)
    }

}