package br.com.picasso.picassohouse.ui.features.users.detail

import br.com.picasso.picassohouse.models.User
import br.com.picasso.picassohouse.networking.PicassoHouseAPI
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class UserDetailPresenter(val apiService : PicassoHouseAPI) : UserDetailContract.Presenter {

    var view : UserDetailContract.View? = null

    var users : List<User> = emptyList()

    override fun start() {

    }

    override fun attachView(view: UserDetailContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    // --------------------------------------------------------
    // DashboardContract.Presenter methods
    // --------------------------------------------------------


}
