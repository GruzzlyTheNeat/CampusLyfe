package com.example.campuslyfe.fragment.harita

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.campuslyfe.R
import com.example.campuslyfe.model.Club
import kotlinx.android.synthetic.main.club_rw_row.view.*

class ClubPopUpRwAdapter(
    private val clubPopUpList: List<Club>,
    private val context: Context,
    private val listenerClubPopUp: OnClubPopUpListener
) : RecyclerView.Adapter<ClubPopUpRwAdapter.ClubPopUpViewHolder>() {
    inner class ClubPopUpViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubPopUpViewHolder {
        return ClubPopUpViewHolder(LayoutInflater.from(context).inflate(R.layout.club_rw_row,parent,false))
    }

    override fun onBindViewHolder(holder: ClubPopUpViewHolder, position: Int) {
        val currentItem = clubPopUpList[position]
        holder.itemView.imageViewClubRow.setImageResource(currentItem.vektor.toString().toInt())
        holder.itemView.textViewClubRow.text = currentItem.name
        holder.itemView.rootView.setOnClickListener {
            listenerClubPopUp.onClubPopUpClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return clubPopUpList.size
    }

    interface OnClubPopUpListener{
        fun onClubPopUpClick(club:Club)
    }

}