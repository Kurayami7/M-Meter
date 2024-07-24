package com.coderops.ui.search.utils

import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.coderops.ui.home.R
import com.coderops.ui.home.databinding.SearchChipsFilterItemBinding
import com.coderops.viewmodel.search.SearchListener
import com.coderops.viewmodel.search.SearchUiState
import com.google.android.material.chip.ChipGroup

fun <T> ChipGroup.createChip(item: T, listener: SearchListener): View {
    val binding: SearchChipsFilterItemBinding = DataBindingUtil.inflate(
        LayoutInflater.from(context),
        R.layout.search_chips_filter_item,
        this,
        false
    )
    binding.item = item as SearchUiState.GenresUiState
    binding.listener = listener
    return binding.root
}