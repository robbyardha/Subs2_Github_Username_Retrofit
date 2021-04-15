package com.ardhacodes.github_retro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ardhacodes.github_retro.databinding.ItemGithubBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private val list = ArrayList<Githubuser>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback)
    {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(users : ArrayList<Githubuser>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(val binding: ItemGithubBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(user: Githubuser)
        {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(user)
            }

            binding.apply {
            Glide.with(itemView)
                    .load(user.avatar_url)
                    .into(avatar)
                tvItemUsername.text = user.login
                tvItemId.text = "Github ID : ${user.id.toString()}"
                tvItemType.text = "Type: ${user.type}"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemGithubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder((view))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


    interface OnItemClickCallback{
        fun onItemClicked(data: Githubuser)
    }
}