package com.coderops.viewmodel.my_list_details

import com.coderops.bases.BaseInteractionListener

interface MyListDetailsListener : BaseInteractionListener {
    fun onClickItem(itemId: Int , mediaType: String)

    fun onClickBackButton()
}