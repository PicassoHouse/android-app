package br.com.picasso.picassohouse.ui.features.login

import android.support.v7.app.AppCompatActivity
import android.app.ProgressDialog

import android.os.Bundle
import android.view.View
import android.widget.EditText

import br.com.picasso.picassohouse.R

import android.content.Intent
import br.com.picasso.picassohouse.ui.features.MainActivity
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick

class LoginActivity : AppCompatActivity() , LoginContract.View {

    lateinit var presenter: LoginPresenter

    // --------------------------------------------------------
    // Views
    // --------------------------------------------------------
    @BindView(R.id.ed_username) lateinit var edUsername : EditText
    @BindView(R.id.ed_password) lateinit var edPassword : EditText

    var progressDialog : ProgressDialog? = null

    // --------------------------------------------------------
    // Lifecycler
    // --------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ButterKnife.bind(this)

        presenter = LoginPresenter(this)
        presenter.attachView(this)
        presenter.start()
    }

    // --------------------------------------------------------
    // Events
    // --------------------------------------------------------
    @OnClick(R.id.bt_login)
    fun onClickBtLogin(view : View) {
        //TODO: autenticar usuario

        val username = edUsername.text.toString()
        val password = edPassword.text.toString()

        //TODO: validar formulario
        presenter.authenticate(username, password)
    }

    // --------------------------------------------------------
    // LoginContract.View methods
    // --------------------------------------------------------

    override fun showMainUi() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun showLoader(flag: Boolean) {
        if (flag) {
            progressDialog = ProgressDialog.show(this, "", "Autenticando...", true, false)
        }
        else if (progressDialog != null) {
            progressDialog?.dismiss()
        }
    }

}

