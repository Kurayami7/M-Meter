package com.coderops.viewmodel.watch_history.state_managment

import com.coderops.bases.BaseInteractionListener

interface WatchHistoryOnEventListeners : BaseInteractionListener {
    fun onCardClickListener(id:Int)
    fun onCardSwipeListener()

}