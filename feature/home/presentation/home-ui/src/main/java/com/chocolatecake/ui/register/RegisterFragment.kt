package com.chocolatecake.ui.register

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentRegisterBinding
import com.chocolatecake.viewmodel.register.RegistrationUIState
import com.chocolatecake.viewmodel.register.RegistrationUiEvent
import com.chocolatecake.viewmodel.register.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegistrationUIState, RegistrationUiEvent>() {

    override val layoutIdFragment: Int = R.layout.fragment_register
    override val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    handleUIState(state)
                }
            }
        }

        binding.buttonRegister.setOnClickListener {
            viewModel.onClickRegister()
        }
    }

    private fun handleUIState(state: RegistrationUIState) {
        binding.progressBar.isVisible = state.isLoading
        if (state.isSuccess) {
            findNavController().navigate(R.id.action_registerFragment_to_profileFragment)
        } else if (state.error != null) {
            showError(state.error!!)
        }
        showLoading(state.isLoading)
    }

    override fun onEvent(event: RegistrationUiEvent) {
        // Handle any other UI events if needed
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
        // You might want to disable the button while loading
        binding.buttonRegister.isEnabled = !isLoading
    }
}