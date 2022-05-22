package com.example.juanmartinezleonwheaterapp.helper

import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*

class GlobalUtilites {
    fun stringToCapitalFirst(string: String): String {
        return string.substring(0, 1).uppercase(Locale.getDefault()) + string.substring(1)
            .lowercase(Locale.getDefault());
    }

    fun convertToCelcius(kelvinDegrees: Double?): String? {
        val celciusDegrees = kelvinDegrees?.minus(273.15)
        return stringToDecimalFormat(celciusDegrees)
    }

    fun convertToFarenheit(kelvinDegrees: Double?): String? {
        val farenheitDegrees = kelvinDegrees?.minus(273.15)?.times(9)?.div(5)?.plus(32)
        return stringToDecimalFormat(farenheitDegrees)
    }

    fun stringToDecimalFormat(double: Double?): String? {
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.DOWN
        return df.format(double)
    }
}