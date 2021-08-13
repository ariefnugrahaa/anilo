package works.anilo.arief.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import works.anilo.arief.R
import works.anilo.arief.presentation.main.adapter.ViewPagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        val tabLayout = tab_layout
        val viewPager = view_pager_2
        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
               0 -> {
                   tab.text = "Contributors"
               }
                1 -> {
                    tab.text = "Repositories"
                }
            }
        }.attach()
    }
}
