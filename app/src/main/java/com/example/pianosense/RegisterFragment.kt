package com.example.pianosense

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class RegisterFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Firebase Authentication başlat
        auth = FirebaseAuth.getInstance()

        val fullNameEditText = view.findViewById<EditText>(R.id.fullNameEditText)
        val emailEditText = view.findViewById<EditText>(R.id.emailEditText)
        val phoneEditText = view.findViewById<EditText>(R.id.phoneEditText)
        val passwordEditText = view.findViewById<EditText>(R.id.passwordEditText)
        val termsCheckBox = view.findViewById<CheckBox>(R.id.termsCheckBox)
        val registerButton = view.findViewById<Button>(R.id.registerButton)

        // Kayıt işlemi
        registerButton.setOnClickListener {
            val fullName = fullNameEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty() && termsCheckBox.isChecked) {
                registerUser(email, password)
            } else {
                Toast.makeText(requireContext(), "Please fill all fields and agree to terms", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Kullanıcı kaydı ve doğrulama e-postası gönderme
    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Doğrulama e-postası gönder
                    auth.currentUser?.sendEmailVerification()
                        ?.addOnCompleteListener { verifyTask ->
                            if (verifyTask.isSuccessful) {
                                Toast.makeText(requireContext(), "Registration successful! Please check your email for verification.", Toast.LENGTH_LONG).show()
                                // Kayıt işlemi başarılı olduğunda LoginFragment'e dön
                                parentFragmentManager.beginTransaction()
                                    .replace(R.id.fragment_container, LoginFragment())
                                    .commit()
                            } else {
                                Toast.makeText(requireContext(), "Failed to send verification email.", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    when (task.exception) {
                        is FirebaseAuthUserCollisionException -> {
                            Toast.makeText(requireContext(), "User already exists!", Toast.LENGTH_SHORT).show()
                        }
                        is FirebaseAuthInvalidCredentialsException -> {
                            Toast.makeText(requireContext(), "Invalid email format!", Toast.LENGTH_SHORT).show()
                        }
                        else -> {
                            Toast.makeText(requireContext(), "Registration failed!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
    }
}
