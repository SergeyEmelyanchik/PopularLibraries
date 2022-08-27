package ru.geekbrains.popularlibraries.view.users.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.popularlibraries.CourseApp
import ru.geekbrains.popularlibraries.core.OnBackPressedListener
import ru.geekbrains.popularlibraries.databinding.FragmentUserDetailsBinding
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.presenter.UserDetailsPresenter

class UserDetailsFragment() : MvpAppCompatFragment(), OnBackPressedListener, UserDetailsView {

    private var _binding: FragmentUserDetailsBinding? = null
    private val binding: FragmentUserDetailsBinding
        get() = _binding!!

    private val presenter: UserDetailsPresenter by moxyPresenter {
        UserDetailsPresenter(CourseApp.instance.router)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        renderUserDetail(arguments?.getParcelable<GitHubUser>("test"))
    }

    private fun renderUserDetail(githubUser: GitHubUser?) {
        githubUser?.let {
            binding.tvDetailsUserLogin.text = githubUser.login
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(gitHubUser: GitHubUser) = UserDetailsFragment().apply {
            arguments = Bundle().apply {
                putParcelable("test", gitHubUser)
            }
        }
    }

    override fun onBackPressed(): Boolean = presenter.onBackPressed()

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}