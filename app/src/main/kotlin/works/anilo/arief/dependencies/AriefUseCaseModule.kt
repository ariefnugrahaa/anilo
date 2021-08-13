package works.anilo.arief.dependencies

import dagger.Module
import dagger.Provides
import works.anilo.arief.data.server.ApiServiceManager
import works.anilo.arief.domain.usecase.GetContributorsUseCase
import works.anilo.arief.domain.usecase.GetSearchUseCase
import javax.inject.Singleton

@Module
class AriefUseCaseModule {

    @Singleton
    @Provides
    fun provieGetUsersUseCase(apiServiceManager: ApiServiceManager) =
            GetContributorsUseCase(apiServiceManager)

    @Singleton
    @Provides
    fun provideGetSearchRepositoriesUseCase(apiServiceManager: ApiServiceManager) =
        GetSearchUseCase(apiServiceManager)

}