package works.anilo.arief.presentation.main.repositories

import works.anilo.arief.presentation.main.repositories.model.SearchHeaderModelItem

interface SearchRepositoriesContract {

    interface View {
        fun initViews()
        fun onError(it: Throwable?)
        fun showListData(it: SearchHeaderModelItem)
        fun hideLoading()
        fun showLoading()
    }

    interface Presenter {
        fun fetchSearchRepositoriesData(q: String)
    }
}