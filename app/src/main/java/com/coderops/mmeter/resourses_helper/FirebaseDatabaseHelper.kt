package com.coderops.mmeter.resourses_helper
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseDatabaseHelper @Inject constructor() {

    private val database = FirebaseDatabase.getInstance()
    private val loginStateRef = database.getReference("login_state")

    fun isLoggedInFlow(): Flow<Boolean> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val isLoggedIn = snapshot.getValue(Boolean::class.java) ?: false
                trySend(isLoggedIn)
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        loginStateRef.addValueEventListener(listener)

        awaitClose { loginStateRef.removeEventListener(listener) }
    }

    fun updateLoginStatus(isLoggedIn: Boolean) {
        loginStateRef.setValue(isLoggedIn)
    }
}