package br.com.picasso.picassohouse.models

import br.com.picasso.picassohouse.R

enum class RoomType {
    bedRoom, livingRoom, kitchen, garage, bathRoom;

    fun iconRes() : Int {
        when (this) {
            bedRoom -> return R.drawable.ic_bed
            kitchen -> return R.drawable.ic_kitchen
            livingRoom -> return R.drawable.ic_living_room
            garage -> return R.drawable.ic_garage
            bathRoom -> return R.drawable.ic_bath
        }
    }
}