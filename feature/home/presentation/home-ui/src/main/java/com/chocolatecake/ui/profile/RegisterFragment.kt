/*
package com.chocolatecake.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentRegisterBinding // Update binding import
import com.chocolatecake.viewmodel.register.RegistrationUIState
import com.chocolatecake.viewmodel.register.RegistrationUiEvent
import com.chocolatecake.viewmodel.register.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import com.chocolatecake.viewmodel.register.RegistrationViewModel

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegistrationUIState, RegistrationUiEvent>() {

    override val layoutIdFragment: Int = R.layout.fragment_register
    override val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe registration state and handle UI updates
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    if (state.isSuccess) {
                        // Registration successful, navigate to login screen
                        findNavController().navigate(R.id.action_registerFragment_to_profileFragment)
                    } else if (state.error != null) {
                        // Display error message (using a Snackbar or Toast)
                        showError(state.error)
                    }
                    // Update UI based on loading state (show/hide progress bar)
                    showLoading(state.isLoading)
                }
            }
        }

        // Set click listener for the "Register" button
        binding.buttonRegister.setOnClickListener {
            viewModel.onClickRegister()
        }
    }

    override fun onEvent(event: RegistrationUiEvent) {
        // Handle any other UI events if needed
    }

    private fun showError(errorMessage: String) {
        // Implement your error display logic here (Snackbar, Toast, etc.)
    }

    private fun showLoading(isLoading: Boolean) {
        // Implement your loading state UI logic here (show/hide progress bar)
    }
}*/
