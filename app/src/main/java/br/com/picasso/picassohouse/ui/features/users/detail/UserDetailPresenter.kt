package br.com.picasso.picassohouse.ui.features.users.detail

import br.com.picasso.picassohouse.models.User
import br.com.picasso.picassohouse.models.UserRole
import br.com.picasso.picassohouse.networking.PicassoHouseAPI
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class UserDetailPresenter(val apiService : PicassoHouseAPI,
                          val user : User? = null) : UserDetailContract.Presenter {

    var view : UserDetailContract.View? = null

    var isEditing : Boolean = (user != null)

    override fun start() {
        if (isEditing) view?.showUser(user!!)
    }

    override fun attachView(view: UserDetailContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }

    private fun isFormValid(user : User): Boolean {
        return user.username.length >= 6 || user.password.length >= 6
    }

    // --------------------------------------------------------
    // UserDetailContract.Presenter methods
    // --------------------------------------------------------

    override fun submit(username: String, displayName: String, password: String, authCode: String, type: UserRole) {
        val user = User("", username,displayName, password, authCode, "", type, "")

        if(!isFormValid(user)) {
            view?.showAlert("Atenção", "Preencha os campos corretamente!")
            return
        }

        apiService.addUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.close()
                }, ::print)
    }


}
