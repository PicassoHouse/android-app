package br.com.picasso.picassohouse.ui.features.login

import android.content.Context
import br.com.picasso.picassohouse.networking.PicassoHouseAPI
import br.com.picasso.picassohouse.utils.AuthHelper
import rx.android.schedulers.AndroidSchedulers
import rx.functions.Action1
import rx.schedulers.Schedulers


class LoginPresenter(val context: Context, val apiService : PicassoHouseAPI) : LoginContract.Presenter {

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
        view?.showLoader(true)
        apiService.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ token ->
                    AuthHelper.setAccessToken(context, token)
                    view?.showLoader(false)
                    view?.showMainUi()
                }, ::print)

    }

}