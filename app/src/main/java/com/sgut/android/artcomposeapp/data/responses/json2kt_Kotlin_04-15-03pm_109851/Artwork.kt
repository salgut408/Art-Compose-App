package com.sgut.android.artcomposeapp

import com.google.gson.annotations.SerializedName


data class Artwork (

  @SerializedName("id"             ) var id            : Int?    = null,
  @SerializedName("title"          ) var title         : String? = null,
  @SerializedName("date_start"     ) var dateStart     : Int?    = null,
  @SerializedName("artist_display" ) var artistDisplay : String? = null,
  @SerializedName("medium_display" ) var mediumDisplay : String? = null,
  @SerializedName("image_id"       ) var imageId       : String? = null

)