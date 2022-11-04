package ru.geekbrains.popularlibraries.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.popularlibraries.databinding.ItemUserBinding
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.utils.loadGlide

typealias OnUserClickListener = (login: String) -> Unit

class UserAdapter(
    private val onUserClickListener: OnUserClickListener,
) : RecyclerView.Adapter<GitHubUserViewHolder>() {

//    private lateinit var userClick: ItemClickListener
//
//    fun setOnUserClickListener(listener: ItemClickListener) {
//        userClick = listener
//    }

    var users: List<GitHubUser> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubUserViewHolder {
        return GitHubUserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false),
            onUserClickListener
        )
    }

    override fun onBindViewHolder(holder: GitHubUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size
}

class GitHubUserViewHolder(
    private val binding: ItemUserBinding,
    private val onUserClickListener: OnUserClickListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: GitHubUser) = with(binding) {

        tvUserLogin.text = item.login

        ivUserAvatar.loadGlide(item.avatarUrl)

        root.setOnClickListener {
            onUserClickListener.invoke(item.login)
        }
    }
}