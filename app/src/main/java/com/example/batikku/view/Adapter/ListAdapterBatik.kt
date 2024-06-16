package com.example.batikku.view.Adapter


import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.batikku.R
import com.example.batikku.view.Model.ResponseBatikItem
import com.example.batikku.view.main.DetailActivity


class ListAdapterBatik(private val batikList: List<ResponseBatikItem>) : RecyclerView.Adapter<ListAdapterBatik.BatikViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.itemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    inner class BatikViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.img_item_photo)
        val nameTextView: TextView = itemView.findViewById(R.id.tv_item_name)
        val historyTextView: TextView = itemView.findViewById(R.id.tv_item_history)

        init {
            itemView.setOnClickListener {
                if (::itemClickListener.isInitialized) {
                    itemClickListener.onItemClick(adapterPosition)
                }
            }
        }

        fun bind(batikItem: ResponseBatikItem) {
            nameTextView.text = batikItem.batikName
            historyTextView.text = batikItem.batikHistory
            // Load image using Glide or any other method
            Glide.with(itemView.context)
                .load(batikItem.image)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BatikViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_row_list, parent, false)
        return BatikViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BatikViewHolder, position: Int) {
        val currentBatik = batikList[position]
        holder.nameTextView.text = currentBatik.batikName
        val maxWords = 20
        val words = currentBatik.batikHistory?.split(" ") ?: listOf()
        val limitedHistory = if (words.size > maxWords) {
            words.take(maxWords).joinToString(" ") + "..."
        } else {
            currentBatik.batikHistory
        }
        holder.historyTextView.text = limitedHistory

        Glide.with(holder.itemView.context)
            .load(currentBatik.image)
            .into(holder.imageView)

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DetailActivity::class.java).apply {
                putExtra("BATIK_ITEM", currentBatik)
            }
            context.startActivity(intent)
        }
    }
    override fun getItemCount() = batikList.size

}




