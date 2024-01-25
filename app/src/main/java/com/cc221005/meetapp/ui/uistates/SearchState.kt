package com.cc221005.meetapp.ui.uistates

import com.cc221005.meetapp.User

/**
 * # Search State
 * The Search State is a data class (blueprint) containing all the variables to interact with states of search functionality
 */
data class SearchState(
    val searchString: String = "",
    val loadedUsers: List<User> = listOf(),
    val isLoading: Boolean = false,
)