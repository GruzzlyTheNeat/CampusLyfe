package com.example.campuslyfe.fragment.admin

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.campuslyfe.databinding.ItemRowEtkinlikBinding
import com.example.campuslyfe.model.Etkinlik

class EtkinlikSilRwAdapter(private val dataset: List<Etkinlik?>, private val context: Context, private val listener:OnEtkinlikSilClickListener) : RecyclerView.Adapter<EtkinlikSilRwAdapter.EtkinlikSilViewHolder>() {



    inner class EtkinlikSilViewHolder(val binding: ItemRowEtkinlikBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(etkinlik: Etkinlik?) {
            binding.item = etkinlik
            binding.executePendingBindings()
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EtkinlikSilViewHolder {
        return EtkinlikSilViewHolder(
            ItemRowEtkinlikBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    interface OnEtkinlikSilClickListener{
        fun onEtkinlikSilClick(etkinlik : Etkinlik?)
    }

    override fun onBindViewHolder(
        holder: EtkinlikSilRwAdapter.EtkinlikSilViewHolder,
        position: Int
    ) {
        holder.bind(dataset[position])
        holder.binding.root.setOnClickListener {
            listener.onEtkinlikSilClick(dataset[position])
        }
    }

    override fun getItemCount(): Int {
       return dataset.size
    }

}