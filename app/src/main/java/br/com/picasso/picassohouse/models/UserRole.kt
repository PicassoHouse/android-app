package br.com.picasso.picassohouse.models


enum class UserRole {
    admin, user, guest;

    override fun toString(): String = when(this) {
        admin -> "Administrador"
        user -> "Usuário"
        guest -> "Convidado"
    }

}
