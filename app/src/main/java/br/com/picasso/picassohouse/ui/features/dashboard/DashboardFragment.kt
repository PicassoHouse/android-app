package br.com.picasso.picassohouse.ui.features.dashboard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.support.v7.widget.CardView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import br.com.picasso.picassohouse.PHApplication
import br.com.picasso.picassohouse.R
import br.com.picasso.picassohouse.ui.features.MainActivity
import br.com.picasso.picassohouse.ui.features.lights.DashboardContract
import br.com.picasso.picassohouse.ui.features.lights.DashboardPresenter
import br.com.picasso.picassohouse.ui.features.lights.LightsFragment
import br.com.picasso.picassohouse.ui.features.lights_info.LightsInfoFragment
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick


class DashboardFragment:  Fragment(), DashboardContract.View  {

    private var parentActivity: MainActivity? = null
        get() = activity as MainActivity

    // --------------------------------------------------------
    // initializer
    // --------------------------------------------------------
    companion object {
        fun newInstance(): DashboardFragment {
            val f = DashboardFragment()
            val args = Bundle()
            f.arguments = args
            return f
        }
    }
    // --------------------------------------------------------
    // Properties
    // --------------------------------------------------------
    lateinit var presenter : DashboardPresenter
    // --------------------------------------------------------
    // Views
    // --------------------------------------------------------
    @BindView(R.id.bt_lock_house) lateinit var btLockHouse: CardView
    @BindView(R.id.bt_unlock_house) lateinit var btUnlockHouse: CardView

    // --------------------------------------------------------
    // Lifecycler
    // --------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiService = (activity.applicationContext as PHApplication).phService
        presenter = DashboardPresenter(apiService)
        presenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
        parentActivity?.setActionBarTitle(R.string.picasso_house)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_dashboard, container, false)
        ButterKnife.bind(this, view)
        setup()
        return view
    }

    private fun setup() {
        //TODO: all configs should be impremented here
    }

    @OnClick(R.id.bt_lock_house, R.id.bt_unlock_house, R.id.bt_lights, R.id.bt_lights_info)
    fun onClickOptions(view: View) {
        when (view.id) {
            R.id.bt_lock_house -> onClickLockButton()
            R.id.bt_unlock_house -> onClickUnlockButton()
            R.id.bt_lights -> parentActivity?.navigationController?.pushFragment(LightsFragment.newInstance())
            R.id.bt_lights_info -> parentActivity?.navigationController?.pushFragment(LightsInfoFragment.newInstance())
        }
    }

    private fun onClickLockButton() {
        AlertDialog.Builder(activity)
                .setMessage(R.string.confirm_lock_house)
                .setTitle(R.string.atention)
                .setNegativeButton(android.R.string.cancel, { dialog, _  -> dialog.dismiss() })
                .setPositiveButton(R.string.lock, { _, _ -> presenter.setHomeIsLocked(true) })
                .show()
    }

    private fun onClickUnlockButton() {
        AlertDialog.Builder(activity)
                .setMessage(R.string.confirm_unlock_house)
                .setTitle(R.string.atention)
                .setNegativeButton(android.R.string.cancel, { dialog, _ -> dialog.dismiss() })
                .setPositiveButton(R.string.unlock, { _, _ -> presenter.setHomeIsLocked(false) })
                .show()
    }

    @OnClick(R.id.bt_open_garage)
    fun onClickOpenGarage(view: View) {
        presenter.setGarageClosed(false)
    }

    @OnClick(R.id.bt_close_garage)
    fun onClickCloseGarage(view: View) {
        presenter.setGarageClosed(true)
    }

    // --------------------------------------------------------
    //  DashboardContract.View methods
    // --------------------------------------------------------
    override fun showButtonLockHouse() {
        btLockHouse.visibility = View.VISIBLE
        btUnlockHouse.visibility = View.GONE
    }

    override fun showButtonUnlockHouse() {
        btLockHouse.visibility = View.GONE
        btUnlockHouse.visibility = View.VISIBLE
    }
}
