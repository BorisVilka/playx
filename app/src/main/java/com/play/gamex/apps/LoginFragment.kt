package com.play.gamex.apps

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.play.gamex.apps.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        binding.emailLogin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.emailLogin.error = null
            }
        })
        binding.passwordLogin.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.passwordLogin.error = null
            }
        })
        binding.button.setOnClickListener(View.OnClickListener {
            if(binding.emailLogin.text.toString().isEmpty()) {
                binding.emailLogin.error = "Empty email"
            } else if(binding.passwordLogin.text.toString().isEmpty()) {
                binding.passwordLogin.error = "Password empty"
            } else {
                FirebaseAuth
                    .getInstance()
                    .signInWithEmailAndPassword(
                        binding.emailLogin.text.toString().trim(),
                        binding.passwordLogin.text.toString().toString()
                    )
                    .addOnCompleteListener(OnCompleteListener {
                            if(it.isSuccessful) {
                                val navHostFragment =
                                    activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
                                val navController = navHostFragment.navController
                                FirebaseAuth.getInstance().updateCurrentUser(it.result.user!!)
                                navController.popBackStack()
                            }
                        }
                    )
                }
            }
        )
        binding.button3.setOnClickListener(View.OnClickListener {
            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController = navHostFragment.navController
            navController.navigate(R.id.registrationFragment)
            }
        )
        return binding.root
    }


}