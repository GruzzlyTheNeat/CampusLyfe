package com.example.campuslyfe.fragment.etkinlikler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentEtkinliklerBinding
import com.example.campuslyfe.model.Etkinlik

class EtkinliklerFragment : Fragment(), EtkinliklerAdapter.OnEtkinlikClickListener {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEtkinliklerBinding.inflate(inflater, container, false).apply {

            lifecycleOwner = viewLifecycleOwner

            val etkinliklerDummyData = listOf(
                Etkinlik("konser monser", "emre aydın konseri", "şelalenin arkası", "054635352523", R.drawable.ic_launcher_background),
                Etkinlik("rektörle maç", "türkiye izlanda maçı", "şelalenin önü", "05055555555", R.drawable.ic_launcher_background),
                Etkinlik("rektörle iftar", "açık büfe", "şelale", "0302402532", R.drawable.ic_launcher_background),
                Etkinlik("rektörle sahur", "ücretsizdir", "şelale", "8042895223890", R.drawable.ic_launcher_background),
                Etkinlik(
                    "bahar şenliği",
                    "aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama ",
                    "adres",
                    "telno",
                    R.drawable.ic_launcher_background
                ),
                Etkinlik("etkinlik adı", "etkinlkik", "şelale", "telefon", R.drawable.ic_launcher_background),
            )
            rvEtkinlikler.adapter =
                EtkinliklerAdapter(etkinliklerDummyData, requireContext(), this@EtkinliklerFragment)
        }.root
    }

    override fun onEtkinlikClick(etkinlik: Etkinlik) {
        findNavController().navigate(
            EtkinliklerFragmentDirections.actionEtkinliklerFragmentToEtkinlikDetayFragment(
                etkinlik
            )
        )
    }
}