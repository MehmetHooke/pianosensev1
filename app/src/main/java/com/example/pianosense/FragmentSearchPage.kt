package com.example.pianosense


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentSearchPage : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_searchpage, container, false)

        // RecyclerView'i ayarla
        val recyclerView = view.findViewById<RecyclerView>(R.id.musicList)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Müzik listesi örnek verileri
        val musicList = listOf(
            MusicItem("The Little Princess", "Juno Erikson", R.drawable.ic_user),
            MusicItem("Valse", "Evgeny Grinko", R.drawable.ic_user),
            MusicItem("Beauty", "Jon Cossack", R.drawable.ic_user),
            MusicItem("Paragon Sky", "Glenn Natale", R.drawable.ic_user)
        )

        // Adapter'i ayarla
        val adapter = MusicAdapter(musicList)
        recyclerView.adapter = adapter  // Burada MusicAdapter'ı RecyclerView'e bağlıyoruz

        return view
    }
}


