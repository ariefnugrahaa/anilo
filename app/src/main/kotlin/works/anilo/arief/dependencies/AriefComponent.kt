package works.anilo.arief.dependencies

import android.content.Context
import dagger.Component
import works.anilo.arief.data.server.ApiServiceManager
import works.anilo.arief.presentation.main.contributors.ContributorsPresenter
import works.anilo.arief.presentation.main.repositories.SearchRepositoriesPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AriefApplicationModule::class,
    AriefNetworkModule::class,
    AriefMapperModule::class,
    AriefPresenterModule::class,
    AriefUseCaseModule::class,
    AriefServiceModule::class
])

interface AriefComponent {
    fun provideApplicationContext(): Context
    fun provideApiServiceManager(): ApiServiceManager
    fun provideContributorsPresenter(): ContributorsPresenter
    fun provideSearchRepositoriesPresenter():SearchRepositoriesPresenter
}