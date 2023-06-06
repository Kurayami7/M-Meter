package com.chocolatecake.movieapp.ui.login

import android.content.Intent
import android.graphics.Rect
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.fragment.app.viewModels
import com.chocolatecake.movieapp.BuildConfig
import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.databinding.FragmentLoginBinding
import com.chocolatecake.movieapp.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginUiState, LoginUiEvent>() {
    override val layoutIdFragment = R.layout.fragment_login
    override val viewModel by viewModels<LoginViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleKeyboardAppearanceEvent()
    }

    private fun handleKeyboardAppearanceEvent() {
        with(binding) {

            val globalLayoutListener = ViewTreeObserver.OnGlobalLayoutListener {
                val rect = Rect()
                root.getWindowVisibleDisplayFrame(rect)

                val screenHeight = root.rootView.height
                val keyboardHeight = screenHeight - rect.bottom

                val isKeyboardVisible =
                    keyboardHeight > screenHeight * 0.15

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
            is LoginUiEvent.LoginEvent -> {
                // TODO --> Navigate To Home Screen
            }

            is LoginUiEvent.SignUpEvent -> {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(BuildConfig.TMDB_SIGNUP_URL))
                startActivity(browserIntent)
            }

            is LoginUiEvent.ShowSnackBar -> {
                showSnackBar(requireContext().getString(event.stringId))
            }

        }
    }

    override fun onSateChange(state: LoginUiState) {

    }
}