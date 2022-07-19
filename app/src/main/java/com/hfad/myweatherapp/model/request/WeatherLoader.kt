package com.hfad.myweatherapp.model.request

import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.hfad.myweatherapp.model.dto.WeatherDTO
import com.hfad.myweatherapp.utils.getLines
import com.hfad.myweatherapp.view.weatherlist.details.OnResponse
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object WeatherLoader : OnResponse {
// 3 - ссылка на адресс запроса
    @RequiresApi(Build.VERSION_CODES.N)
    fun requestLoader(lat: Double, lon: Double, onResponse: OnResponse) {
        val uri =
            URL("https://api.weather.yandex.ru/v2/forecast?lat=${lat}&lon=$lon}")


        var myConnection: HttpURLConnection? = null
// 4 - открываем конект устанавливаем атрибуты(ключ и тп)
        myConnection = uri.openConnection() as HttpURLConnection
        myConnection.readTimeout = 3000
        myConnection.addRequestProperty(
            "X-Yandex-API-Key",
            "dcc58378-25de-4d0c-abc7-a2f5d79cabc1"
        )
        val handler =
            Handler(Looper.myLooper()!!)
        Thread {
            val reader = BufferedReader(InputStreamReader(myConnection.inputStream)) // 5 - читаем поток запроса

            val weatherDTO = Gson().fromJson( // 6 - конвертируем в WeatherDTO при помощи Gson()
                getLines(reader),
                WeatherDTO::class.java)
                onResponse.onResponse(weatherDTO) // 7 - этот ответ ворачиваем в onResponse колбек

        }.start()

    }
}