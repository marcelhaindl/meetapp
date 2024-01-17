package com.cc221005.meetapp.utils

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.cc221005.meetapp.R
import com.cc221005.meetapp.ui.uistates.UserModel
import com.cc221005.meetapp.ui.views.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

fun getTrailingButtonFunction(navController: NavController, currentScreen: Screen, userModel: UserModel, auth: FirebaseAuth, context: Context, db: FirebaseFirestore): () -> Unit {
    when(currentScreen) {
        Screen.OnboardingFlow1 -> return {
            navController.navigate(Screen.OnboardingFlow2.route)
        }

        Screen.OnboardingFlow2 -> return {
            navController.navigate(Screen.OnboardingFlow3.route)
        }

        Screen.OnboardingFlow3 -> return {
            auth.createUserWithEmailAndPassword(userModel.userState.value.email, userModel.userState.value.password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        navController.navigate(Screen.OnboardingFlow4.route)
                    } else {
                        Toast.makeText(
                            context,
                            context.getString(R.string.authentication_failed),
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }

        Screen.OnboardingFlow3Login -> return {
            auth.signInWithEmailAndPassword(userModel.userState.value.email, userModel.userState.value.password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful) {
                        userModel.updateFirebaseUser(auth.currentUser)
                    } else {
                        Toast.makeText(
                            context,
                            context.getString(R.string.authentication_failed),
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
                }
        }

        Screen.OnboardingFlow4 -> return {
            val data = hashMapOf(
                "username" to userModel.userState.value.username,
                "name" to userModel.userState.value.name
            )
            auth.currentUser?.let {
                db.collection("users").document(it.uid)
                    .set(data, SetOptions.merge())
                    .addOnSuccessListener { navController.navigate(Screen.OnboardingFlow5.route) }
                    .addOnFailureListener {
                        Toast.makeText(
                            context,
                            context.getString(R.string.failed_adding_data),
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
            }
        }

        Screen.OnboardingFlow5 -> return {
            val data = hashMapOf(
                "interests" to userModel.userState.value.interests
            )
            auth.currentUser?.let {
                db.collection("users").document(it.uid)
                    .set(data, SetOptions.merge())
                    .addOnSuccessListener { navController.navigate(Screen.OnboardingFlow6.route) }
                    .addOnFailureListener {
                        Toast.makeText(
                            context,
                            context.getString(R.string.failed_adding_data),
                            Toast.LENGTH_SHORT,
                        ).show()
                    }
            }
        }

        Screen.OnboardingFlow6 -> return {
            userModel.updateFirebaseUser(auth.currentUser)
        }

        else -> return {
            if(userModel.userState.value.currentUser == null) navController.navigate(Screen.OnboardingFlow1.route)
        }
    }
}