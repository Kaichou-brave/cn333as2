package com.example.mynotes.models

import android.os.Parcel
import android.os.Parcelable

class NoteList(val name: String, val Notes: ArrayList<String> = ArrayList()) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.createStringArrayList()!!
    ) {
    }

    override fun describeContents() = 0

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeStringList(Notes)
    }

    companion object CREATOR : Parcelable.Creator<NoteList> {
        override fun createFromParcel(parcel: Parcel): NoteList {
            return NoteList(parcel)
        }

        override fun newArray(size: Int): Array<NoteList?> {
            return arrayOfNulls(size)
        }
    }
}