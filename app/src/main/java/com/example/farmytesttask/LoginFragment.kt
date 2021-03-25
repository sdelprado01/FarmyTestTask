package com.example.farmytesttask

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_login, container, false)

        val emailEditText = root.findViewById<View>(R.id.editTextTextEmailAddress) as EditText
        val passwordEditText = root.findViewById<View>(R.id.editTextTextPassword) as EditText
        val btnCreateAccount = root.findViewById<View>(R.id.buttonCreateAccount)
        val btnLogin = root.findViewById<View>(R.id.buttonLogin)

        btnLogin.setOnClickListener {
            when {
                TextUtils.isEmpty(emailEditText.text.trim { it <= ' ' }) -> {
                    Toast.makeText(this.context, "Please enter a valid email", Toast.LENGTH_SHORT)
                        .show()
                }

                TextUtils.isEmpty(passwordEditText.text.trim { it <= ' ' }) -> {
                    Toast.makeText(
                        this.context,
                        "Please enter a valid password",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else->{
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(), passwordEditText.text.toString()).addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            Toast.makeText(this.context, "You are logged in succesfully", Toast.LENGTH_SHORT)
                                .show()
                            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_mainFragment)
                        } else {
                            Toast.makeText(this.context, "Login not succesful try again or register to login", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }

        btnCreateAccount.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_registerFragment)
        }


        return root
    }

}