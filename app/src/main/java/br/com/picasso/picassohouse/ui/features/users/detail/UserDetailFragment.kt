package br.com.picasso.picassohouse.ui.features.users.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ListView
import android.widget.Toast
import br.com.picasso.picassohouse.PHApplication
import br.com.picasso.picassohouse.R
import br.com.picasso.picassohouse.models.User
import br.com.picasso.picassohouse.ui.features.MainActivity
import br.com.picasso.picassohouse.ui.features.users.list.UsersAdapter
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnItemClick
import butterknife.OnItemLongClick


class UserDetailFragment :  Fragment(), UserDetailContract.View  {

    private var parentActivity: MainActivity? = null
        get() = activity as MainActivity

    // --------------------------------------------------------
    // initializer
    // --------------------------------------------------------
    companion object {
        fun newInstance(): UserDetailFragment {
            val f = UserDetailFragment()
            val args = Bundle()
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

    // --------------------------------------------------------
    // Lifecycler
    // --------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiService = (activity.applicationContext as PHApplication).phService
        presenter = UserDetailPresenter(apiService)
        presenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
        parentActivity?.setActionBarTitle(R.string.picasso_house)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_user_detail, container, false)
        ButterKnife.bind(this, view)
        setup()
        return view
    }

    private fun setup() {
    }


    // --------------------------------------------------------
    //  UserDetailContract.View methods
    // --------------------------------------------------------

    override fun showUser(user: User) {
        //TODO: show user info
    }

}
