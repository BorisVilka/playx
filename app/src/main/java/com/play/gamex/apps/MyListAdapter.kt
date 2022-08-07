package com.play.gamex.apps

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.play.gamex.apps.databinding.ListItemBinding
import java.io.InputStream

class MyListAdapter(
    val arr: Array<String>?,
    val context: Context?,
    val listener: ClickListener
): RecyclerView.Adapter<MyListAdapter.Companion.MyViewHolder>()
{

    companion object {

        class MyViewHolder(val binding: ListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

                fun bind(name: String, context: Context?) {
                    binding.nameList.text = name
                    try {
                        var inp = context?.assets?.open("$name.jpg")
                        binding.logoList.setImageBitmap(BitmapFactory.decodeStream(inp))
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
        }

        interface ClickListener {
            fun click(name: String,pos:Int)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("TAG","BIND HOLDER")
        holder.binding.root.setOnClickListener {
            arr?.let { it1 -> listener.click(it1.get(position),position) }
        }
        arr?.let { holder.bind(it.get(position), context) }
    }

    override fun getItemCount(): Int {
        return arr?.size ?: 0
    }
}