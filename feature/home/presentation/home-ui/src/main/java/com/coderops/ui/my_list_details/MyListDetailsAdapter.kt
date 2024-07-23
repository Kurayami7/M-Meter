package com.coderops.ui.my_list_details
import com.coderops.bases.BaseAdapter
import com.coderops.ui.home.R
import com.coderops.viewmodel.my_list_details.MyListDetailsListener
import com.coderops.viewmodel.my_list_details.MyListDetailsUiState



class  MyListDetailsAdapter(items: List<MyListDetailsUiState>, listener: MyListDetailsListener):
    BaseAdapter<MyListDetailsUiState>(items, listener) {

    override val layoutID = R.layout.item_my_list_details
}