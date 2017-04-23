package br.com.picasso.picassohouse.ui.features.lights

import br.com.picasso.picassohouse.models.Room
import br.com.picasso.picassohouse.models.RoomType
import br.com.picasso.picassohouse.networking.PicassoHouseAPI
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class DashboardPresenter(val apiService : PicassoHouseAPI) : DashboardContract.Presenter {

    var view : DashboardContract.View? = null

    private var isHomeLocked : Boolean = false

    override fun start() {
        //TODO: call API and get house status
        view?.showButtonLockHouse()
    }

    override fun attachView(view: DashboardContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    // --------------------------------------------------------
    // DashboardContract.Presenter methods
    // --------------------------------------------------------

    override fun setHomeIsLocked(isLocked: Boolean) {
        isHomeLocked = isLocked

//        apiService.setHomeLocked(isLocked)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe {
//                    if(isLocked){
//                        view?.showButtonUnlockHouse()
//                    } else {
//                        view?.showButtonLockHouse()
//                    }
//                }
        //TODO: remove this lines of code, just mocks
        if(isLocked){
            view?.showButtonUnlockHouse()
        } else {
            view?.showButtonLockHouse()
        }
    }

}
