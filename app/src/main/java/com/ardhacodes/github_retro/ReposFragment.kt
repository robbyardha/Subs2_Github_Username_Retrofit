package com.ardhacodes.github_retro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ardhacodes.github_retro.databinding.FragmentFollowersBinding
import com.ardhacodes.github_retro.databinding.FragmentRepositoryBinding

class ReposFragment : Fragment(R.layout.fragment_repository){
    private var _binding : FragmentRepositoryBinding? = null
    private val binding get() = _binding!!

    //declaration view model
    private lateinit var viewModel: ReposVM
    private lateinit var adapter: ReposAdapter
    private lateinit var username: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //arguments declaration
        val arguments = arguments
        username = arguments?.getString(DetailUserActivity.EXTRA_USERNAME).toString()


        _binding = FragmentRepositoryBinding.bind(view)


        adapter = ReposAdapter()
        adapter.notifyDataSetChanged()
        binding.apply {
            rvGithubrepos.setHasFixedSize(true)
            rvGithubrepos.layoutManager = LinearLayoutManager(activity)
            rvGithubrepos.adapter = adapter
        }
        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ReposVM::class.java)
        viewModel.setRepos(username)
        viewModel.getRepos().observe(viewLifecycleOwner, {
            if (it != null){
                adapter.setList(it)
                showLoading(false)
            }
        })

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_repository, container, false)
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}