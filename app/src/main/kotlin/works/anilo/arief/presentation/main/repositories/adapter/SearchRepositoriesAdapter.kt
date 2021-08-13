package works.anilo.arief.presentation.main.repositories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_search.view.*
import works.anilo.arief.R
import works.anilo.arief.presentation.main.repositories.model.SearchHeaderModelItem

class SearchRepositoriesAdapter():works.anilo.arief.common.base.adapter.BaseAdapter<RecyclerView.ViewHolder, SearchHeaderModelItem.SearchModel>() {

    private var data: MutableList<SearchHeaderModelItem.SearchModel> = mutableListOf()

    fun removeAllData(): Boolean {
        return data.removeAll(data)
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SearchViewHolder(layoutInflater.inflate(R.layout.item_search, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SearchViewHolder)
        holder.bindData(data[position])
    }

    override fun getDataAt(position: Int): SearchHeaderModelItem.SearchModel  = data[position]

    override fun getAllData(): MutableList<SearchHeaderModelItem.SearchModel> = data

    override fun addAllData(data: MutableList<SearchHeaderModelItem.SearchModel>) {
        this.data.addAll(data)
        this.notifyDataSetChanged()    }

    override fun addData(data: SearchHeaderModelItem.SearchModel) {
        this.data.add(data)
        this.notifyDataSetChanged()
    }

    override fun setData(data: MutableList<SearchHeaderModelItem.SearchModel>) {
        this.data = data
        this.notifyDataSetChanged()
    }
}

class SearchViewHolder(viewItem: View):RecyclerView.ViewHolder(viewItem){

    fun bindData(data: SearchHeaderModelItem.SearchModel) {
        itemView.textview_name_repositories.text = data.fullName
        itemView.textview_url_repositories.text = data.owner.login
        itemView.textview_owner_repositories.text = data.owner.type
    }
}
