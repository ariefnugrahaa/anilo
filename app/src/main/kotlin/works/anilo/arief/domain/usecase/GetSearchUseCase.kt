package works.anilo.arief.domain.usecase

import io.reactivex.Observable
import works.anilo.arief.data.server.ApiServiceManager
import works.anilo.arief.presentation.main.repositories.model.SearchHeaderModelItem

class GetSearchUseCase (private val repository: ApiServiceManager){

    fun fetchSearch(q: String): Observable<SearchHeaderModelItem> {
        return repository.emittedSearchRepositories(q)
    }
}