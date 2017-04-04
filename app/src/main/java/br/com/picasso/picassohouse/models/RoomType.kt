package br.com.picasso.picassohouse.models

import br.com.picasso.picassohouse.R

enum class RoomType {
    bedRoom, livingRoom, kitchen, garage;

    fun iconRes() : Int {
        //TODO: change icons
        when (this) {
            bedRoom -> return R.drawable.ic_garage
            kitchen -> return R.drawable.ic_garage
            livingRoom -> return R.drawable.ic_garage
            garage -> return R.drawable.ic_garage
        }
    }
}