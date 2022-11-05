package ru.geekbrains.popularlibraries.view.userdetails

import android.annotation.SuppressLint
import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.popularlibraries.core.App
import ru.geekbrains.popularlibraries.databinding.FragmentUserScreenBinding
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.model.repository.GitHubRepositoryImpl
import ru.geekbrains.popularlibraries.model.network.NetworkProvider
import ru.geekbrains.popularlibraries.presenter.UserDetailsPresenter
import ru.geekbrains.popularlibraries.utils.AndroidNetworkStatus
import ru.geekbrains.popularlibraries.utils.hide
import ru.geekbrains.popularlibraries.utils.loadGlide
import ru.geekbrains.popularlibraries.utils.show
import ru.geekbrains.popularlibraries.view.main.OnBackPressedListener

class UserDetailsFragment : MvpAppCompatFragment(), UserDetailsView, OnBackPressedListener {

    private val reposAdapter = ReposAdapter {
        presenter.openRepoScreen(it)

    }


    private val presenter: UserDetailsPresenter by moxyPresenter {
        UserDetailsPresenter().apply {
            App.instance.appComponent.inject(this)
        }
    }

    private var binding: FragmentUserScreenBinding? = null

    companion object {
        const val KEY_USER = "KEY_USER"
        fun newInstance(userLogin: String) = UserDetailsFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_USER, userLogin)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentUserScreenBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getString(KEY_USER)?.let {
            presenter.loadUser(it)
        }
        binding?.rvGitHubUserRepos?.adapter = reposAdapter
        binding?.rvGitHubUserRepos?.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onBackPressed() = presenter.onBackPressed()

    @SuppressLint("SetTextI18n")
    override fun showUser(user: GitHubUser) {
        TransitionManager.beginDelayedTransition(binding?.root)
        binding?.userName?.text = user.login
        binding?.ivUserAvatar?.loadGlide(user.avatarUrl)
        binding?.userRepos?.text = "Repo:" + user.repos?.size.toString()
        user.repos?.let {
            reposAdapter.repos = it
        }
    }

    override fun showLoading() {
        TransitionManager.beginDelayedTransition(binding?.root)
        binding?.apply {
            progressBar.show()
            userName.hide()
            ivUserAvatar.hide()
            rvGitHubUserRepos.hide()
            userRepos.hide()
        }
    }

    override fun hideLoading() {
        binding?.apply {
            progressBar.hide()
            userName.show()
            ivUserAvatar.show()
            rvGitHubUserRepos.show()
            userRepos.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}