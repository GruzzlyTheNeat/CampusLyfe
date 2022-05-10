package com.example.campuslyfe.fragment.etkinlikler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentEtkinliklerBinding
import com.example.campuslyfe.model.Club
import com.example.campuslyfe.model.Etkinlik
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EtkinliklerFragment : Fragment(), EtkinliklerAdapter.OnEtkinlikClickListener {
    private lateinit var etkinlikList : ArrayList<Etkinlik>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEtkinliklerBinding.inflate(inflater, container, false).apply {

            lifecycleOwner = viewLifecycleOwner

            val databaseEtkinlik = FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Etkinlikler")
            etkinlikList = arrayListOf<Etkinlik>()
            databaseEtkinlik.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    println("çalıştıı1")
                    if(snapshot.exists()){
                        println("çalıştıı2")
                        for(etkinlikSnapShot in snapshot.children){
                            println("çalıştıı3")

                            val etkinlik = etkinlikSnapShot.getValue(Etkinlik::class.java)
                            println(etkinlik?.etkinlikAd)
                            etkinlikList.add(etkinlik!!)
                        }
                        rvEtkinlikler.adapter =
                            EtkinliklerAdapter(etkinlikList, requireContext(), this@EtkinliklerFragment)

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    println("hataaa ${error}")
                }

            })

//            val etkinliklerDummyData = listOf(
//                Etkinlik(
//                    "konser monser",
//                    "emre aydın konseri",
//                    "şelalenin arkası",
//                    "054635352523",
//                    R.drawable.ic_launcher_background
//                ),
//                Etkinlik(
//                    "rektörle maç",
//                    "türkiye izlanda maçı",
//                    "şelalenin önü",
//                    "05055555555",
//                    R.drawable.ic_launcher_background
//                ),
//                Etkinlik(
//                    "rektörle iftar",
//                    "açık büfe",
//                    "şelale",
//                    "0302402532",
//                    R.drawable.ic_launcher_background
//                ),
//                Etkinlik(
//                    "rektörle sahur",
//                    "ücretsizdir",
//                    "şelale",
//                    "8042895223890",
//                    R.drawable.ic_launcher_background
//                ),
//                Etkinlik(
//                    "bahar şenliği",
//                    "aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama aşırı uzun açıklama ",
//                    "adres",
//                    "telno",
//                    R.drawable.ic_launcher_background
//                ),
//                Etkinlik(
//                    "etkinlik adı",
//                    "etkinlkik",
//                    "şelale",
//                    "telefon",
//                    R.drawable.ic_launcher_background
//                ),
//            )

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