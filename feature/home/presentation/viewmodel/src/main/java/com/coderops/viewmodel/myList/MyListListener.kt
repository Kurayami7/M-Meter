package com.coderops.viewmodel.myList
import com.coderops.bases.BaseInteractionListener

interface MyListListener : BaseInteractionListener {

    fun onClickItem(listId: Int , listType: String = "movie", listName: String = "favorite")

    fun onClickNewList()

    fun onClickBackButton()
    fun onClickShowDelete()
    fun onClickDelete(listId: Int, listName: String)
}