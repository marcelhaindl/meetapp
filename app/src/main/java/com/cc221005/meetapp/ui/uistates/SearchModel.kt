package com.cc221005.meetapp.ui.uistates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cc221005.meetapp.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * # Search Model
 * The Search Model is used to interact with states relating to the search functionality.
 *
 * @param db (FirebaseFirestore) Firebase database variable to interact with the database
 * @param userModel (UserModel) User Model variable to interact with the user model
 */
class SearchModel(private val db: FirebaseFirestore, private val userModel: UserModel) : ViewModel() {
    private val _searchState = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState> = _searchState.asStateFlow()

    /**
     * # Update Search
     * Updates the search string and gets all the users where the input is similar to their username.
     * It also removes the currently logged in user in order to prevent the user to search for itself.
     *
     * @param searchString (String) Search string from search bar.
     */
    fun updateSearch(searchString: String) {
        viewModelScope.launch {
            _searchState.update { it.copy(searchString = searchString) }
            _searchState.update { it.copy(isLoading = true) }
        }
        db.collection("users")
            .whereGreaterThanOrEqualTo("username", searchString)
            .whereLessThanOrEqualTo("username", searchString + "\uf8ff")
            .get()
            .addOnSuccessListener { result ->
                val users: MutableList<User> = result.documents.map { document ->
                    document.toObject(User::class.java)?.apply { uid = document.id }
                } as MutableList<User>

                users.removeIf { it.uid == userModel.userState.value.localUser?.uid }

                viewModelScope.launch {
                    _searchState.update { it.copy(loadedUsers = users) }
                    _searchState.update { it.copy(isLoading = false) }

                }
            }
    }
}