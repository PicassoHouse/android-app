package br.com.picasso.picassohouse.ui.features.users.list

import br.com.picasso.picassohouse.models.User
import br.com.picasso.picassohouse.networking.PicassoHouseAPI
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class UsersPresenter(val apiService : PicassoHouseAPI) : UsersContract.Presenter {

    var view : UsersContract.View? = null

    var users : List<User> = emptyList()

    override fun start() {
        view?.showLoader(true)
        apiService.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.showLoader(false)
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

    override fun selectItem(position: Int) {
        view?.showUserDetail(users[position])
    }

    override fun removerItem(position: Int) {
        view?.showLoader(true)
        val user = users[position]
        apiService.removeUser(user._id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.showLoader(false)
                    users = users.minus(user)
                    view?.showUsers(users)
                }, ::print)
    }

}
