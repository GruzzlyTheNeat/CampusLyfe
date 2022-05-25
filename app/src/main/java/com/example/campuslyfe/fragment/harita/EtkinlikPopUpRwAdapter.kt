package com.example.campuslyfe.fragment.harita

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.campuslyfe.databinding.ItemRowEtkinlikBinding
import com.example.campuslyfe.model.Etkinlik

class EtkinlikPopUpRwAdapter(
    private val etkinlikList: ArrayList<Etkinlik>,
    private val context: Context,
    private val listener: OnPopUpEtkinlikClickListener
) : RecyclerView.Adapter<EtkinlikPopUpRwAdapter.ViewHolderPopUpEtkinlik>() {
    class ViewHolderPopUpEtkinlik(val binding: ItemRowEtkinlikBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(etkinlik: Etkinlik) {
            binding.item = etkinlik
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderPopUpEtkinlik {
        return ViewHolderPopUpEtkinlik(
            ItemRowEtkinlikBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolderPopUpEtkinlik, position: Int) {

        holder.bind(etkinlikList[position])
        holder.binding.root.setOnClickListener {
            listener.onEtkinlikPopUpClick(etkinlikList[position])
        }

    }

    override fun getItemCount(): Int {
        return etkinlikList.size
    }

    interface OnPopUpEtkinlikClickListener {
        fun onEtkinlikPopUpClick(etkinlik: Etkinlik)
    }

}