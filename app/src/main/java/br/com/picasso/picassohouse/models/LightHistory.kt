package br.com.picasso.picassohouse.models

import java.util.*

class LightHistory(val user:User?,
                   val date: Date,
                   val isLightOn: Boolean,
                   val room_id: Int)