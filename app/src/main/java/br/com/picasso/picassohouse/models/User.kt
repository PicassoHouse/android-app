package br.com.picasso.picassohouse.models

import java.util.*

class User (
        val username : String,
        val displayName : String,
        val password : String,
        val auth_code : String,
        val imageUrl : String,
        val role : String,
        val access_token: String)
