package com.coderops.viewmodel.common.listener

import com.coderops.bases.BaseInteractionListener

interface MediaListener: BaseInteractionListener {
    fun onClickMedia(id: Int)
}