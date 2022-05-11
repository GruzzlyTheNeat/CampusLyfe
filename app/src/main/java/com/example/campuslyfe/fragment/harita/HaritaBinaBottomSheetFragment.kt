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
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson

class HaritaBinaBottomSheetFragment : BottomSheetDialogFragment(),
    EtkinlikPopUpRwAdapter.OnPopUpEtkinlikClickListener,
    ClubPopUpRwAdapter.OnClubPopUpListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DialogHaritaBinaBottomSheetBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner

            val binaArgument = Gson().fromJson(arguments?.getString(PARAM_BINA), Bina::class.java)
            bina = binaArgument
            val etkinliklerDummyData = listOf(
                Etkinlik(
                    "konser monser",
                    "emre aydın konseri",
                    "şelalenin arkası",
                    "054635352523",
                    R.drawable.ic_launcher_background,
                    2.5,5.2
                ),
                Etkinlik(
                    "rektörle maç",
                    "türkiye izlanda maçı",
                    "şelalenin önü",
                    "05055555555",
                    R.drawable.ic_launcher_background,
                    2.5,5.2
                ),
                Etkinlik(
                    "rektörle iftar",
                    "açık büfe",
                    "şelale",
                    "0302402532",
                    R.drawable.ic_launcher_background,
                    2.5,5.2
                )
            )
            val clubPopUpList = listOf(
                Club(
                    R.drawable.ic_baseline_apps_24,
                    "Kulüp1",
                    "Açıklama1",
                    "Adres1",
                    "Contact1",
3.5,6.3                ),
                Club(
                    R.drawable.ic_baseline_apps_24,
                    "Kulüp2",
                    "Açıklama2",
                    "Adres2",
                    "Contact2",
                    3.5,6.3
                ),
                Club(
                    R.drawable.ic_baseline_apps_24,
                    "Kulüp3",
                    "Açıklama3",
                    "Adres3",
                    "Contact3",
                    3.5,6.3
                )
            )

            recyclerViewEtkinlikPopUp.adapter = EtkinlikPopUpRwAdapter(
                etkinliklerDummyData,
                requireContext(),
                this@HaritaBinaBottomSheetFragment
            )
            recyclerViewClubPopUp.adapter = ClubPopUpRwAdapter(
                clubPopUpList,
                requireContext(),
                this@HaritaBinaBottomSheetFragment
            )
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