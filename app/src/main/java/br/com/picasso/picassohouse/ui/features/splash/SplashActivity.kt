package br.com.picasso.picassohouse.ui.features.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent
import br.com.picasso.picassohouse.ui.features.MainActivity
import br.com.picasso.picassohouse.utils.AuthHelper


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val token = AuthHelper.getAccessToken(this)
        if(token.isEmpty()) {
            startActivity(Intent(this, LoginActivity::class.java))
        }else{
            startActivity(Intent(this, MainActivity::class.java))
        }

        finish()
    }
}
