package com.coderops.ui.showMore

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.coderops.bases.BaseFragment
import com.coderops.ui.home.R
import com.coderops.ui.home.databinding.FragmentShowMoreBinding
import com.coderops.viewmodel.showmore.ShowMoreType
import com.coderops.viewmodel.showmore.ShowMoreUiEvent
import com.coderops.viewmodel.showmore.ShowMoreUiState
import com.coderops.viewmodel.showmore.ShowMoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ShowMoreFragment : BaseFragment<FragmentShowMoreBinding, ShowMoreUiState, ShowMoreUiEvent>() {

    override val layoutIdFragment: Int = R.layout.fragment_show_more
    override val viewModel: ShowMoreViewModel by viewModels()
    private val showMoreAdapter by lazy { ShowMoreAdapter(viewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
    }

    private fun setAdapter() {

        binding.recyclerMedia.adapter = showMoreAdapter

        collectLatest {
            viewModel.state.collectLatest { state ->
                val flow = when (state.showMoreType) {
                    ShowMoreType.POPULAR_MOVIES -> state.showMorePopularMovies
                    ShowMoreType.TOP_RATED -> state.showMoreTopRated
                    ShowMoreType.TRENDING -> state.showMoreTrending
                }
                collectLast(flow) { itemsPagingData ->
                    showMoreAdapter.submitData(itemsPagingData)
                }
                collectLast(showMoreAdapter.loadStateFlow) { viewModel.setErrorUiState(it) }

            }
        }
    }

    private fun <T> LifecycleOwner.collectLast(flow: Flow<T>, action: suspend (T) -> Unit) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collectLatest(action)
            }
        }
    }

    override fun onEvent(event: ShowMoreUiEvent) {
        when (event) {
            ShowMoreUiEvent.BackNavigate -> findNavController().popBackStack()
            is ShowMoreUiEvent.NavigateToMovieDetails -> findNavController().navigate(
                ShowMoreFragmentDirections.actionShowMoreFragmentToMovieDetailsFragment(event.itemId)
            )

            is ShowMoreUiEvent.ShowSnackBar -> showSnackBar(event.messages)

        }
    }

}


