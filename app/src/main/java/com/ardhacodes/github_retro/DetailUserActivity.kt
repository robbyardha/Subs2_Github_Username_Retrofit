package com.ardhacodes.github_retro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ardhacodes.github_retro.databinding.ActivityDetailUserBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions

class DetailUserActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_USERNAME =  "extra_username"
    }

    private lateinit var binding: ActivityDetailUserBinding

    private lateinit var viewModel: DetailUserViewModel
    var bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailGithub()
        configurationOfViewPager()
    }

    private fun detailGithub()
    {
        //IntentStringExtra
        val username = intent.getStringExtra(EXTRA_USERNAME)
        //bundle fragment
        bundle.putString(EXTRA_USERNAME, username)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)
        if (username == null){

        }else{
            viewModel.setUserDetail(username)
        }
//        viewModel.setUserDetail(username!!)
        viewModel.getUserDetail().observe(this, {
            if (it != null){
                //Declaration Binding get XML
                val bindLogin = binding.tvUsername
                val bindNama = binding.tvNama
                val bindBio = binding.tvBio
                val bindCompany = binding.tvCompany
                val bindLocation = binding.tvLocation
                val bindFollowers= binding.tvFollowers
                val bindFollowings = binding.tvFollowing
                val bindRepository = binding.tvRepository
                val bindAvatarGithub = binding.circleAvatarDetail

//                Load data
                bindLogin.text = it.login
                bindNama.text = it.name
                bindBio.text = it.bio
                bindCompany.text = "Company : ${it.company}"
                bindLocation.text = "Location : ${it.location}"
                bindFollowers.text = "Followers: ${it.followers}"
                bindFollowings.text = "Following: ${it.following}"
                bindRepository.text ="Repository: ${it.public_repos}"
                Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .into(bindAvatarGithub)

                //LoadAction Bar name
                var actionBarTitle = supportActionBar
                actionBarTitle!!.title = "Detail Of ${it.login}"

            }
        })
    }

    private fun configurationOfViewPager()
    {
        var sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.viewPager.adapter = sectionPagerAdapter
        var bindClickTabs = binding.clicktabs
        bindClickTabs.setupWithViewPager(binding.viewPager)
    }
}