package com.coderops.viewmodel.showmore

import com.coderops.bases.BaseInteractionListener

interface ShowMoreListener : BaseInteractionListener {
    fun onClickMedia(mediaId: Int)
    fun backNavigate()
}
