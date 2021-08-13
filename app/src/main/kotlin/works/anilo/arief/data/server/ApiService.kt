package works.anilo.arief.data.server

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import works.anilo.arief.presentation.main.contributors.model.ContributorsModelItem
import works.anilo.arief.presentation.main.repositories.model.SearchHeaderModelItem

interface ApiService {

    @GET(CONTRIBUTORS)
    fun getAllContributors(@Query("since") page:Int):Observable<MutableList<ContributorsModelItem>>

    @GET(SEARCH)
    fun getSearchRepositories(@Query("q") q:String):Observable<SearchHeaderModelItem>

    private companion object {
        const val  CONTRIBUTORS = "repos/jquery/jquery/contributors"
        const val  SEARCH = "search/repositories"
    }
}