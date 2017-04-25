package br.com.picasso.picassohouse.ui.features.lights_info

import br.com.picasso.picassohouse.models.CurrentMonthLightInfo
import br.com.picasso.picassohouse.models.LightHistoryItem
import br.com.picasso.picassohouse.ui.base.BasePresenter
import br.com.picasso.picassohouse.ui.base.BaseView

interface LightsInfoContract {

    interface View : BaseView {
        fun showCurrentMonthInfo(info: CurrentMonthLightInfo)
        fun showChart(history: List<LightHistoryItem>)
    }

    interface Presenter : BasePresenter<View>

}