package br.com.picasso.picassohouse.ui.features.login

import android.content.Context


class LoginPresenter(private var context: Context) : LoginContract.Presenter {

    private var view : LoginContract.View? = null

    // --------------------------------------------------------
    // LoginContract.Presenter methods
    // --------------------------------------------------------
    override fun start() {

    }

    override fun attachView(view: LoginContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    override fun authenticate(username: String, password: String) {
        //TODO: implements auth
        view?.showMainUi()
    }
}