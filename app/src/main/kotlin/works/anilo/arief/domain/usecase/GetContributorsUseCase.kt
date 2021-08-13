package works.anilo.arief.domain.usecase

import io.reactivex.Observable
import works.anilo.arief.data.server.ApiServiceManager
import works.anilo.arief.presentation.main.contributors.model.ContributorsModelItem

class GetContributorsUseCase (private val repository: ApiServiceManager){

    fun fetchContributors(page: Int): Observable<MutableList<ContributorsModelItem>> {
        return repository.emittedContributors(page)
    }
}