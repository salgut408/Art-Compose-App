package com.sgut.android.artcomposeapp

import com.google.gson.annotations.SerializedName


data class ArtListResponse (

  @SerializedName("pagination" ) var pagination : Pagination?     = Pagination(),
  @SerializedName("data"       ) var artworkList      : ArrayList<Artwork> = arrayListOf(),
  @SerializedName("info"       ) var info       : Info?           = Info(),
  @SerializedName("config"     ) var config     : Config?         = Config()

)