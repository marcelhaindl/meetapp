package com.cc221005.meetapp

import com.google.firebase.firestore.Exclude

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