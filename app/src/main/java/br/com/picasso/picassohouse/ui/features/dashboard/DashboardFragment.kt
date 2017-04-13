package br.com.picasso.picassohouse.ui.features.dashboard

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import br.com.picasso.picassohouse.R
import br.com.picasso.picassohouse.ui.features.MainActivity
import br.com.picasso.picassohouse.ui.features.lights.LightsFragment
import br.com.picasso.picassohouse.ui.features.lights_info.LightsInfoFragment
import butterknife.ButterKnife
import butterknife.OnClick


class DashboardFragment:  Fragment() {

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
    // Lifecycler
    // --------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_dashboard, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    @OnClick(R.id.bt_lights, R.id.bt_lights_info)
    fun onClickOptions(view: View) {
        when (view.id) {
            R.id.bt_lights -> parentActivity?.navigationController?.pushFragment(LightsFragment.newInstance())
            R.id.bt_lights_info -> parentActivity?.navigationController?.pushFragment(LightsInfoFragment.newInstance())
        }
    }
}
