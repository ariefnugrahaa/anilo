package works.anilo.arief.presentation.main.repositories

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_repositories.*
import works.anilo.arief.AriefApplication
import works.anilo.arief.R
import works.anilo.arief.common.extension.makeGone
import works.anilo.arief.common.extension.makeVisible
import works.anilo.arief.common.extension.showToast
import works.anilo.arief.presentation.main.contributors.model.ContributorsModelItem
import works.anilo.arief.presentation.main.repositories.adapter.SearchRepositoriesAdapter
import works.anilo.arief.presentation.main.repositories.model.SearchHeaderModelItem


class RepositoriesFragment : Fragment(), SearchRepositoriesContract.View {

    private val presenter by lazy { AriefApplication.ariefComponent.provideSearchRepositoriesPresenter() }
    private fun getListAdapter(): SearchRepositoriesAdapter? = rv_search_repositories.adapter as? SearchRepositoriesAdapter
    private var originalList = listOf<SearchHeaderModelItem.SearchModel>()
    private var baseKeyword:String = "q"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repositories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attach(this)
    }

    override fun initViews() {

        edit_text_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) = Unit
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val keyword = p0.toString()
                baseKeyword = keyword
                when {
                    keyword.length >= 3 || keyword.isNotEmpty() -> {
                        iv_clear_search.makeVisible()
                        presenter.fetchSearchRepositoriesData(q = keyword)
                    }
                    keyword.isEmpty() -> {
                        iv_clear_search.makeGone()
                        presenter.fetchSearchRepositoriesData(q = "q")
                    }
                }
            }
        })

        iv_clear_search.setOnClickListener {
            edit_text_search.text.clear()
        }

        rv_search_repositories.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = SearchRepositoriesAdapter()
        }.also { presenter.fetchSearchRepositoriesData(q = "q") }

    }

    override fun onError(it: Throwable?) {
        context?.showToast("Too many request, Harap tunggu beberapa waktu dan lakukan search ulang")
        shimmer_loading_repositories.makeGone()
    }

    override fun showListData(it: SearchHeaderModelItem) {
        activity?.runOnUiThread { it?.let {
            if(getListAdapter()?.itemCount == 0) {
                getListAdapter()?.removeAllData()
                getListAdapter()?.setData(it.items.toMutableList())
            }else{
                getListAdapter()?.removeAllData()
                getListAdapter()?.addAllData(it.items.toMutableList())
            }
            originalList = getListAdapter()?.getAllData().orEmpty()
        } }
    }

    override fun hideLoading() {
        shimmer_loading_repositories.makeGone()
        rv_search_repositories.makeVisible()
    }

    override fun showLoading() {
        shimmer_loading_repositories.makeVisible()
        rv_search_repositories.makeGone()
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

}