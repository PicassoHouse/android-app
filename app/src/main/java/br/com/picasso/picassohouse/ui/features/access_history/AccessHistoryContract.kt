package br.com.picasso.picassohouse.ui.features.access_history

import br.com.picasso.picassohouse.models.AccessHistory
import br.com.picasso.picassohouse.ui.base.BasePresenter
import br.com.picasso.picassohouse.ui.base.BaseView

interface AccessHistoryContract {

    interface View : BaseView {
        fun showAccessHistory(history: List<AccessHistory>)
    }

    interface Presenter : BasePresenter<View> {
    }

}