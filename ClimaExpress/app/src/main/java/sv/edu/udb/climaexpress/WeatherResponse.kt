package sv.edu.udb.climaexpress

data class WeatherResponse(
    val name: String,             // Nombre de la ciudad
    val main: Main,               // Datos principales (temperatura)
    val weather: List<Weather>    // Lista con descripción e ícono
)

data class Main(
    val temp: Double              // Temperatura actual
)

data class Weather(
    val icon: String,             // Código del ícono
    val description: String       // Descripción del clima
)