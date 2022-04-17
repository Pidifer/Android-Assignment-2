package com.example.mvvmtraining.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.mvvmtraining.view.ClassicViewFragment
import com.example.mvvmtraining.view.PopViewFragment
import com.example.mvvmtraining.view.RockViewFragment

class PageAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3;
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return PopViewFragment()
            }
            1 -> {
                return RockViewFragment()
            }
            2 -> {
                return ClassicViewFragment()
            }
            else -> {
                return PopViewFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Pop"
            }
            1 -> {
                return "Rock"
            }
            2 -> {
                return "Classic"
            }
        }
        return super.getPageTitle(position)
    }

}