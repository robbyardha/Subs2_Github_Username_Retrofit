package com.ardhacodes.github_retro

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class SectionPagerAdapter(private val mContext: Context, fragManager : FragmentManager, data:Bundle) : FragmentPagerAdapter(fragManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragmentBundles: Bundle

    init {
        fragmentBundles = data
    }


    @StringRes
    private val TAB_TITLE = intArrayOf(R.string.tab1, R.string.tab2, R.string.tab3)


    override fun getCount(): Int = 3
    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
            2 -> fragment = ReposFragment()
        }
        //passing arguments
        fragment?.arguments = this.fragmentBundles

        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TAB_TITLE[position])
    }
}