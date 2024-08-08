package com.example.myimagefinder.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.myimagefinder.Fragment.ImageFragment
import com.example.myimagefinder.Fragment.MypickFragment
import com.example.myimagefinder.R
import com.example.myimagefinder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            btnSearch.setOnClickListener {
                setFragment(ImageFragment())
            }
            btnMypick.setOnClickListener {
                setFragment(MypickFragment())
            }
        }
        setFragment(ImageFragment())
    }

    private fun setFragment(frag: Fragment) {
        supportFragmentManager.commit {
            replace(R.id.frameLayout, frag)
            setReorderingAllowed(true)
            addToBackStack("")
        }
    }

}
