package com.example.mvvmtraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mvvmtraining.databinding.ActivityMainBinding
import com.example.mvvmtraining.utils.PageAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
       setContentView(binding.root)
        binding.viewPager.adapter = PageAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)

    }




}

