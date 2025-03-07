package com.coderops.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.coderops.bases.BaseFragment
import com.coderops.bases.ListName
import com.coderops.bases.ListType
import com.coderops.ui.home.R
import com.coderops.ui.home.databinding.FragmentProfileBinding
import com.coderops.viewmodel.profile.ProfileUIState
import com.coderops.viewmodel.profile.ProfileUiEvent
import com.coderops.viewmodel.profile.ProfileViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileUIState, ProfileUiEvent>() {

    override val layoutIdFragment: Int = R.layout.fragment_profile
    override val viewModel: ProfileViewModel by viewModels()

    companion object {
        private const val PREF_THEME_STATE = "theme_state"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeAppTheme()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    // Directly set visibility based on isLoggedIn
                    binding.whenUserLoggedin.visibility = if (state.isLoggedIn) View.VISIBLE else View.GONE
                    binding.whenUserNotLoggedin.visibility = if (state.isLoggedIn) View.GONE else View.VISIBLE
                }
            }
        }
    }

    override fun onEvent(event: ProfileUiEvent) {
        when (event) {
            ProfileUiEvent.NavigateToFavoriteScreen -> {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToMyListDetailsFragment(
                        listType = ListType.movie.name,
                        listId = 0,
                        listName = ListName.favorite.name,
                    )
                )
            }

            ProfileUiEvent.NavigateToWatchlistScreen -> {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToMyListDetailsFragment(
                        listType = ListType.movie.name,
                        listId = 0,
                        listName = ListName.watchlist.name,
                    )
                )
            }

            ProfileUiEvent.NavigateToWatchHistoryScreen -> {
                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToWatchHistoryFragment())
            }

            ProfileUiEvent.NavigateToMyListsScreen -> {
                findNavController().navigate(
                    ProfileFragmentDirections.actionProfileFragmentToMyListFragment()
                )
            }

            ProfileUiEvent.Logout -> {
                showCofirmDialog()
            }

            is ProfileUiEvent.NavigateWithLink -> {
                findNavController().navigate(event.link)
            }
        }
    }

    private fun showCofirmDialog(){
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.logout))
            .setMessage(getString(R.string.do_u_wanna_leave_us))
            .setPositiveButton(getString(R.string.confirm)) { _, _ ->
                viewModel.logout()
            }
            .setNeutralButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }


    private fun changeAppTheme() {
        val sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val switchButtonTheme = binding.switchBottonTheme
        val savedThemeState = sharedPreferences.getBoolean(PREF_THEME_STATE, false)
        switchButtonTheme.isChecked = savedThemeState

        switchButtonTheme.setOnCheckedChangeListener { _, isChecked ->
            sharedPreferences.edit().putBoolean(PREF_THEME_STATE, isChecked).apply()
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}