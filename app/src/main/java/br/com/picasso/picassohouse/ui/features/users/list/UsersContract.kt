package br.com.picasso.picassohouse.ui.features.users.list

import br.com.picasso.picassohouse.models.User
import br.com.picasso.picassohouse.ui.base.BasePresenter
import br.com.picasso.picassohouse.ui.base.BaseView

interface UsersContract {

    interface View : BaseView {
        fun showUsers(users : List<User>)
        fun showUserDetail(user : User)
        fun showLoader(flag: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun selectItem(position: Int)
        fun removerItem(position: Int)
    }

}