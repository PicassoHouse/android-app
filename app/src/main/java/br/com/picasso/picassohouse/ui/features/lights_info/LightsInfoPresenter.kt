package br.com.picasso.picassohouse.ui.features.lights_info

import br.com.picasso.picassohouse.models.LightHistory
import br.com.picasso.picassohouse.networking.PicassoHouseAPI
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class LightsInfoPresenter(val apiService : PicassoHouseAPI) : LightsInfoContract.Presenter {

    var view : LightsInfoContract.View? = null

    private var history : List<LightHistory> = emptyList()

    override fun start() {
        apiService.getLightHistory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    history = it
                    view?.showAccessHistory(history)
                }, ::print )
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
