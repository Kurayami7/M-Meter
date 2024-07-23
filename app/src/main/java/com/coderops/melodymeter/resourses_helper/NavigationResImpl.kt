package com.coderops.melodymeter.resourses_helper

import com.coderops.bases.NavigationRes
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationResImpl @Inject constructor() : NavigationRes {
    override val homeFeatureLink = com.coderops.ui.home.R.id.homeFragment
    override val gameFeatureLink = com.coderops.ui.trivia.R.id.game_nav_graph
    override val authFeatureLink = com.coderops.ui.home.R.id.loginFragment
    override val signupFeatureLink = com.coderops.ui.home.R.id.registerFragment
}