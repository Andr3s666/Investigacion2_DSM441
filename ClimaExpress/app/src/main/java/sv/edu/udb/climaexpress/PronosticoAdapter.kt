package sv.edu.udb.climaexpress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PronosticoAdapter(private val lista: List<CityWeatherResponse>) :
    RecyclerView.Adapter<PronosticoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val icon: ImageView = view.findViewById(R.id.imageViewIcon)
        val city: TextView = view.findViewById(R.id.textViewCity)
        val temp: TextView = view.findViewById(R.id.textViewTemp)
        val description: TextView = view.findViewById(R.id.textViewDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_clima, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = lista.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]

        holder.city.text = item.name
        holder.temp.text = "${item.main.temp.toInt()}°C"
        holder.description.text = item.weather[0].description

        val iconUrl = "https://openweathermap.org/img/wn/${item.weather[0].icon}@2x.png"
        Glide.with(holder.itemView.context).load(iconUrl).into(holder.icon)
    }
}
