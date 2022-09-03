package ru.geekbrains.popularlibraries.core.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.view.users.UsersFragment
import ru.geekbrains.popularlibraries.view.users.user.UserDetailsFragment
import ru.geekbrains.popularlibraries.view.users.user.UserDetailsFragment.Companion.KEY_USER

object UsersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return UsersFragment.getInstance()
    }
}

data class UserScreen(private val user: GitHubUser) : FragmentScreen {

    override fun createFragment(factory: FragmentFactory): Fragment {
        return UserDetailsFragment.newInstance(Bundle().apply {
            putParcelable(KEY_USER, user)
        })
    }
}