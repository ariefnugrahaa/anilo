package works.anilo.arief.presentation.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import works.anilo.arief.presentation.main.contributors.ContributorsFragment
import works.anilo.arief.presentation.main.repositories.RepositoriesFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(p0: Int): Fragment {
        return when (p0) {
            0 -> {
                ContributorsFragment()
            }
            1 -> {
                RepositoriesFragment()
            }
            else -> ContributorsFragment()
        }
    }
}