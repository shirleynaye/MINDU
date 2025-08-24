package ec.repositoriocompartido.db

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun todayDateString(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return sdf.format(Date())
}
