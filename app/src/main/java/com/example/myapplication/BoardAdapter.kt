package com.example.mya

import com.example.myapplication.R

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BoardAdapter(context: Context, data: Array<IntArray>) : RecyclerView.Adapter<BoardAdapter.VH>() {

    private var context: Context? = null
    private var data: Array<IntArray>? = null

    init {
        this.context = context
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        var view = LayoutInflater.from(context).inflate(R.layout.board_room, parent, false)
        return VH(view)
    }

    override fun getItemCount(): Int {
        return 200
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val i = position/10
        val j = position%10
        if (data?.get(i)?.get(j) ==0){
            holder.board_room.setBackgroundColor(Color.WHITE)
        } else {
            holder.board_room.setBackgroundColor(Color.BLUE)
        }
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        var board_room: TextView = itemView.findViewById(R.id.tv_board)
    }

}