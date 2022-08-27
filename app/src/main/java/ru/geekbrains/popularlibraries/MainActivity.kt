package ru.geekbrains.popularlibraries

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.geekbrains.popularlibraries.databinding.ActivityMainBinding
import ru.geekbrains.popularlibraries.main.UserAdapter
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.repository.implementation.GitHubRepositoryImpl
import ru.geekbrains.popularlibraries.utils.DEFAULT_VALUE_ONE
import ru.geekbrains.popularlibraries.utils.DEFAULT_VALUE_TWO
import ru.geekbrains.popularlibraries.utils.DEFAULT_VALUE_ZERO

class MainActivity : MvpAppCompatActivity(), MainView {

    private lateinit var binding: ActivityMainBinding

    private val adapter = UserAdapter()

    private val presenter by moxyPresenter { CountersPresenter(GitHubRepositoryImpl()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
    }

    private fun initRecyclerView() {

        with(binding) {
            rvGitHubUsers.layoutManager = LinearLayoutManager(this@MainActivity)
            rvGitHubUsers.adapter = adapter
        }
    }

    override fun initList(list: List<GitHubUser>) {
        adapter.users = list
    }
}