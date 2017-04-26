package br.com.picasso.picassohouse.models

import java.io.Serializable

class User (
        val _id : String,
        val username : String,
        val displayName : String,
        val password : String,
        val auth_code : String,
        val imageUrl : String,
        val role : UserRole,
        val access_token: String) : Serializable
