package com.coderops.ui.my_list

import com.coderops.bases.BaseAdapter
import com.coderops.ui.home.R
import com.coderops.viewmodel.myList.MyListListener
import com.coderops.viewmodel.myList.MyListUiState

class  MyListAdapter(items: List<MyListUiState>, listener: MyListListener):
    BaseAdapter<MyListUiState>(items, listener) {

    override val layoutID = R.layout.item_my_list
}