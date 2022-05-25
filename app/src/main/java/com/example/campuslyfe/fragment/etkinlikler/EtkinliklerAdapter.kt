package com.example.campuslyfe.fragment.etkinlikler

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.campuslyfe.databinding.ItemRowEtkinlikBinding
import com.example.campuslyfe.model.Etkinlik

class EtkinliklerAdapter(
    private val dataset: List<Etkinlik?>,
    private val context: Context,
    private val listener: OnEtkinlikClickListener
) : RecyclerView.Adapter<EtkinliklerAdapter.EtkinliklerViewHolder>() {

    inner class EtkinliklerViewHolder(val binding: ItemRowEtkinlikBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(etkinlik: Etkinlik?) {
            binding.item = etkinlik
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EtkinliklerViewHolder {
        return EtkinliklerViewHolder(
            ItemRowEtkinlikBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EtkinliklerViewHolder, position: Int) {
        holder.bind(dataset[position])
        holder.binding.root.setOnClickListener {
            listener.onEtkinlikClick(dataset[position])
        }
    }

    override fun getItemCount() = dataset.size

    interface OnEtkinlikClickListener {
        fun onEtkinlikClick(etkinlik: Etkinlik?)
    }
}