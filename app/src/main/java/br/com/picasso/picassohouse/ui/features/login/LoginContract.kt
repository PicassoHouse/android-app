package br.com.picasso.picassohouse.ui.features.login

import br.com.picasso.picassohouse.ui.base.BasePresenter
import br.com.picasso.picassohouse.ui.base.BaseView


interface LoginContract {

    interface View : BaseView {
        fun showMainUi()
        fun showLoader(flag: Boolean)
        fun showDialog(title: String, message: String)
    }

    interface Presenter : BasePresenter<View> {
        fun authenticate(username: String, password: String)
    }

}