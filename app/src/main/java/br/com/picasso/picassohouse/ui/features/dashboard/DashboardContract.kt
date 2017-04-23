package br.com.picasso.picassohouse.ui.features.lights

import br.com.picasso.picassohouse.models.Room
import br.com.picasso.picassohouse.ui.base.BasePresenter
import br.com.picasso.picassohouse.ui.base.BaseView

interface DashboardContract {

    interface View : BaseView {
        fun showButtonLockHouse()
        fun showButtonUnlockHouse()
    }

    interface Presenter : BasePresenter<View> {
        fun setHomeIsLocked(isLocked: Boolean)
    }

}