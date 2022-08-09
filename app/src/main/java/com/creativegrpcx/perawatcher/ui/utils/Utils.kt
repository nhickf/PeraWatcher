package com.creativegrpcx.perawatcher.ui.utils

import java.text.NumberFormat
import java.util.*

fun String.formatDecimalSeparator(): String {
    return  if (toString().isEmpty() || toString().isBlank()) toString() else NumberFormat.getNumberInstance(Locale.US).format(toString().toDouble())
}

fun String.removeComma() : Double{
    return if (toString().isEmpty() || toString().isBlank())  0.00 else  toString().replace(",","").trim().toDouble()
}
