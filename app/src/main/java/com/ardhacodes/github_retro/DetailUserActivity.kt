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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra(EXTRA_USERNAME)


        //bundle fragment
        val bundle = Bundle()
        bundle.putString(EXTRA_USERNAME, username)



        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            DetailUserViewModel::class.java)
        viewModel.setUserDetail(username!!)
        viewModel.getUserDetail().observe(this, {
            if (it != null){
                binding.apply {
                    tvUsername.text = it.login
                    tvNama.text = it.name
                    tvBio.text = it.bio
                    tvCompany.text = "Company : ${it.company}"
                    tvLocation.text = "Location : ${it.location}"
                    tvFollowers.text = "Followers: ${it.followers}"
                    tvFollowing.text = "Following: ${it.following}"
                    tvRepository.text = "Repository: ${it.public_repos}"
                    Glide.with(this@DetailUserActivity)
                            .load(it.avatar_url)
                            .into(circleAvatarDetail)

                    var actionBarTitle = supportActionBar
                    actionBarTitle!!.title = "Detail Of ${it.login}"
                }
            }
        })

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, bundle)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            clicktabs.setupWithViewPager(viewPager)
        }
    }
}