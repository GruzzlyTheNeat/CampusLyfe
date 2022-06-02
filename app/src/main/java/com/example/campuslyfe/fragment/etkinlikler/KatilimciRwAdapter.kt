package com.example.campuslyfe.fragment.etkinlikler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.campuslyfe.R
import com.example.campuslyfe.model.User
import com.example.campuslyfe.utils.downloadFromURL
import com.example.campuslyfe.utils.placeHolderProgressBar
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.user_rw_row.view.*

class KatilimciRwAdapter(private val userList : List<User>) : RecyclerView.Adapter<KatilimciRwAdapter.ViewHolder>() {

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder((LayoutInflater.from(parent.context).inflate(R.layout.user_rw_row,parent,false)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.textViewUserNameRow.text = currentItem?.adSoyad
        val uid = currentItem.userId
        if(uid != null){
            val imagePath = "users/$uid"
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child(imagePath)
            imageRef.downloadUrl.addOnSuccessListener {
                holder.itemView.imgUserRow.downloadFromURL(it.toString(), placeHolderProgressBar(holder.itemView.context))
            }
            holder.itemView.rootView.setOnClickListener {
                val action = EtkinlikKatilimciListFragmentDirections.actionEtkinlikKatilimciListFragmentToKatilimciProfilFragment(currentItem)
                holder.itemView.findNavController().navigate(action)
            }

        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }


}