package sv.edu.udb.climaexpress

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ClimaAdapter(private val listaClima: List<CityWeatherResponse>) :
    RecyclerView.Adapter<ClimaAdapter.ClimaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClimaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_clima, parent, false)
        return ClimaViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClimaViewHolder, position: Int) {
        val item = listaClima[position]

        holder.city.text = item.name
        holder.temp.text = "${item.main.temp.toInt()}°C"
        holder.description.text = item.weather[0].description

        val iconUrl = "https://openweathermap.org/img/wn/${item.weather[0].icon}@2x.png"
        Glide.with(holder.itemView.context).load(iconUrl).into(holder.icon)
    }

    override fun getItemCount(): Int = listaClima.size

    class ClimaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val city: TextView = view.findViewById(R.id.textViewCity)
        val temp: TextView = view.findViewById(R.id.textViewTemp)
        val description: TextView = view.findViewById(R.id.textViewDescription)
        val icon: ImageView = view.findViewById(R.id.imageViewIcon)
    }
}