package com.coderops.viewmodel.common.listener

import com.coderops.bases.BaseInteractionListener

interface PeopleListener: BaseInteractionListener {
    fun onClickPeople(id: Int)
}