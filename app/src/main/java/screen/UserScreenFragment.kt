package screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.popularlibraries.core.App
import ru.geekbrains.popularlibraries.core.OnBackPressedListener
import ru.geekbrains.popularlibraries.databinding.FragmentUserScreenBinding
import ru.geekbrains.popularlibraries.presenter.UserDetailsPresenter

class UserScreenFragment : MvpAppCompatFragment(), UserScreenView, OnBackPressedListener {

    private val presenter: UserDetailsPresenter by moxyPresenter {
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

    override fun onBackPressed() = presenter.onBackPressed()

    override fun setUser(login: String?) {
        binding.userName.text = login
    }
}