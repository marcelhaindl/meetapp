package com.cc221005.meetapp

import com.google.firebase.firestore.Exclude

/**
 * # User Class
 * User is a data class (blueprint) representing user information.
 *
 * @param uid (String) The unique identifier for the user. Nullable.
 * @param email (String) The email address of the user. Nullable.
 * @param username (String) The username chosen by the user. Nullable.
 * @param name (String) The name of the user. Nullable.
 * @param interests (MutableList<String>) List of interests associated with the user. Initialized as an empty mutable list.
 * @param followers (MutableList<String>) List of user IDs who follow the user. Initialized as an empty mutable list.
 * @param following (MutableList<String>) List of user IDs whom the user follows. Initialized as an empty mutable list.
 * @param biography (String) A brief biography or description provided by the user. Nullable.
 */
data class User(
    var uid: String? = "",
    var email: String? = "",
    var username: String? = "",
    var name: String? = "",
    var interests: MutableList<String>? = mutableListOf(),
    var followers: MutableList<String>? = mutableListOf(),
    var following: MutableList<String>? = mutableListOf(),
    var biography: String? = "",
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "uid" to uid,
            "email" to email, // only for currently logged in user
            "username" to username,
            "name" to name,
            "interests" to interests,
            "followers" to followers,
            "following" to following,
            "biography" to biography,
            )
    }
    override fun toString(): String {
        return "UserData(uid=$uid, email=$email, username=$username, name=$name, interests=$interests, followers=$followers, following=$following, biography=$biography)"
    }
}