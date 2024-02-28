package com.example.tasklists.models

import android.os.Parcel
import android.os.Parcelable

data class Objective(
    var description: String?,
    var isDone: Boolean
) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeByte(if (isDone) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Objective> {
        override fun createFromParcel(parcel: Parcel): Objective {
            return Objective(parcel)
        }

        override fun newArray(size: Int): Array<Objective?> {
            return arrayOfNulls(size)
        }
    }
}