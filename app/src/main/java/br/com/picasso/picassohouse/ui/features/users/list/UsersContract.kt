package br.com.picasso.picassohouse.ui.features.users.list

import br.com.picasso.picassohouse.models.Room
import br.com.picasso.picassohouse.models.User
import br.com.picasso.picassohouse.ui.base.BasePresenter
import br.com.picasso.picassohouse.ui.base.BaseView

interface UsersContract {

    interface View : BaseView {
        fun showUsers(users : List<User>)
    }

    interface Presenter : BasePresenter<View> {
    }

}