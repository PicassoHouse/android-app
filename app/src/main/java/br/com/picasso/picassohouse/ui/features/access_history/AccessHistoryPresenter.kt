package br.com.picasso.picassohouse.ui.features.lights

import br.com.picasso.picassohouse.models.AccessHistory
import br.com.picasso.picassohouse.models.Room
import br.com.picasso.picassohouse.models.RoomType
import br.com.picasso.picassohouse.networking.PicassoHouseAPI
import br.com.picasso.picassohouse.ui.features.access_history.AccessHistoryContract
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class AccessHistoryPresenter(val apiService : PicassoHouseAPI) : AccessHistoryContract.Presenter {

    var view : AccessHistoryContract.View? = null

    private var history : List<AccessHistory> = emptyList()

    override fun start() {
        loadHistory()
    }

    override fun attachView(view: AccessHistoryContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    private fun loadHistory() {
        apiService.getAccessHistory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    history = it
                    view?.showAccessHistory(history)
                }, {
                    print(it)
                })
    }

    // --------------------------------------------------------
    // AccessHistoryContract.Presenter methods
    // --------------------------------------------------------

}
