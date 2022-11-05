package ru.geekbrains.popularlibraries.core.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.geekbrains.popularlibraries.model.data.ReposDto
import ru.geekbrains.popularlibraries.view.user.UsersFragment
import ru.geekbrains.popularlibraries.view.userdetails.UserDetailsFragment
import ru.geekbrains.popularlibraries.view.userrepository.RepoUserFragment
import ru.geekbrains.popularlibraries.view.userrepository.RepoUserFragment.Companion.KEY_REPO

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

data class RepoScreen(private val repo: ReposDto) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return RepoUserFragment.getInstance(Bundle().apply {
            putParcelable(KEY_REPO,repo)
        })
    }
}