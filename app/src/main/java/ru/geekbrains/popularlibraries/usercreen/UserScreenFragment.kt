package ru.geekbrains.popularlibraries.usercreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.popularlibraries.core.App
import ru.geekbrains.popularlibraries.databinding.FragmentUserScreenBinding
import ru.geekbrains.popularlibraries.presenter.UserDetailsPresenter
import ru.geekbrains.popularlibraries.view.OnBackPressedListener
import ru.geekbrains.popularlibraries.view.userdetails.UserScreenView

i

class UserScreenFragment : MvpAppCompatFragment(), UserScreenView, OnBackPressedListener {

    private val detailsPresenter: UserDetailsPresenter by moxyPresenter {
        UserDetailsPresenter(App.instance.router, arguments?.getParcelable(KEY_USER))
    }

    private lateinit var binding: FragmentUserScreenBinding

    companion object {
        const val KEY_USER = "KEY_USER"
        fun newInstance(bundle: Bundle) = UserScreenFragment().apply { arguments = bundle }
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

    override fun onBackPressed() = detailsPresenter.onBackPressed()

    override fun setUser(login: String?) {
        binding.userName.text = login
    }
}