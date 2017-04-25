package br.com.picasso.picassohouse.ui.features.users.list

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
import br.com.picasso.picassohouse.ui.features.users.detail.UserDetailFragment
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnItemClick


class UsersFragment :  Fragment(), UsersContract.View {

    private var parentActivity: MainActivity? = null
        get() = activity as MainActivity

    // --------------------------------------------------------
    // initializer
    // --------------------------------------------------------
    companion object {
        fun newInstance(): UsersFragment {
            val f = UsersFragment()
            val args = Bundle()
            f.arguments = args
            return f
        }
    }
    // --------------------------------------------------------
    // Properties
    // --------------------------------------------------------
    lateinit var presenter : UsersPresenter
    lateinit var usersAdapter: UsersAdapter

    // --------------------------------------------------------
    // Views
    // --------------------------------------------------------
    @BindView(R.id.lv_users) lateinit var lvUsers : ListView

    // --------------------------------------------------------
    // Lifecycler
    // --------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiService = (activity.applicationContext as PHApplication).phService
        presenter = UsersPresenter(apiService)
        presenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
        parentActivity?.setActionBarTitle(R.string.picasso_house)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        parentActivity?.fab?.visibility = View.GONE
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_users, container, false)
        ButterKnife.bind(this, view)
        setup()
        return view
    }

    private fun setup() {
        //set actin bar title
        parentActivity?.setActionBarTitle(R.string.users)

        //setup FabButton
        parentActivity?.fab?.visibility = View.VISIBLE
        parentActivity?.fab?.setOnClickListener {
            parentActivity?.navigationController?.pushFragment(UserDetailFragment.newInstance())
        }

        //setup adapter
        usersAdapter = UsersAdapter(context)
        lvUsers.adapter = usersAdapter
    }

    @OnItemClick(R.id.lv_users)
    fun onClickUser(position: Int){
        Toast.makeText(parentActivity, "Click $position", Toast.LENGTH_LONG).show()
    }

    // --------------------------------------------------------
    //  UserDetailContract.View methods
    // --------------------------------------------------------

    override fun showUsers(users: List<User>) {
        usersAdapter.setItems(users)
    }

}
