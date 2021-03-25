package com.example.farmytesttask

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import org.w3c.dom.Text


class RegisterFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_register, container, false)

        val emailEditText = root.findViewById<View>(R.id.editTextTextEmailAddressRegister) as EditText
        val passwordEditText = root.findViewById<View>(R.id.editTextTextPasswordRegister) as EditText

        val btnRegister = root.findViewById<View>(R.id.buttonRegister)
        btnRegister.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.text.toString(), passwordEditText.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        Toast.makeText(this.context, "Register was successful", Toast.LENGTH_SHORT)
                            .show()
                        NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_loginFragment)
                    }else{
                        Toast.makeText(this.context, "Register not successful", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } else {
                Toast.makeText(this.context, "Insert your email and password to preceed with the register", Toast.LENGTH_SHORT).show()
            }
        }

        val btnLogin = root.findViewById<View>(R.id.buttonLoginRegister)
        btnLogin.setOnClickListener {
            NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return root

    }

}