package com.play.gamex.apps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.play.gamex.apps.databinding.FragmentMainBinding


class MainFragment : Fragment() {


    private lateinit var adapter: MyListAdapter
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater,container,false)
        binding.list.adapter = adapter
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val arr = context?.resources?.getStringArray(R.array.list)
        adapter = MyListAdapter(
            arr,
            context,
            object: MyListAdapter.Companion.ClickListener {
                override fun click(name:String,pos:Int) {
                    val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
                    val navController = navHostFragment.navController
                    var args = Bundle()
                    args.putString("name",name)
                    args.putInt("ind",pos)
                    navController.navigate(R.id.itemFragment,args)
                }
            }
        )
    }
}