package br.com.picasso.picassohouse.models

class User (
        val username : String,
        val displayName : String,
        val password : String,
        val auth_code : String,
        val imageUrl : String,
        val role : UserRole,
        val access_token: String)
