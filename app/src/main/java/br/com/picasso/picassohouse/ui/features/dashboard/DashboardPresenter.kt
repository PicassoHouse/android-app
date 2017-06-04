package br.com.picasso.picassohouse.ui.features.dashboard

import android.util.Log
import br.com.picasso.picassohouse.networking.PicassoHouseAPI
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class DashboardPresenter(val apiService : PicassoHouseAPI) : DashboardContract.Presenter {

    var view : DashboardContract.View? = null

    private var isHomeLocked : Boolean = false

    override fun start() {
        apiService.getHouseInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ house ->
                    isHomeLocked = house.isLocked
                    if(isHomeLocked)
                        view?.showButtonUnlockHouse()
                    else
                        view?.showButtonLockHouse()
                }, ::print )

    }

    override fun attachView(view: DashboardContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    // --------------------------------------------------------
    // UserDetailContract.Presenter methods
    // --------------------------------------------------------

    override fun setHomeIsLocked(isLocked: Boolean) {
        isHomeLocked = isLocked

        apiService.setHomeLocked(isLocked)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(isLocked){
                        view?.showButtonUnlockHouse()
                    } else {
                        view?.showButtonLockHouse()
                    }
                }, ::print)
    }

    override fun setGarageClosed(isClosed: Boolean) {
        apiService.setGarageOpened(!isClosed)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
    }

}
