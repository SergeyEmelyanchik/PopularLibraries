package ru.geekbrains.popularlibraries.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.geekbrains.popularlibraries.R
import ru.geekbrains.popularlibraries.model.GitHubUser
import ru.geekbrains.popularlibraries.view.users.OnItemClickListener

class UserAdapter(private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<UserAdapter.GitHubUserViewHolder>() {

    var users: List<GitHubUser> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return GitHubUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: GitHubUserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    inner class GitHubUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val tvLogin by lazy { itemView.findViewById<TextView>(R.id.tvUserLogin) }
        fun bind(item: GitHubUser) = with(item) {
            tvLogin.text = login
            itemView.setOnClickListener { onItemClickListener.onItemClick(item) }
        }
    }
}
