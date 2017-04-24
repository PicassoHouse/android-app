package br.com.picasso.picassohouse.utils

import android.content.Context

class AuthHelper {
    companion object {

        val ACCESS_TOKEN_KEY = "ACCESS_TOKEN_KEY"

        fun setAccessToken(context: Context, token : String) {
            val sharedPref = context.getSharedPreferences(ACCESS_TOKEN_KEY, Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString(ACCESS_TOKEN_KEY, token)
            editor.commit()
        }

        fun getAccessToken(context: Context): String {
            val prefs = context.getSharedPreferences(ACCESS_TOKEN_KEY, Context.MODE_PRIVATE)
            return prefs.getString(ACCESS_TOKEN_KEY, "")
        }

    }
}