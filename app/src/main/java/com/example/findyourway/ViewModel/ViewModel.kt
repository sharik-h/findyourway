package com.example.findyourway.ViewModel

import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.findyourway.Data.Post
import com.example.findyourway.Data.Scroll
import com.example.findyourway.local
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate

class viewModel: ViewModel() {

    val imageBitmap = mutableStateOf<Bitmap?>(null)
    val firestore = Firebase.firestore
    val newScroll = mutableStateOf(Scroll())
    val scrollData = mutableStateOf(Scroll())
    var allScrolls = mutableListOf<Scroll>()
    var newPost = mutableStateOf(Post())
    var selectedScroll = mutableStateOf(Scroll())
    val user = FirebaseAuth.getInstance().currentUser

    fun updateData(name: String, value: String){
        when(name){
            "nameScroll" -> newScroll.value = newScroll.value.copy(name = value)
            "locationScroll" -> newScroll.value = newScroll.value.copy(location = value)
            "descScroll" -> newScroll.value = newScroll.value.copy(description =  value)
            "postname" -> newPost.value = newPost.value.copy(name = value)
            "postdate" -> newPost.value = newPost.value.copy(date = value)
            "postDesc" -> newPost.value = newPost.value.copy(Description = value)
            "selname" -> selectedScroll.value = selectedScroll.value.copy(name = value)
            "seldesc" -> selectedScroll.value = selectedScroll.value.copy(description = value)
            "selpos" -> selectedScroll.value = selectedScroll.value.copy(location = value)
        }
    }

    fun getAllScroll(){
        firestore
            .collection("scrolls")
            .get()
            .addOnSuccessListener {
                val data = mutableListOf<Scroll>()
                it.documents.forEach {
                    data.add(it.toObject(Scroll::class.java)!!)
                }
                allScrolls = data
            }.addOnFailureListener {
                println("-------------------------------------")
                println(it.message)
            }
    }

    fun addNewScroll(){
        firestore
            .collection("scrolls")
            .add(newScroll.value)
        newScroll.value = Scroll()
    }

    fun searchScroll() {
        val selectedLat = local.latitude.toString()
        val selectedLong = local.longitude.toString()
        val nearby = allScrolls.filter {
            println("-------------------------------------------------")
            println(it.location.split(",").first())
            println(selectedLat)
            it.location.split(",").first().substring(0,3) == selectedLat.substring(0,3)
                    && it.location.split(",").last().substring(0,3) == selectedLong.substring(0,3)
        }

        var closestValue = nearby[0]
        for (i in 1 until nearby.size) {
            if (Math.abs(nearby[i].location.split(",").last().toInt() - selectedLat.toInt())
                < Math.abs(closestValue.location.split(",").last().toInt() - selectedLat.toInt())) {
                if (Math.abs(nearby[i].location.split(",").first().toInt() - selectedLat.toInt())
                    < Math.abs(closestValue.location.split(",").first().toInt() - selectedLat.toInt())){
                    closestValue = nearby[i]
                }
            } else {
                break
            }
        }
        updateData("selname", closestValue.name)
        updateData("selpos", closestValue.location)
        updateData("seldesc", closestValue.description)
    }

    fun setbitimgValue(bitmap: Bitmap) {
        imageBitmap.value = bitmap
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun sendPost(){
        updateData("postname", user?.displayName.toString())
        updateData("postdate", LocalDate.now().toString())
        firestore
            .collection("posts")
            .add(newPost)
        newPost.value = Post()
    }
}