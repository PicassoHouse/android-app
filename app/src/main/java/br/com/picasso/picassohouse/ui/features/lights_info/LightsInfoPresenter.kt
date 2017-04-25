package br.com.picasso.picassohouse.ui.features.lights_info

import br.com.picasso.picassohouse.models.Room
import br.com.picasso.picassohouse.networking.PicassoHouseAPI
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class LightsInfoPresenter(val apiService : PicassoHouseAPI) : LightsInfoContract.Presenter {

    var view : LightsInfoContract.View? = null

    lateinit var rooms : List<Room>

    override fun start() {
        //load current month light info
        apiService.getCurrentMonthLightInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view?.showCurrentMonthInfo(it) }, ::print)

        //load light info history
        apiService.getLightHistory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view?.showChart(it) }, ::print)
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
