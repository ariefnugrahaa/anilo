package works.anilo.arief.presentation.main.contributors

import works.anilo.arief.presentation.main.contributors.model.ContributorsModelItem

interface ContributorsContract {

    interface View {
        fun initViews()
        fun onError(it: Throwable?)
        fun showListData(it: MutableList<ContributorsModelItem>?)
        fun hideLoading()
        fun showLoading()
    }

    interface Presenter {
        fun fetchContributorsData(since:Int)
    }
}