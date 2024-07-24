package com.coderops.viewmodel.people

import com.coderops.bases.BaseInteractionListener

interface PeopleDetailsListener : BaseInteractionListener {
    fun onClickMedia(itemId: Int,name:String)
    fun backNavigate()

}