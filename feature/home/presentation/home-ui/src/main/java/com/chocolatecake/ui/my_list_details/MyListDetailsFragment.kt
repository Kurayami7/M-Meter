package com.chocolatecake.ui.my_list_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.common.base.SwipeToDeleteItem
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentMyListDetailsBinding
import com.chocolatecake.viewmodel.my_list_details.MyListDetailsUiEvent
import com.chocolatecake.viewmodel.my_list_details.MyListDetailsUiState
import com.chocolatecake.viewmodel.my_list_details.MyListDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyListDetailsFragment :
    BaseFragment<FragmentMyListDetailsBinding, MyListDetailsUiState, MyListDetailsUiEvent>() {

    override val layoutIdFragment: Int = R.layout.fragment_my_list_details
    override val viewModel: MyListDetailsViewModel by viewModels()
    private lateinit var myListDetailsAdapter: MyListDetailsAdapter
    private lateinit var swipeToDeleteMedia: SwipeToDeleteItem
    private lateinit var touchHelper: ItemTouchHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        swipeToDeleteMediaSetup()
    }

    private fun swipeToDeleteMediaSetup() {
        swipeToDeleteMedia = object : SwipeToDeleteItem() {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ) = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                viewModel.deleteMedia(
                    position,
                )
                myListDetailsAdapter.notifyItemChanged(position)
            }
        }
        if (::touchHelper.isInitialized) {
            touchHelper.attachToRecyclerView(null)
            touchHelper = ItemTouchHelper(swipeToDeleteMedia)
            touchHelper.attachToRecyclerView(binding.recyclerViewMyListDetails)
        } else {
            touchHelper = ItemTouchHelper(swipeToDeleteMedia)
            touchHelper.attachToRecyclerView(binding.recyclerViewMyListDetails)
        }
    }

    private fun setAdapter() {
        myListDetailsAdapter = MyListDetailsAdapter(mutableListOf(), viewModel)
        binding.recyclerViewMyListDetails.adapter = myListDetailsAdapter
    }


    override fun onEvent(event: MyListDetailsUiEvent) {
        when (event) {
            is MyListDetailsUiEvent.NavigateToMovieDetails -> {
                findNavController().navigate(
                    MyListDetailsFragmentDirections.actionMyListDetailsFragmentToMovieDetailsFragment(
                        movieId = event.movieId,
                    )
                )
            }

            is MyListDetailsUiEvent.OnClickBack -> {
                findNavController().popBackStack()
            }

            is MyListDetailsUiEvent.ShowSnackBar -> showSnackBar(event.message)
            is MyListDetailsUiEvent.NavigateToTvDetails -> {
                findNavController().navigate(
                    MyListDetailsFragmentDirections.actionMyListDetailsFragmentToTvDetailsFragment(
                        tvShowId = event.movieId,
                    )
                )
            }
        }
    }
}

