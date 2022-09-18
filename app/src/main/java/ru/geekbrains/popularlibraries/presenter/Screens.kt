package ru.geekbrains.popularlibraries.core.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.popularlibraries.view.user.UsersFragment
import ru.geekbrains.popularlibraries.view.userdetails.UserDetailsFragment

object UsersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UsersFragment.getInstance()
    }
}

data class UserScreen(private val userLogin: String) : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserDetailsFragment.newInstance(userLogin)
    }
}