package com.example.tztest

import android.os.Parcel
import android.os.Parcelable
import java.time.LocalDate
import java.util.Date

data class Item(
    val id: Int,
    val systolic: Int,
    val diastolic: Int,
    val pulse: Int,
    val date: String,
    val time: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(systolic)
        parcel.writeInt(diastolic)
        parcel.writeInt(pulse)
        parcel.writeString(date)
        parcel.writeString(time)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}