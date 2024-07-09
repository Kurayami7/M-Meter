package com.chocolatecake.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentRegisterBinding
import com.chocolatecake.usecase.LoginError
import com.chocolatecake.usecase.LoginUseCase
import com.chocolatecake.viewmodel.register.RegistrationUIState
import com.chocolatecake.viewmodel.register.RegistrationUiEvent
import com.chocolatecake.viewmodel.register.RegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegistrationViewModel by viewModels()

    @Inject
    lateinit var loginUseCase: LoginUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    if (state.registrationSuccess) {
                        // Extract email and password used for registration
                        val email = viewModel.email.value
                        val password = viewModel.password.value

                        // Trigger the login functionality using LoginUseCase
                        launch {
                            val loginResult = loginUseCase(email, password)
                            when (loginResult) {
                                LoginError.SUCCESS -> {
                                    // Navigate to the home screen
                                    findNavController().navigate(R.id.action_registerFragment_to_homeFragment)
                                }
                                // Handle other login errors as needed
                                else -> {
                                    // Display error message or handle the error appropriately
                                    Toast.makeText(requireContext(), "Login failed after registration", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}