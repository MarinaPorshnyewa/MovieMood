package com.example.moviemood.repository

import androidx.lifecycle.MutableLiveData
import com.example.moviemood.model.Favorite
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

object FirestoreRepository {
    val myRef = Firebase.database.getReference("https://moviemood2-fe0cd-default-rtdb")

    val data = MutableLiveData<Favorite>()

    fun getData() {
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {


                    data.value = dataSnapshot.child("users").child("1").child("name").getValue<Favorite>()
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })

    }

    /*fun setData(email: String?, film: Int) {

        val user = Favorite(film)
        val id = myRef.push().key
        if (id != null) {
            if (email != null) {
                myRef.child(id).child(email).setValue(user)
            }
        }
    }*/
    fun writeNewUser(userId: String, name: Int, email: String) {
        val user = Favorite(name, email)

        myRef.child("users").child(userId).setValue(user)
    }

    fun writeNewUser2(userId: String, name: Int, email: String) {

        myRef.child("users").child(userId).child("name").push().setValue(name)
    }

    private fun childEventListenerRecycler(){
        val childEventListener = object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
    }
}

