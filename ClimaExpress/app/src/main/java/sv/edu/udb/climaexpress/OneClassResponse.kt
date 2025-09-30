package sv.edu.udb.climaexpress

data class OneCallResponse(
    val current: Current,
    val daily: List<Daily>
)

data class Current(
    val temp: Double,
    val weather: List<Weather>
)

data class Daily(
    val dt: Long,
    val temp: Temp,
    val weather: List<Weather>
)

data class Temp(
    val day: Double,
    val min: Double,
    val max: Double
)

data class Weather(
    val description: String,
    val icon: String
)