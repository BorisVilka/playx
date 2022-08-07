package com.play.gamex.apps

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.play.gamex.apps.databinding.FragmentItemBinding
import com.play.gamex.apps.databinding.FragmentMainBinding


class ItemFragment : Fragment() {

    private lateinit var binding: FragmentItemBinding
    private lateinit var name: String
    private var ind = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        name = arguments?.getString("name","").toString()
        ind = arguments?.getInt("ind",0)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentItemBinding.inflate(inflater,container,false)
        binding.textView.text = name
        var inp = context?.assets?.open("$name.jpg")
        binding.imageView.setImageBitmap(BitmapFactory.decodeStream(inp))
        binding.textView2.text = context?.resources?.getStringArray(R.array.desc)?.get(ind)
        // Inflate the layout for this fragment
        return binding.root
    }

}