package br.com.picasso.picassohouse.models


class Room(val id: String,
           val title: String,
           val type: RoomType,
           var isLightOn: Boolean)