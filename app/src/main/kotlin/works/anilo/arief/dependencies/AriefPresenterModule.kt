package works.anilo.arief.dependencies

import dagger.Module
import dagger.Provides
import works.anilo.arief.domain.usecase.GetContributorsUseCase
import works.anilo.arief.domain.usecase.GetSearchUseCase
import works.anilo.arief.presentation.main.contributors.ContributorsPresenter
import works.anilo.arief.presentation.main.repositories.SearchRepositoriesPresenter
import javax.inject.Singleton

@Module
class AriefPresenterModule {

    @Singleton
    @Provides
    fun provideUserPresenter(getUsersUseCase: GetContributorsUseCase) =
            ContributorsPresenter(getUsersUseCase)

    @Singleton
    @Provides
    fun provideSearchRepositoriesPresenter(getSearchRepositoriesUseCase: GetSearchUseCase) =
        SearchRepositoriesPresenter(getSearchRepositoriesUseCase)
}