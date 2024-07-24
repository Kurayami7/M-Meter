package com.coderops.ui.tv_show_guessing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.coderops.bases.BaseFragment
import com.coderops.ui.trivia.R
import com.coderops.ui.trivia.databinding.FragmentTvShowGuessingBinding
import com.coderops.viewmodel.common.model.GameUIEvent
import com.coderops.viewmodel.common.model.GameUiState
import com.coderops.viewmodel.tv_show_guessing.TvShowGuessingViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class TvShowGuessingFragment: BaseFragment<FragmentTvShowGuessingBinding, GameUiState, GameUIEvent>() {
    override val layoutIdFragment: Int = R.layout.fragment_tv_show_guessing
    override val viewModel: TvShowGuessingViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.includeItemCardChooseTvShow.listener = viewModel
        initData()
    }

    private fun initData() {
        collectLatest {
            viewModel.state.collectLatest { state ->
                binding.includeItemHeaderGame.item = state
                binding.includeItemCardChooseTvShow.item = state
            }
        }
    }

    override fun onEvent(event: GameUIEvent) {
        when(event){
            GameUIEvent.NavigateToLoserScreen -> {
                showSnackBar("Lossssssssser")
                findNavController().navigate(TvShowGuessingFragmentDirections.actionTvShowGuessingFragmentToTypeGameFragment())
            }
            is GameUIEvent.NavigateToWinnerScreen -> findNavController().navigate(
                TvShowGuessingFragmentDirections.actionTvShowGuessingFragmentToCongratsFragment(event.gameType)
            )
            GameUIEvent.ShowTimeOut -> {showSnackBar(getString(R.string.time_out))
                findNavController().navigate(TvShowGuessingFragmentDirections.actionTvShowGuessingFragmentToTypeGameFragment())
                }
            is GameUIEvent.ShowSnackbar -> showSnackBar(event.message)
            is GameUIEvent.ShowBuyHeartDialog -> showDialog(event.numberOfPoints)
        }
    }

    private fun showDialog(numberOfPoints: Int) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.trivai_lost_title))
            .setMessage(getString(R.string.trivia_losing_meesage, numberOfPoints))
            .setNeutralButton(getString(R.string.trivia_cancel_game)) { _, _ ->
                findNavController().navigate(
                    TvShowGuessingFragmentDirections.actionTvShowGuessingFragmentToTypeGameFragment()
                )
            }
            .setPositiveButton(getString(R.string.trivai_buy_dialog)) { _, _ ->
                viewModel.buyHearts(numberOfPoints)
            }.setCancelable(false)
            .show()
    }
}