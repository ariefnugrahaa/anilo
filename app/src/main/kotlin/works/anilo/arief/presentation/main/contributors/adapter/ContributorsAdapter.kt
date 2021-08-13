package works.anilo.arief.presentation.main.contributors.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_users.view.*
import works.anilo.arief.R
import works.anilo.arief.presentation.main.contributors.model.ContributorsModelItem

class UserAdapter():works.anilo.arief.common.base.adapter.BaseAdapter<RecyclerView.ViewHolder, ContributorsModelItem>() {

    private var data: MutableList<ContributorsModelItem> = mutableListOf()

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserViewHolder(layoutInflater.inflate(R.layout.item_users, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserViewHolder)
        holder.bindData(data[position])

    }

    override fun addAllData(data: MutableList<ContributorsModelItem>) {
        this.data.addAll(data)
        this.notifyDataSetChanged()
    }

    override fun addData(data: ContributorsModelItem) {
        this.data.add(data)
        this.notifyDataSetChanged()
    }

    override fun getDataAt(position: Int): ContributorsModelItem  = data[position]

    override fun getAllData(): MutableList<ContributorsModelItem> = data

    override fun setData(data: MutableList<ContributorsModelItem>) {
        this.data = data
        this.notifyDataSetChanged()
    }
}

class UserViewHolder(viewItem: View):RecyclerView.ViewHolder(viewItem){

    fun bindData(data: ContributorsModelItem) {
        itemView.textview_contributors.text = data.login
        itemView.textview_link_contributors.text = data.login
        itemView.textview_type_contributors.text = data.login

        Glide.with(itemView.context).load(data.avatarUrl).into(itemView.imageview_users)
    }
}
