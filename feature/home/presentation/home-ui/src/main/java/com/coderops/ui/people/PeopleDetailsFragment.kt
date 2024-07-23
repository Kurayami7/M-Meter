package com.coderops.ui.people

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.coderops.bases.BaseFragment
import com.coderops.ui.home.R
import com.coderops.ui.home.databinding.FragmentPeopleDetailsBinding
import com.coderops.ui.people.adapter.PeopleDetailsRecyclerAdapter

import com.coderops.viewmodel.people.PeopleDetailsUiEvent
import com.coderops.viewmodel.people.PersonDetailsUiState
import com.coderops.viewmodel.people.PeopleDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleDetailsFragment :
    BaseFragment<FragmentPeopleDetailsBinding, PersonDetailsUiState, PeopleDetailsUiEvent>() {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_people_details
    override val viewModel: PeopleDetailsViewModel by viewModels()
    private val args: PeopleDetailsFragmentArgs by navArgs()
    private lateinit var peopleMoviesAdapter: PeopleDetailsRecyclerAdapter
    private lateinit var peopleTvShowsAdapter: PeopleDetailsRecyclerAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapters()
        getData()
    }

    private fun setAdapters() {
        peopleMoviesAdapter = PeopleDetailsRecyclerAdapter(mutableListOf(), viewModel)
        binding.recyclerViewPeopleMovies.adapter = peopleMoviesAdapter
        peopleTvShowsAdapter = PeopleDetailsRecyclerAdapter(mutableListOf(), viewModel)
        binding.recyclerViewPeopleTvShows.adapter = peopleTvShowsAdapter


    }

    private fun getData() {
        collectLatest {
            viewModel.state.collect { state ->
                peopleMoviesAdapter.setItems(state.movies)
                peopleTvShowsAdapter.setItems(state.tvShows)
                if (state.onErrors.isNotEmpty()) {
                    state.onErrors.last().let {
                        showSnackBar(it)
                    }
                }
            }
        }
    }

    override fun onEvent(event: PeopleDetailsUiEvent) {
        when (event) {
            PeopleDetailsUiEvent.BackNavigate -> findNavController().popBackStack()
            is PeopleDetailsUiEvent.ClickMovieEvent ->
                findNavController().navigate(
                    PeopleDetailsFragmentDirections.actionPeopleDetailsFragmentToMovieDetailsFragment(
                        event.itemId
                    )
                )

            is PeopleDetailsUiEvent.ClickTvShowsEvent ->
                findNavController().navigate(
                    PeopleDetailsFragmentDirections.actionPeopleDetailsFragmentToTvDetailsFragment(
                        event.itemId
                    )
                )
        }
    }

}