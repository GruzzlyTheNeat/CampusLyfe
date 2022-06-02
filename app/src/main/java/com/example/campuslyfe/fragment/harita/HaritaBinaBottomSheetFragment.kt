package com.example.campuslyfe.fragment.harita

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.DialogHaritaBinaBottomSheetBinding
import com.example.campuslyfe.model.Bina
import com.example.campuslyfe.model.Club
import com.example.campuslyfe.model.Etkinlik
import com.example.campuslyfe.utils.getDatabaseInstance
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.*
import com.google.gson.Gson

class  HaritaBinaBottomSheetFragment : BottomSheetDialogFragment(),
    EtkinlikPopUpRwAdapter.OnPopUpEtkinlikClickListener,
    ClubPopUpRwAdapter.OnClubPopUpListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DialogHaritaBinaBottomSheetBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            val etkinlikList = arrayListOf<Etkinlik>()
            var etkinlikRealList: ArrayList<Etkinlik>
            var toplulukRealList: ArrayList<Club>
            val binaList = arrayListOf<Bina>()
            val toplulukList = ArrayList<Club>()

            val binaArgument = Gson().fromJson(arguments?.getString(PARAM_BINA), Bina::class.java)
            bina = binaArgument
            val databaseBina =
                getDatabaseInstance()
                    .getReference("Binalar")
            databaseBina.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (dss in snapshot.children) {
                            val bina = dss.getValue(Bina::class.java)
                            binaList.add(bina!!)
                        }
                        for (b in binaList) {
                            if (bina?.binaAd.equals(b.binaAd)) {

                                for (e in b.etkinliklerList!!) {
                                    etkinlikList.add(e)
                                }
                                for (t in b.topulukList!!) {
                                    toplulukList.add(t)
                                }

                            }
                        }
                        etkinlikRealList = ArrayList(etkinlikList).apply {
                            removeAt(0)
                        }

                        recyclerViewEtkinlikPopUp.adapter = EtkinlikPopUpRwAdapter(
                            etkinlikRealList,
                            requireContext(),
                            this@HaritaBinaBottomSheetFragment
                        )
                        toplulukRealList = ArrayList(toplulukList).apply {
                            removeAt(0)
                        }
                        recyclerViewClubPopUp.adapter = ClubPopUpRwAdapter(
                            toplulukRealList,
                            requireContext(),
                            this@HaritaBinaBottomSheetFragment
                        )


                    }
                }

                override fun onCancelled(error: DatabaseError) {

                }

            })


        }.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Theme_App_BottomSheetDialog_Transparent)
        dialog?.window?.setWindowAnimations(R.style.DialogAnimation)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState).apply {
            setOnShowListener { dialog ->
                val bottomSheetDialog = dialog as BottomSheetDialog
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
                    ?.also { view ->
                        BottomSheetBehavior.from(view).state = BottomSheetBehavior.STATE_EXPANDED
                    }
            }
        }
    }

    override fun onClubPopUpClick(club: Club) {
        dismiss()
        parentFragment?.findNavController()?.navigate(
            HaritaFragmentDirections.actionHaritaFragmentToClubDetayFragment(
                club
            )
        )
    }

    override fun onEtkinlikPopUpClick(etkinlik: Etkinlik) {
        dismiss()
        parentFragment?.findNavController()?.navigate(
            HaritaFragmentDirections.actionHaritaFragmentToEtkinlikDetayFragment(
                etkinlik
            )
        )
    }

    companion object {
        const val PARAM_BINA = "bina"
    }
}