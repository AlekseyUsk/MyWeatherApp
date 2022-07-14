package com.hfad.myweatherapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.BufferedReader
import java.io.Reader
import java.util.stream.Collectors

class Utils {

}

@RequiresApi(Build.VERSION_CODES.N)
private fun getLines(reader: BufferedReader): String {
    return reader.lines().collect(Collectors.joining("\n"))
}