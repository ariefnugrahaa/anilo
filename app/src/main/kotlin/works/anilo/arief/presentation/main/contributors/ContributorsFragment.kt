package works.anilo.arief.presentation.main.contributors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_contributors.*
import works.anilo.arief.AriefApplication
import works.anilo.arief.R
import works.anilo.arief.common.extension.isVisible
import works.anilo.arief.common.extension.makeGone
import works.anilo.arief.common.extension.makeVisible
import works.anilo.arief.presentation.main.contributors.model.ContributorsModelItem
import works.anilo.arief.common.extension.showToast
import works.anilo.arief.presentation.main.contributors.adapter.UserAdapter

class ContributorsFragment : Fragment(), ContributorsContract.View {

    private val presenter by lazy { AriefApplication.ariefComponent.provideContributorsPresenter() }
    private fun getListAdapter(): UserAdapter? = rv_contributors.adapter as? UserAdapter
    private var originalList = listOf<ContributorsModelItem>()
    private var shouldLoadMore = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contributors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.attach(this)
    }

    override fun initViews() {

        rv_contributors.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = UserAdapter()
            addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (shouldLoadMore) {
                        val lastItemId = getListAdapter()?.getAllData()?.last()?.id ?: 0
                        val lastItemPosition = (layoutManager as? LinearLayoutManager)?.findLastCompletelyVisibleItemPosition()
                                ?: 0
                        val lastItem = getListAdapter()?.getAllData()?.get(lastItemPosition)?.id
                                ?: 0
                        if (lastItemId == lastItem) {
                            presenter.fetchContributorsData(lastItemId)
                        }
                    }
                }
            })
        }.also { presenter.fetchContributorsData(0) }
    }

    override fun onError(it: Throwable?) {
        context?.showToast("Error server")
    }

    override fun showListData(it: MutableList<ContributorsModelItem>?) {
        activity?.runOnUiThread { it?.let {
            if(getListAdapter()?.itemCount == 0) {
                getListAdapter()?.setData(it)
            }else{
                getListAdapter()?.addAllData(it)
            }
            originalList = getListAdapter()?.getAllData().orEmpty()
        } }
    }

    override fun hideLoading() {
        shimmer_loading_contributor.makeGone()
    }

    override fun showLoading() {
        shimmer_loading_contributor.makeVisible()
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

}