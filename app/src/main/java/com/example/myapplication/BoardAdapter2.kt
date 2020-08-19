package com.example.myapplication

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BoardAdapter2(private var context: Context, private var data: Array<Array<Int>>) : RecyclerView.Adapter<BoardAdapter2.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(context).inflate(R.layout.board_room, parent, false)
        return VH(view)
    }

    override fun getItemCount(): Int {
        return 16
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val i = position/4
        val j = position%4
        if (data[i][j] == 0){
            holder.board_room.setBackgroundColor(Color.WHITE)
        } else {
            holder.board_room.setBackgroundColor(Color.BLUE)
        }
    }

    class VH(itemView: View) : RecyclerView.ViewHolder(itemView){
        var board_room: TextView = itemView.findViewById(R.id.tv_board)
    }

}