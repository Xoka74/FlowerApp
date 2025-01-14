package com.shurdev.trade.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MyPlantPresentation(
    val name: String,
    val imageData: ByteArray?,
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MyPlantPresentation

        if (name != other.name) return false
        if (imageData != null) {
            if (other.imageData == null) return false
            if (!imageData.contentEquals(other.imageData)) return false
        } else if (other.imageData != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + (imageData?.contentHashCode() ?: 0)
        return result
    }
}