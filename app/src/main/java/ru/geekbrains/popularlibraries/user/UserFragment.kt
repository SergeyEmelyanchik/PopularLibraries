package ru.geekbrains.popularlibraries.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.popularlibraries.CourseApp
import ru.geekbrains.popularlibraries.core.OnBackPressedListener
import ru.geekbrains.popularlibraries.databinding.FragmentUserListBinding
import ru.geekbrains.popularlibraries.main.UserAdapter
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.repository.implementation.GitHubRepositoryImpl

class UserFragment : MvpAppCompatFragment(), UserView, OnBackPressedListener {
    companion object {
        fun getInstance(): UserFragment {
            return UserFragment()
//                .apply {
//                    arguments = Bundle("login" to login)
//                }
        }
    }

    private var _binding: FragmentUserListBinding? = null
    private val binding: FragmentUserListBinding
        get() = _binding!!

    private val adapter = UserAdapter()

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(GitHubRepositoryImpl(), CourseApp.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            rvGitHubUsers.layoutManager = LinearLayoutManager(requireContext())
            rvGitHubUsers.adapter = adapter
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    override fun initList(list: List<GitHubUser>) {
        adapter.users = list
    }

    override fun onBackPressed(): Boolean = presenter.onBackPressed()
}