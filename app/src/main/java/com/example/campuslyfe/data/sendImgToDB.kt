package com.example.campuslyfe.data

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage


class sendImgToDB  {

  //  private lateinit var ImageUri: Uri

    fun uploadImgUser(imageUri : Uri, name : String){
        val storageRef = FirebaseStorage.getInstance().getReference("users/$name")
        storageRef.putFile(imageUri).addOnSuccessListener {
            println("Kondu")
        }.addOnFailureListener{
            println("Konamadı")
        }

    }

    fun uploadImgEtkinlik(imageUri : Uri, name : String){
        val storageRef = FirebaseStorage.getInstance().getReference("etkinlikler/$name")
        storageRef.putFile(imageUri).addOnSuccessListener {
            println("Kondu")
        }.addOnFailureListener{
            println("Konamadı")
        }

    }

    fun uploadImgTopluluk(imageUri : Uri, name : String){
        val storageRef = FirebaseStorage.getInstance().getReference("topluluklar/$name")
        storageRef.putFile(imageUri).addOnSuccessListener {
            println("Kondu")
        }.addOnFailureListener{
            println("Konamadı")
        }

    }

//    fun selectImg(){
//
//        val intent = Intent()
//        intent.type = "image/*"
//        intent.action = Intent.ACTION_GET_CONTENT
//        startActivityForResult(intent, 100)
//
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if(requestCode == 100 && resultCode == RESULT_OK){
//
//            ImageUri = data?.data!!
//
//        }
//    }
}