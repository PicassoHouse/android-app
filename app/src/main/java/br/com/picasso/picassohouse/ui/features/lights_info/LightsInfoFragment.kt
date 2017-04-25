package br.com.picasso.picassohouse.ui.features.lights_info

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import br.com.picasso.picassohouse.PHApplication
import br.com.picasso.picassohouse.R
import br.com.picasso.picassohouse.models.CurrentMonthLightInfo
import br.com.picasso.picassohouse.models.LightHistoryItem
import br.com.picasso.picassohouse.ui.features.MainActivity
import butterknife.BindView
import butterknife.ButterKnife
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


class LightsInfoFragment:  Fragment() , LightsInfoContract.View {

    private var parentActivity: MainActivity? = null
        get() = activity as MainActivity


    // --------------------------------------------------------
    // initializer
    // --------------------------------------------------------
    companion object {
        fun newInstance(): LightsInfoFragment {
            val f = LightsInfoFragment()
            val args = Bundle()
            f.arguments = args
            return f
        }
    }

    // --------------------------------------------------------
    // Properties
    // --------------------------------------------------------
    lateinit var presenter : LightsInfoPresenter
    // --------------------------------------------------------
    // Views
    // --------------------------------------------------------
    @BindView(R.id.chart) lateinit var chartView: BarChart
    @BindView(R.id.tv_hours_on) lateinit var tvHoursOn: TextView
    @BindView(R.id.tv_hours_off) lateinit var tvHoursOff: TextView


    // --------------------------------------------------------
    // Lifecycler
    // --------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = (activity.applicationContext as PHApplication).phService
        presenter = LightsInfoPresenter(apiService)
        presenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_lights_info, container, false)
        ButterKnife.bind(this, view)
        setup()
        return view
    }

    private fun setup() {

        parentActivity?.setActionBarTitle(R.string.lights_info)

        //setup chartView
        chartView.setFitBars(true)
        chartView.isScaleYEnabled = false
        chartView.isScaleXEnabled = false
        chartView.axisLeft.setDrawLabels(true)
        chartView.axisLeft.setDrawAxisLine(false)
        chartView.axisLeft.setDrawGridLines(true)
        chartView.axisLeft.setDrawZeroLine(true)
        chartView.axisRight.isEnabled = false
        chartView.xAxis.setDrawGridLines(false)
        chartView.xAxis.setDrawAxisLine(false)
        chartView.xAxis.setDrawLimitLinesBehindData(true)
        chartView.xAxis.granularity = 1f
        chartView.xAxis.setDrawLabels(true)
        chartView.xAxis.position = XAxis.XAxisPosition.BOTTOM
        chartView.legend.isEnabled = false
    }

    // --------------------------------------------------------
    // LightsContract.View methods
    // --------------------------------------------------------
    override fun showCurrentMonthInfo(info: CurrentMonthLightInfo) {
        tvHoursOff.text = "${info.hoursOff}hr"
        tvHoursOn.text = "${info.hoursOn}hr"
    }

    override fun showChart(history: List<LightHistoryItem>) : Unit {

        val dataSets = history.mapIndexed { index, lightHistoryItem ->
            val label = lightHistoryItem.label.substring(0, 3)

            val dataSet = BarDataSet(arrayListOf(BarEntry(index.toFloat(), lightHistoryItem.value.toFloat())), label)
            dataSet.color = context.resources.getColor(R.color.colorAccent)
            dataSet.label = label

            return@mapIndexed dataSet
        }

        //IBarDataSet
        chartView.data = BarData(dataSets)
        chartView.xAxis.valueFormatter = IndexAxisValueFormatter(history.map { it.label.substring(0,3) })
        chartView.invalidate()
    }
}
