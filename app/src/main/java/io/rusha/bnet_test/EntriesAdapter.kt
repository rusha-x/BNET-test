package io.rusha.bnet_test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat

class EntriesAdapter(private val entries: List<Entry>) : RecyclerView.Adapter<EntriesAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val entryText = view.findViewById<TextView>(R.id.entryText)
        val creationDateText : TextView =
            TODO("Здесь нужен айди верстки котрую нужно сделать")
    }
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = entries[position]
        holder.entryText.text = entry.body
        holder.creationDateText.text = dateFormat.format(entry.creationDate)

    }

    override fun getItemCount(): Int {
        return entries.size
    }




    //val firstApiFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    //val date = LocalDate.parse("2019-08-07 09:00:00" , firstApiFormat)

}