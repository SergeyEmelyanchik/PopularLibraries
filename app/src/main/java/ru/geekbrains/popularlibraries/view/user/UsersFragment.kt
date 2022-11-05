package ru.geekbrains.popularlibraries.view.user

import android.os.Bundle
import android.transition.TransitionManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.popularlibraries.core.App
import ru.geekbrains.popularlibraries.view.main.OnBackPressedListener
import ru.geekbrains.popularlibraries.databinding.FragmentUserListBinding
import ru.geekbrains.popularlibraries.main.UserAdapter
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.model.repository.GitHubRepositoryImpl
import ru.geekbrains.popularlibraries.model.network.NetworkProvider
import ru.geekbrains.popularlibraries.presenter.UsersPresenter
import ru.geekbrains.popularlibraries.utils.AndroidNetworkStatus
import ru.geekbrains.popularlibraries.utils.hide
import ru.geekbrains.popularlibraries.utils.show

class UsersFragment : MvpAppCompatFragment(), UserView, OnBackPressedListener {

    private val adapter = UserAdapter {
        presenter.openUserScreen(it)
    }


    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter()
    }

    //   private val listener = object : ItemClickListener {
    //       override fun onUserClick(user: GitHubUser) {
    //           detailsPresenter.openUserScreen(user)
    //       }
    //   }

    private lateinit var binding: FragmentUserListBinding

    companion object {
        fun getInstance(): UsersFragment {
            return UsersFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentUserListBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // adapter.setOnUserClickListener(listener)
        binding.rvGitHubUsers.adapter = adapter
        binding.rvGitHubUsers.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun initList(list: List<GitHubUser>) {
        TransitionManager.beginDelayedTransition(binding.root)
        adapter.users = list
    }

    override fun showLoading() {
        binding.progressBarList.show()
    }

    override fun hideLoading() {
        binding.progressBarList.hide()
    }

    override fun errorGetUser(message: String?) {
        Log.d("TAG", "errorGetUser() called with: message = $message")
    }


    override fun onBackPressed() = presenter.onBackPressed()

}