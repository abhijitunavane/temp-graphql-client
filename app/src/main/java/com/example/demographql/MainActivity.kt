package com.example.demographql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.demographql.ui.UsersFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager: FragmentManager = supportFragmentManager
        val fragment: Fragment? = fragmentManager.findFragmentById(R.id.users_fragment_container)
        if (fragment == null) {
            val fragmentFactory = fragmentManager.fragmentFactory
            val userFragment =
                fragmentFactory.instantiate(classLoader, UsersFragment::class.java.name.toString())
            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.add(R.id.users_fragment_container, userFragment)
            fragmentTransaction.commit()
        }
    }
}
