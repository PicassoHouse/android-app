package br.com.picasso.picassohouse.ui.base

interface BasePresenter<V : BaseView> {
    fun start()

    fun attachView(view: V)
    fun detachView()
}