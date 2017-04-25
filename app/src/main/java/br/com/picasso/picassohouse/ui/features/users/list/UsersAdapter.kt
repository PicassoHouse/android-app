package br.com.picasso.picassohouse.ui.features.users.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import butterknife.ButterKnife
import butterknife.BindView
import br.com.picasso.picassohouse.R
import br.com.picasso.picassohouse.models.User


open class UsersAdapter(context : Context) : BaseAdapter() {

    private var items = mutableListOf<User>()

    private val mInflator: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View?
        val holder: ItemViewHolder

        if (convertView != null) {
            view = convertView
            holder = view.tag as ItemViewHolder
        } else {
            view = mInflator.inflate(R.layout.item_users_list, parent, false)
            holder = ItemViewHolder(view)
            view.tag = holder
        }

        val user = items[position]

        holder.tvTitle.text = if (user.displayName.isNotEmpty()) user.displayName else user.username
        holder.tvSubtitle.text = user.role.toString()

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

    fun setItems(items: List<User>) {
        this.items = items as MutableList<User>
        notifyDataSetChanged()
    }
}