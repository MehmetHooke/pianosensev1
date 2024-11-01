package com.example.pianosense

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment

class Screen1Fragment : Fragment() {
    private var listener: OnNextClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnNextClickListener) {
            listener = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_screen1, container, false)
        val buttonNext = view.findViewById<ImageView>(R.id.buttonNext)
        buttonNext.setOnClickListener {
            (activity as? OnNextClickListener)?.onNextClick()
        }
        return view
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}
