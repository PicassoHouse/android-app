package br.com.picasso.picassohouse.ui.features.lights_info

import br.com.picasso.picassohouse.models.Room
import br.com.picasso.picassohouse.networking.PicassoHouseAPI


class LightsInfoPresenter(val apiService : PicassoHouseAPI?) : LightsInfoContract.Presenter {

    var view : LightsInfoContract.View? = null

    lateinit var rooms : List<Room>

    override fun start() {
    }

    override fun attachView(view: LightsInfoContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    // --------------------------------------------------------
    // LightsContract.Presenter methods
    // --------------------------------------------------------


}
