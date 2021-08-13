package works.anilo.arief.data.server

import io.reactivex.Observable
import works.anilo.arief.presentation.main.contributors.model.ContributorsModelItem
import works.anilo.arief.presentation.main.repositories.model.SearchHeaderModelItem

class ApiServiceManager(private val apiService: ApiService) {

    fun emittedContributors(page: Int):Observable<MutableList<ContributorsModelItem>>{
        return apiService.getAllContributors(page)
    }

    fun emittedSearchRepositories(q: String):Observable<SearchHeaderModelItem>{
        return apiService.getSearchRepositories(q)
    }

}