package br.com.picasso.picassohouse.ui.features.lights

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import butterknife.ButterKnife
import butterknife.BindView
import br.com.picasso.picassohouse.R
import br.com.picasso.picassohouse.models.AccessHistory
import br.com.picasso.picassohouse.models.Room
import br.com.picasso.picassohouse.models.RoomType
import br.com.picasso.picassohouse.utils.asString


open class AccessHistoryAdapter(context : Context) : BaseAdapter() {

    private var items = mutableListOf<AccessHistory>()

    private val mInflator: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val holder:  AccessHistoryAdapter.ItemViewHolder

        if (convertView != null) {
            view = convertView
            holder = view.tag as AccessHistoryAdapter.ItemViewHolder
        } else {
            view = mInflator.inflate(R.layout.item_users_list, parent, false)
            holder = AccessHistoryAdapter.ItemViewHolder(view)
            view.tag = holder
        }

        val item = items[position]

        holder.tvTitle.text = item.user.username
        holder.tvSubtitle.text = item.date.asString("dd/MM/yyyy 'às' HH:mm")

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

        @BindView(R.id.rootView) lateinit var rootView: View
        @BindView(R.id.tv_title) lateinit var tvTitle: TextView
        @BindView(R.id.tv_subtitle) lateinit var tvSubtitle: TextView

        init {
            ButterKnife.bind(this, view)
        }
    }

    fun setItems(items: List<AccessHistory>) {
        this.items = items as MutableList<AccessHistory>
        notifyDataSetChanged()
    }
}