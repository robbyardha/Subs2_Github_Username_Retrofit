package com.ardhacodes.github_retro

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ardhacodes.github_retro.databinding.ItemReposBinding
import com.bumptech.glide.Glide

class ReposAdapter : RecyclerView.Adapter<ReposAdapter.ListViewHolder>(){
    private val list = ArrayList<GithubRepos>()

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback)
    {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setList(repos : ArrayList<GithubRepos>){
        list.clear()
        list.addAll(repos)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(val binding: ItemReposBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(repos: GithubRepos)
        {
            binding.root.setOnClickListener {
                onItemClickCallback?.onItemClicked(repos)
            }

            binding.apply {
                tvReposName.text = "Name : ${repos.name}"
                tvReposFullname.text = "Full name : ${repos.full_name}"
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemReposBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder((view))
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size


    interface OnItemClickCallback{
        fun onItemClicked(data: GithubRepos)
    }
}