package com.coderops.viewmodel.tv_details.listener

import com.coderops.bases.BaseInteractionListener

interface SeasonListener : BaseInteractionListener {
    fun onClickSeason(seasonNumber: Int)
}