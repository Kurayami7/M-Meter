package com.chocolatecake.ui

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.auth.BuildConfig
import com.chocolatecake.ui.auth.R
import com.chocolatecake.ui.auth.databinding.FragmentLoginBinding
import com.chocolatecake.viewmodel.LoginUiEvent
import com.chocolatecake.viewmodel.LoginUiState
import com.chocolatecake.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginUiState, LoginUiEvent>() {
    private lateinit var auth: FirebaseAuth
    override val layoutIdFragment = R.layout.fragment_login
    override val viewModel by viewModels<LoginViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        handleKeyboardAppearanceEvent()
    }

    private fun handleKeyboardAppearanceEvent() {
        with(binding) {
            val globalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
                val rect = Rect()
                root.getWindowVisibleDisplayFrame(rect)

                val screenHeight = root.rootView.height
                val keyboardHeight = screenHeight - rect.bottom

                val isKeyboardVisible = keyboardHeight > screenHeight * 0.15
                handleKeyboardAppearanceEvent(isKeyboardVisible)
            }
            root.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
        }
    }

    private fun handleKeyboardAppearanceEvent(isVisible: Boolean) {
        with(binding.loginMotionLayout) {
            setTransitionDuration(300)
            if (isVisible) {
                transitionToEnd()
            } else {
                transitionToStart()
            }
        }
    }

    override fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.NavigateToHomeScreen -> {
                Log.d("LoginFragment", "Navigating to home screen")
                val navController = findNavController()
                navController.popBackStack(navController.graph.startDestinationId, false)
                navController.navigate(event.id)

                val firebaseUser = auth.currentUser
                if (firebaseUser != null) {
                    // TODO: Display user information (e.g., in a TextView you'll add to the layout)
                    // Example: binding.userNameTextView.text = firebaseUser.displayName ?: firebaseUser.email

                    // TODO: Add a logout button to your layout and handle its click
                    // Example:
                    // binding.logoutButton.setOnClickListener {
                    //     auth.signOut()
                    //     // Handle UI changes after logout (e.g., hide user info, show login button)
                    // }
                }
            }

            is LoginUiEvent.SignUpEvent -> {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.TMDB_SIGNUP_URL))
                startActivity(browserIntent)
            }

            is LoginUiEvent.ShowSnackBar -> {
                val keyboard = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                keyboard.hideSoftInputFromWindow(view?.windowToken, 0)
                showSnackBar(event.message)
            }
        }
    }
}