package com.example.roomdaodatabase

import androidx.room.TypeConverter
import java.math.BigDecimal

class Converters {
    @TypeConverter
    fun stringToPrice(value: String): BigDecimal {
        return BigDecimal(value)
    }

    @TypeConverter
    fun priceToString(price: BigDecimal): String {
        return price.toString()
    }
}