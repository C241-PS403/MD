package com.example.batikku.view.Model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResponseBatikItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("batik_name")
	val batikName: String? = null,

	@field:SerializedName("batik_id")
	val batikId: Int? = null,

	@field:SerializedName("batik_history")
	val batikHistory: String? = null,

	@field:SerializedName("batik_meaning")
	val batikMeaning: String? = null,

	@field:SerializedName("batik_origin")
	val batikOrigin: String? = null
)

	: Parcelable {

	constructor(parcel: Parcel) : this(
		parcel.readString(),
		parcel.readString(),
		parcel.readValue(Int::class.java.classLoader) as? Int,
		parcel.readString(),
		parcel.readString(),
		parcel.readString()
	)

	companion object : Parceler<ResponseBatikItem> {

		override fun ResponseBatikItem.write(parcel: Parcel, flags: Int) {
			parcel.writeString(image)
			parcel.writeString(batikName)
			parcel.writeValue(batikId)
			parcel.writeString(batikHistory)
			parcel.writeString(batikOrigin)
			parcel.writeString(batikMeaning)
		}

		override fun create(parcel: Parcel): ResponseBatikItem {
			return ResponseBatikItem(parcel)
		}
	}
}
