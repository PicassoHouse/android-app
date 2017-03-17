package br.com.picasso.picassohouse.ui.features.login

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //TODO: verificar se existe sessao se usuario: caso existir ir para MainActivity
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}
