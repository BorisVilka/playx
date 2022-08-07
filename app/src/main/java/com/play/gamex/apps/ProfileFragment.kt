package com.play.gamex.apps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.play.gamex.apps.databinding.FilledProfileBinding
import com.play.gamex.apps.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        if(FirebaseAuth.getInstance().currentUser!=null) {
            binding.layout.removeAllViews()
            val profileSignedBinding = FilledProfileBinding.inflate(inflater,container,false)
            binding.layout.addView(profileSignedBinding.root)
            profileSignedBinding.exit.setOnClickListener {
                FirebaseAuth.getInstance().signOut()
                val navHostFragment =
                    activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
                val navController = navHostFragment.navController
                navController.popBackStack()
                navController.navigate(R.id.profileFragment)
            }
            profileSignedBinding.email.text = FirebaseAuth.getInstance().currentUser?.email
            //profileSignedBinding.name.text = FirebaseAuth.getInstance().currentUser?.displayName
        } else {
            val emptyProfileBinding = binding.include
            emptyProfileBinding.button2.setOnClickListener {
                val navHostFragment =
                    activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
                val navController = navHostFragment.navController
                navController.navigate(R.id.loginFragment)
            }

        }
        return binding.root
    }


}