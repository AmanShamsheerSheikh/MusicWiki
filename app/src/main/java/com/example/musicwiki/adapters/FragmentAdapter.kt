package com.example.musicwiki.adapters


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.musicwiki.data.tagInfo.TagInfo


class FragmentAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    var fragmentList : ArrayList<Fragment> = ArrayList()
    var fragmentTitle : ArrayList<String> = ArrayList()
    var tag : String = ""
    override fun getCount(): Int {
       return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        val bundle = Bundle()
        val fragment = fragmentList[position]
        bundle.putString("tag", tag)
        fragment.setArguments(bundle)
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        Log.d("title", "getPageTitle: "+fragmentTitle[position])
        return fragmentTitle[position]
    }

    fun addFragment(fragment: Fragment,title: String){
        fragmentList.add(fragment)
        fragmentTitle.add(title)
    }
    fun setName(name: String){
        tag = name
    }
}