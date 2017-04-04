package br.com.picasso.picassohouse.ui.features.lights

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import butterknife.ButterKnife
import butterknife.BindView
import br.com.picasso.picassohouse.R
import br.com.picasso.picassohouse.models.Room


open class LightsAdapter(context : Context) : BaseAdapter() {

    private var items = mutableListOf<Room>()

    private val mInflator: LayoutInflater = LayoutInflater.from(context)

    var onSwitchChangeListener : OnSwitchChangeListener? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val holder:  LightsAdapter.ItemViewHolder

        if (convertView != null) {
            view = convertView
            holder = view.tag as LightsAdapter.ItemViewHolder
        } else {
            view = mInflator.inflate(R.layout.item_lights_list, parent, false)
            holder = LightsAdapter.ItemViewHolder(view)
            view.tag = holder
        }

        val room = items[position]

        holder.tvTitle.text = room.title
        holder.ivIcon.setImageResource(room.type.iconRes())
        holder.status.isChecked = room.isLightOn
        holder.status.setOnCheckedChangeListener { _, isChecked ->  onSwitchChangeListener?.onSwitchChangeListener(position, isChecked)}

        return view!!
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return items.size
    }


    internal class ItemViewHolder(view: View) {

        @BindView(R.id.tv_title) lateinit var tvTitle: TextView
        @BindView(R.id.iv_icon) lateinit var ivIcon: ImageView
        @BindView(R.id.sw_status) lateinit var status: Switch

        init {
            ButterKnife.bind(this, view)
        }
    }

    fun setOnSwitchChangeListener(listener : ((position: Int, toStatus: Boolean) -> Unit)?) {
        onSwitchChangeListener = object : OnSwitchChangeListener {
            override fun onSwitchChangeListener(position: Int, toStatus: Boolean) {
                listener?.invoke(position, toStatus)
            }
        }
    }

    interface OnSwitchChangeListener {
        fun onSwitchChangeListener(position: Int, toStatus: Boolean)
    }

    fun setItems(items: List<Room>) {
        this.items = items as MutableList<Room>
        notifyDataSetChanged()
    }
}