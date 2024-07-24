package com.coderops.ui.people.adapter
import com.coderops.bases.BaseAdapter
import com.coderops.ui.home.R
import com.coderops.viewmodel.people.PeopleDetailsListener
import com.coderops.viewmodel.people.PersonDetailsUiState


class  PeopleDetailsRecyclerAdapter(items: List<PersonDetailsUiState.PeopleMediaUiState>, listener: PeopleDetailsListener):
    BaseAdapter<PersonDetailsUiState.PeopleMediaUiState>(items, listener) {

    override val layoutID = R.layout.item_people_media
}