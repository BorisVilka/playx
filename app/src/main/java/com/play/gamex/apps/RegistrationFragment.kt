package com.play.gamex.apps

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.play.gamex.apps.databinding.FragmentRegistrationBinding

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater,container,false)
        binding.emailReg.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.emailReg.error = null
            }
        })
        binding.passwordReg.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.passwordReg.error = null
            }
        })
        binding.button3.setOnClickListener(View.OnClickListener {
            if(binding.emailReg.text.toString().isEmpty()) {
                binding.emailReg.error = "Empty email"
            } else if(binding.passwordReg.text.toString().isEmpty()) {
                binding.passwordReg.error = "Password empty"
            } else {
                FirebaseAuth
                    .getInstance()
                    .createUserWithEmailAndPassword(
                        binding.emailReg.text.toString().trim(),
                        binding.passwordReg.text.toString().trim()
                    )
                    .addOnCompleteListener(OnCompleteListener {
                        if(it.isSuccessful) {
                            val navHostFragment =
                                activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
                            val navController = navHostFragment.navController
                            FirebaseAuth.getInstance().updateCurrentUser(it.result.user!!)
                            navController.popBackStack()
                            navController.popBackStack()
                        }
                    }
                )
            }
        }
        )
        return binding.root
    }


}