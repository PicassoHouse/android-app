package br.com.picasso.picassohouse.ui.features.users.detail

import br.com.picasso.picassohouse.models.User
import br.com.picasso.picassohouse.models.UserRole
import br.com.picasso.picassohouse.ui.base.BasePresenter
import br.com.picasso.picassohouse.ui.base.BaseView

interface UserDetailContract {

    interface View : BaseView {
        fun showUser(user : User)
        fun showAlert(title: String, message: String)
        fun close()
    }

    interface Presenter : BasePresenter<View> {
        fun submit(username: String,
                   displayName: String,
                   password: String,
                   authCode: String,
                   type: UserRole)
    }

}