package com.ifs21049.dinopedia

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ifs21049.dinopedia.databinding.ItemRowDinoBinding

class ListDinoAdapter (private val ListDino: ArrayList<Dino>):
    RecyclerView.Adapter<ListDinoAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowDinoBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val dino = ListDino[position]
        holder.binding.ivItemDino.setImageResource(dino.icon)
        holder.binding.tvItemDino.text = dino.name
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(ListDino[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = ListDino.size

    class ListViewHolder(var binding: ItemRowDinoBinding) : RecyclerView.ViewHolder(binding.root)
    interface OnItemClickCallback {
        fun onItemClicked(data: Dino)
    }
}