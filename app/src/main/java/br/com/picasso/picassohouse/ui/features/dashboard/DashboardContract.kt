package br.com.picasso.picassohouse.ui.features.dashboard

import br.com.picasso.picassohouse.ui.base.BasePresenter
import br.com.picasso.picassohouse.ui.base.BaseView

interface DashboardContract {

    interface View : BaseView {
        fun showButtonLockHouse()
        fun showButtonUnlockHouse()
    }

    interface Presenter : BasePresenter<View> {
        fun setHomeIsLocked(isLocked: Boolean)
        fun setGarageClosed(isClosed: Boolean)
    }

}