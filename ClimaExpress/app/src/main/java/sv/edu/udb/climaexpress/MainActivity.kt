package sv.edu.udb.climaexpress

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import sv.edu.udb.climaexpress.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val apiKey = "2bcdf0d7ce1e4a374f9a5d18773f92de"
    private val ciudades = listOf("San Salvador", "Santa Ana", "San Miguel", "Cojutepeque")
    private val resultados = mutableListOf<CityWeatherResponse>()
    private lateinit var adapter: ClimaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ClimaAdapter(resultados)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.swipeRefresh.setOnRefreshListener {
            cargarClima()
        }

        cargarClima()
    }

    private fun cargarClima() {
        resultados.clear()
        binding.progressBar.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.Main).launch {
            try {
                for (ciudad in ciudades) {
                    val response = WeatherClient.service.getWeather(
                        city = ciudad,
                        apiKey = apiKey,
                        units = "metric",
                        lang = "es"
                    )

                    Log.d("CLIMA", "Consultando ciudad: $ciudad")

                    if (response.isSuccessful && response.body() != null) {
                        val clima = response.body()!!
                        Log.d("CLIMA", "Respuesta completa: $clima")
                        resultados.add(clima)
                    } else {
                        Log.e("CLIMA", "Error en respuesta: ${response.code()} - ${response.message()}")
                    }
                }

                Log.d("CLIMA", "Total ciudades cargadas: ${resultados.size}")

                if (resultados.isNotEmpty()) {
                    val clima = resultados[0]
                    binding.textViewTemp.text = "${clima.main.temp.toInt()}°C"
                    binding.textViewDescription.text = clima.weather[0].description
                    val iconUrl = "https://openweathermap.org/img/wn/${clima.weather[0].icon}@2x.png"
                    Glide.with(this@MainActivity).load(iconUrl).into(binding.imageViewIcon)
                } else {
                    Log.e("CLIMA", "No se recibió clima para ninguna ciudad")
                }

                adapter.notifyDataSetChanged()
                binding.recyclerView.visibility = View.VISIBLE

            } catch (e: Exception) {
                Log.e("CLIMA", "Excepción: ${e.message}")
                Toast.makeText(this@MainActivity, getString(R.string.error_red), Toast.LENGTH_SHORT).show()
            }

            binding.progressBar.visibility = View.GONE
            binding.swipeRefresh.isRefreshing = false
        }
    }
}