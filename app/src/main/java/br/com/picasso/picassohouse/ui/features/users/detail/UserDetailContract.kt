package br.com.picasso.picassohouse.ui.features.users.detail

import br.com.picasso.picassohouse.models.Room
import br.com.picasso.picassohouse.models.User
import br.com.picasso.picassohouse.ui.base.BasePresenter
import br.com.picasso.picassohouse.ui.base.BaseView

interface UserDetailContract {

    interface View : BaseView {
        fun showUser(user : User)
    }

    interface Presenter : BasePresenter<View> {
    }

}