package works.anilo.arief.presentation.main.contributors

import works.anilo.arief.common.base.presenter.BasePresenter
import works.anilo.arief.common.utils.Logger
import works.anilo.arief.domain.usecase.GetContributorsUseCase
import works.anilo.arief.presentation.main.contributors.model.ContributorsModelItem

class ContributorsPresenter(private val getContributorsUseCase: GetContributorsUseCase):BasePresenter<ContributorsContract.View>(),
    ContributorsContract.Presenter {

    override fun onAttach() {
        super.onAttach()
        view().initViews()
    }

    private fun onFetchContributorsDataFailed(it: Throwable?) {
        view().onError(it)
        Logger.debug("Error >>> ${it?.message}")
    }

    override fun fetchContributorsData(since:Int) {
        view().showLoading()
            observe {
             subscribeOnIoSchedulers(
                     getContributorsUseCase.fetchContributors(since)
             ).subscribe(
                     { onFetchUsersDataSuccess(it) },
                     { onFetchContributorsDataFailed(it) }
             )
        }
    }

    private fun onFetchUsersDataSuccess(it: MutableList<ContributorsModelItem>){
        view().hideLoading()
        view().showListData(it)
    }
}