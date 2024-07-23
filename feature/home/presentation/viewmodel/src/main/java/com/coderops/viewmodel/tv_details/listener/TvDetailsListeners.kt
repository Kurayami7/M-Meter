package com.coderops.viewmodel.tv_details.listener

import com.coderops.viewmodel.common.listener.MediaListener
import com.coderops.viewmodel.common.listener.PeopleListener
import com.coderops.viewmodel.common.listener.ChipListener

interface TvDetailsListeners : RateListener, PeopleListener, MediaListener,
    SeasonListener, ShowMoreCast, ShowMoreRecommended,PlayButtonListener, ChipListener