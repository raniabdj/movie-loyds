package com.example.moviesapp.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.moviesapp.R
import com.example.moviesapp.databinding.FragmentLogInBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class logIn : Fragment() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {



        }
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            println("--------------")
            println(currentUser)
            view?.findNavController()?.navigate(R.id.mainFragment)

        }
    }

    fun Login() {

        activity?.let {
            auth.signInWithEmailAndPassword(
                binding.userEmail.text.toString(),
                binding.userPassword.text.toString()
            ).addOnCompleteListener(it) {
                if (it.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "createUserWithEmail:success")
                    val user = auth.currentUser
                    Toast.makeText(
                        view?.context, "Authentication Create user.",
                        Toast.LENGTH_LONG
                    ).show()
                    println(user)
                    // updateUI(user)
                    view?.findNavController()?.navigate(R.id.mainFragment)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "createUserWithEmail:failure", it.exception)
                    Toast.makeText(
                        view?.context, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                    //updateUI(null)
                }

            }
        }
    }

    private lateinit var binding: FragmentLogInBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLogInBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth

        binding.userLogin.setOnClickListener {
            Login()
        }
        binding.registerLogin.setOnClickListener {
            view.findNavController().navigate(R.id.createAnacc)

        }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment logIn.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            logIn().apply {
                arguments = Bundle().apply {

                }
            }
    }
}