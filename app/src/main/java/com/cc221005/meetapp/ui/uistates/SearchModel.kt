package com.cc221005.meetapp.ui.uistates

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cc221005.meetapp.User
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
class SearchModel(private val db: FirebaseFirestore) : ViewModel() {
    private val _searchState = MutableStateFlow(SearchState())
    val searchState: StateFlow<SearchState> = _searchState.asStateFlow()

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
                val users = result.documents.map { document ->
                    document.toObject(User::class.java)!!
                }

                viewModelScope.launch {
                    _searchState.update { it.copy(loadedUsers = users) }
                    _searchState.update { it.copy(isLoading = false) }

                }
            }
    }

    fun updateSpecificUser(user: User) {
        viewModelScope.launch {
            _searchState.update { it.copy(specificUser = user) }
        }
    }
}