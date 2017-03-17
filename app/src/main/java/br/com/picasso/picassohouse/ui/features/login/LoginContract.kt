package br.com.picasso.picassohouse.ui.features.login

import br.com.picasso.picassohouse.ui.base.BasePresenter
import br.com.picasso.picassohouse.ui.base.BaseView


interface LoginContract {

    interface View : BaseView {
        fun showMainUi()
        fun showLoader(flag: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun authenticate(terminal: String, password: String)
    }

}