package com.chocolatecake.ui.memorize_game

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.ui.trivia.databinding.FragmentMemorizeBinding
import com.chocolatecake.viewmodel.memorize_game.MemorizeGameUIEvent
import com.chocolatecake.viewmodel.memorize_game.MemorizeGameUIState
import com.chocolatecake.viewmodel.memorize_game.MemorizeGameViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MemorizeGameFragment :
    BaseFragment<FragmentMemorizeBinding, MemorizeGameUIState, MemorizeGameUIEvent>() {

    override val layoutIdFragment: Int = R.layout.fragment_memorize
    override val viewModel: MemorizeGameViewModel by viewModels()
    private lateinit var memorizeGameAdapter: MemorizeGameAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("123123123", "onViewCreated: $123123123")
        memorizeGameAdapter = MemorizeGameAdapter(mutableListOf(), viewModel)
        binding.recyclerViewMemorizeGame.adapter = memorizeGameAdapter
        collectLatest {
            viewModel.state.collect {
                Log.d("123123123", "onViewCreated: $it")
                memorizeGameAdapter.setItems(it.board)
            }
        }
    }

    override fun onEvent(event: MemorizeGameUIEvent) {
        when (event) {
            MemorizeGameUIEvent.NavigateToLoserScreen -> {
                findNavController().navigate(MemorizeGameFragmentDirections.actionMemorizeGameFragmentToTypeGameFragment())
            }

            MemorizeGameUIEvent.NavigateToWelcomeGameScreen -> {
                findNavController().navigate(MemorizeGameFragmentDirections.actionMemorizeGameFragmentToHomeGameFragment())
            }

            is MemorizeGameUIEvent.NavigateToWinnerScreen -> {
                findNavController().navigate(
                    MemorizeGameFragmentDirections.actionMemorizeGameFragmentToCongratsFragment(
                        event.gameType
                    )
                )
            }

            is MemorizeGameUIEvent.ShowSnackbar -> showSnackBar(event.message)
        }
    }
}