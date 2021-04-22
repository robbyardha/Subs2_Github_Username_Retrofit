package com.ardhacodes.github_retro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ardhacodes.github_retro.databinding.FragmentFollowersBinding
import com.ardhacodes.github_retro.databinding.FragmentFollowingBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FollowingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FollowingFragment : Fragment(R.layout.fragment_following) {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//    private var _binding : FragmentFollowersBinding? = null
    private var _binding : FragmentFollowingBinding? = null
    private val binding get() = _binding!!

    //declaration view model
    private lateinit var viewModel: FollowingVM
    private lateinit var adapter: UserAdapter
    private lateinit var username: String
    private var arrList: ArrayList<Githubuser> = ArrayList()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //arguments declaration
        val arguments = arguments
        username = arguments?.getString(DetailUserActivity.EXTRA_USERNAME).toString()


        _binding = FragmentFollowingBinding.bind(view)
//        _binding = FragmentFollowingBinding.bind(view)


        adapter = UserAdapter(arrList)
        adapter.notifyDataSetChanged()

        configRv()
        showLoading(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowingVM::class.java)
        viewModel.setListFollowing(username)
        viewModel.getListFollowing().observe(viewLifecycleOwner, {
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
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun configRv()
    {
        binding.rvGithubuser.setHasFixedSize(true)
        binding.rvGithubuser.layoutManager = LinearLayoutManager(activity)
        binding.rvGithubuser.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}