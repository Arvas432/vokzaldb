package com.example.vokzaldb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TrainsListAdapter : ListAdapter<Train, TrainsListAdapter.TrainViewHolder>(TrainsComparator()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainViewHolder {
        return TrainViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: TrainViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.id, current.origin, current.destination, current.time, current.way)
    }

    class TrainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ItemTime: TextView = itemView.findViewById(R.id.tablo_table_text_view_time)
        private val ItemTrainId: TextView = itemView.findViewById(R.id.train_id)
        private val ItemOrigin: TextView = itemView.findViewById(R.id.table_text_view_origin)
        private val ItemDestination: TextView = itemView.findViewById(R.id.tablo_table_text_view_destination)
        private val ItemWay: TextView = itemView.findViewById(R.id.text_view_gate)
        fun bind(id: Long, origin: String, destination: String, time: String, way: Int) {
            ItemTime.text = time
            ItemTrainId.text = id.toString()
            ItemOrigin.text = "Откуда: $origin"
            ItemDestination.text = "Куда: $destination"
            ItemWay.text = way.toString()
        }

        companion object {
            fun create(parent: ViewGroup): TrainViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.tablo_table_list_item, parent, false)
                return TrainViewHolder(view)
            }
        }
    }
    class TrainsComparator : DiffUtil.ItemCallback<Train>() {
        override fun areItemsTheSame(oldItem: Train, newItem: Train): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Train, newItem: Train): Boolean {
            return oldItem.id == newItem.id
        }
    }
}
