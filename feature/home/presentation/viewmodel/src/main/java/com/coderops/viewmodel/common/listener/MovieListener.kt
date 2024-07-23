package com.coderops.viewmodel.common.listener

import com.coderops.bases.BaseInteractionListener

interface MovieListener: BaseInteractionListener {
    fun onClickMedia(id: Int)
}