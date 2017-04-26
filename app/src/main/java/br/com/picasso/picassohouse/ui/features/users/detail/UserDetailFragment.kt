package br.com.picasso.picassohouse.ui.features.users.detail

import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import br.com.picasso.picassohouse.PHApplication
import br.com.picasso.picassohouse.R
import br.com.picasso.picassohouse.models.User
import br.com.picasso.picassohouse.models.UserRole
import br.com.picasso.picassohouse.ui.features.MainActivity
import butterknife.*


class UserDetailFragment :  Fragment(), UserDetailContract.View  {

    private var parentActivity: MainActivity? = null
        get() = activity as MainActivity



    // --------------------------------------------------------
    // initializer
    // --------------------------------------------------------
    companion object {

        val USER_KEY = "USER_KEY"

        fun newInstance(user : User? = null): UserDetailFragment {
            val f = UserDetailFragment()
            val args = Bundle()
            if(user != null)
                args.putSerializable(USER_KEY, user)
            f.arguments = args
            return f
        }
    }
    // --------------------------------------------------------
    // Properties
    // --------------------------------------------------------
    lateinit var presenter : UserDetailPresenter

    // --------------------------------------------------------
    // Views
    // --------------------------------------------------------
    @BindView(R.id.ed_username) lateinit var edUsername : EditText
    @BindView(R.id.ed_display_name) lateinit var edDisplayName : EditText
    @BindView(R.id.tv_label_password) lateinit var tvLabelPassword : TextView
    @BindView(R.id.ed_password) lateinit var edPassword : EditText
    @BindView(R.id.ed_auth_code) lateinit var edAuthCode : EditText
    @BindView(R.id.rd_user_role) lateinit var rdGroupUserRole : RadioGroup
    @BindView(R.id.bt_submit) lateinit var btSubmit : Button


    // --------------------------------------------------------
    // Lifecycler
    // --------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiService = (activity.applicationContext as PHApplication).phService

        presenter = UserDetailPresenter(apiService, arguments.getSerializable(USER_KEY) as? User)
        presenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
        parentActivity?.setActionBarTitle(if(presenter.isEditing) R.string.edit_user else R.string.add_user)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_user_detail, container, false)
        ButterKnife.bind(this, view)
        setup()
        return view
    }

    private fun setup() {
    }

    @OnClick(R.id.bt_submit)
    fun onClickSubmit(view: View) {

        val username = edUsername.text.toString()
        val password = edPassword.text.toString()
        val authCode = edAuthCode.text.toString()
        val displayName = edDisplayName.text.toString()
        val type = when(rdGroupUserRole.checkedRadioButtonId) {
            R.id.radio_admin -> UserRole.admin
            R.id.radio_user -> UserRole.user
            R.id.radio_guest -> UserRole.guest
            else -> UserRole.guest
        }

        presenter.submit(username, displayName, password, authCode, type)
    }

    // --------------------------------------------------------
    //  UserDetailContract.View methods
    // --------------------------------------------------------

    override fun showUser(user: User) {
        edUsername.setText(user.username)
        edDisplayName.setText(user.displayName)
        edAuthCode.setText(user.auth_code)

        val idChecked = when(user.role) {
            UserRole.admin -> R.id.radio_admin
            UserRole.user -> R.id.radio_user
            UserRole.guest -> R.id.radio_guest
            else -> R.id.radio_guest
        }
        rdGroupUserRole.check(idChecked)
        rdGroupUserRole.isEnabled = false

        btSubmit.visibility = View.GONE
        tvLabelPassword.visibility = View.GONE
        edPassword.visibility = View.GONE
    }

    override fun showAlert(title: String, message: String) {
        AlertDialog.Builder(parentActivity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, { dialog, which -> dialog.dismiss() })
                .show()
    }

    override fun close() {
        parentActivity?.navigationController?.popFragment()
    }
}
