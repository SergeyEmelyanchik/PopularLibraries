package ru.geekbrains.popularlibraries.main

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.geekbrains.popularlibraries.CourseApp
import ru.geekbrains.popularlibraries.R
import ru.geekbrains.popularlibraries.core.OnBackPressedListener
import ru.geekbrains.popularlibraries.databinding.ActivityMainBinding

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private val navigator = AppNavigator(this, R.id.containerMain)

    private val presenter by moxyPresenter { MainPresenter(CourseApp.instance.router) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        CourseApp.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        CourseApp.instance.navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach() { currentFragment ->
            if (currentFragment is OnBackPressedListener && currentFragment.onBackPressed()) {
                return
            }
        }
        presenter.onBackPressed()
    }
}