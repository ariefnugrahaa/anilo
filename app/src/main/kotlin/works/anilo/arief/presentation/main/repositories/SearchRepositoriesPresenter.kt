package works.anilo.arief.presentation.main.repositories

import works.anilo.arief.common.base.presenter.BasePresenter
import works.anilo.arief.common.utils.Logger
import works.anilo.arief.domain.usecase.GetSearchUseCase
import works.anilo.arief.presentation.main.repositories.model.SearchHeaderModelItem

class SearchRepositoriesPresenter(private val getSearchRepositoriesUseCase: GetSearchUseCase):BasePresenter<SearchRepositoriesContract.View>(),
    SearchRepositoriesContract.Presenter {

    override fun onAttach() {
        super.onAttach()
        view().initViews()
    }

    private fun onFetchSearchRepositoriesDataFailed(it: Throwable?) {
        view().onError(it)
        Logger.debug("Error >>> ${it?.message}")
    }

    override fun fetchSearchRepositoriesData(q: String) {
        view().showLoading()
        observe {
            subscribeOnIoSchedulers(
                getSearchRepositoriesUseCase.fetchSearch(q)
            ).subscribe(
                { onFetchSearchRepositoriesDataSuccess(it) },
                { onFetchSearchRepositoriesDataFailed(it) }
            )
        }
    }

    private fun onFetchSearchRepositoriesDataSuccess(it: SearchHeaderModelItem){
        view().hideLoading()
        view().showListData(it)
    }

}