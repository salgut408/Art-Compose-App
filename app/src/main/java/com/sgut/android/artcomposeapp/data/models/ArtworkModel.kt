package com.sgut.android.artcomposeapp.data.models

import com.google.gson.annotations.SerializedName

data class ArtworkModel(

   var id            : Int?    = null,
    var title         : String? = null,
     var dateStart     : Int?    = null,
    var artistDisplay : String? = null,
     var mediumDisplay : String? = null,
     var imageId       : String? = null
) {
 fun getOtherImgUrl(): String {
  val urlForDisplay = "https://www.artic.edu/iiif/2/${imageId}/full/843,/0/default.jpg"
  return urlForDisplay
 }
}




