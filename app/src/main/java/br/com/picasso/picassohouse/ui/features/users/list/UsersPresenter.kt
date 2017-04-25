package br.com.picasso.picassohouse.ui.features.users.list

import br.com.picasso.picassohouse.models.User
import br.com.picasso.picassohouse.networking.PicassoHouseAPI
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class UsersPresenter(val apiService : PicassoHouseAPI) : UsersContract.Presenter {

    var view : UsersContract.View? = null

    var users : List<User> = emptyList()

    override fun start() {
        apiService.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    users = it
                    view?.showUsers(users)
                }, ::print)
    }

    override fun attachView(view: UsersContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    // --------------------------------------------------------
    // DashboardContract.Presenter methods
    // --------------------------------------------------------


}
