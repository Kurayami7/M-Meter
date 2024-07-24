package com.coderops.viewmodel.profile

interface ProfileListener {
    fun onClickFavorite()
    fun onClickWatchlist()
    fun onClickWatchHistory()
    fun onClickMyLists()
    fun onClickPopcornPuzzles()
    fun onClickLogout()
    fun onUserNotLoggedIn()
    fun ocClickLogIn()
}