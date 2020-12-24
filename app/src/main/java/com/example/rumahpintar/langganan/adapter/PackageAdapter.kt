package com.example.rumahpintar.langganan.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.rumahpintar.langganan.Currency
import com.example.rumahpintar.langganan.model.Package
import com.example.rumahpintar.R

class PackageAdapter(
    val context: Context,
    var dataset: List<Package.Subscribe>
) : RecyclerView.Adapter<PackageAdapter.ViewHolder>() {

    private lateinit var onItemClickListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(model: Package.Subscribe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_package, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataset[position]
        holder.bind(data, onItemClickListener)
        holder.namePackageText.text = data.name
        holder.usersPackageText.text = "${data.users} pengguna"
        holder.pricePackageButton.text = Currency.intToRupiah(data.price!!)
//        holder.pricePackageButton.setOnClickListener {
//            Toast.makeText(context, "Ini dari dalam adapter", Toast.LENGTH_SHORT).show()
//        }

    }

    override fun getItemCount(): Int = dataset.size

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var namePackageText: TextView = itemView.findViewById(R.id.text_name_package)
        var usersPackageText: TextView = itemView.findViewById(R.id.text_users_package)
        var pricePackageButton: Button = itemView.findViewById(R.id.button_price_package)

        fun bind(model: Package.Subscribe, listener: OnItemClickListener) {
            pricePackageButton.setOnClickListener {
                listener.onItemClick(model)
            }
        }
    }
}