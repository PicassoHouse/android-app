package br.com.picasso.picassohouse.ui.features

import android.os.Bundle
import android.view.View
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import br.com.picasso.picassohouse.R
import android.content.Intent
import android.widget.TextView
import br.com.picasso.picassohouse.PHApplication
import br.com.picasso.picassohouse.models.User
import butterknife.ButterKnife
import br.com.picasso.picassohouse.ui.features.dashboard.DashboardFragment
import br.com.picasso.picassohouse.ui.features.lights.LightsFragment
import br.com.picasso.picassohouse.ui.features.lights_info.LightsInfoFragment
import br.com.picasso.picassohouse.ui.features.login.LoginActivity
import br.com.picasso.picassohouse.utils.AuthHelper
import br.com.picasso.picassohouse.utils.setupStatusBarLollilop
import butterknife.BindView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


open class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    // --------------------------------------------------------
    // Views
    // --------------------------------------------------------
    @BindView(R.id.toolbar) lateinit var toolbar: Toolbar
    @BindView(R.id.drawer_layout) lateinit var drawerLayout: DrawerLayout
    @BindView(R.id.nav_view) lateinit var navigationView: NavigationView



    lateinit var toggle: ActionBarDrawerToggle

    // --------------------------------------------------------
    // Properties
    // --------------------------------------------------------
    lateinit var navigationController : NavigationController

    val rootLayout: View?
        get() = drawerLayout


    lateinit var currentUser : User

    // --------------------------------------------------------
    // Lifecycler
    // --------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.setDebug(true)
        ButterKnife.bind(this)

        setSupportActionBar(toolbar)
        setupStatusBarLollilop()

        toggle = ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close)
        drawerLayout.setDrawerListener(toggle)
        toggle.syncState()

        //initialize navigation
        navigationController = NavigationController(this)
        navigationController.presentFragment(DashboardFragment.newInstance())

        navigationView.setNavigationItemSelectedListener(this)

        loadUser()
    }

    private fun loadUser() {
        val apiService = (applicationContext as PHApplication).phService

        apiService.getUserLogged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ it ->
                    currentUser = it
                    val headerView = navigationView.getHeaderView(0)
                    (headerView.findViewById(R.id.tv_display_name) as TextView).text = it.displayName
                    (headerView.findViewById(R.id.tv_username) as TextView).text = it.username
                }, ::print)
    }


    override fun onBackPressed() {
        setActionBarTitle(R.string.app_name)
        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            this.onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    // --------------------------------------------------------
    // NavigationView Listener
    // --------------------------------------------------------

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_dashboard -> navigationController.presentFragment(DashboardFragment.newInstance())
            R.id.nav_luzes -> navigationController.presentFragment(LightsFragment.newInstance())
            R.id.nav_spent_history -> navigationController.presentFragment(LightsInfoFragment.newInstance())
            R.id.nav_logout -> logout()
            else -> {
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun logout() {
        AuthHelper.setAccessToken(this, "")

        navigationController.clearStack()

        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    fun setActionBarTitle(title: Int) {
        supportActionBar?.setTitle(title)
    }
}