package com.sgut.android.artcomposeapp.data.responses.`json2kt_Kotlin_04-15-03pm_109851`

import com.sgut.android.artcomposeapp.Artwork
import com.sgut.android.artcomposeapp.data.models.ArtworkModel
import com.sgut.android.artcomposeapp.data.remote.DomainMapper

//class ArtworkDtoMapper: DomainMapper<Artwork, ArtworkModel> {
//    override fun mapToDomainModel(model: Artwork): ArtworkModel {
//        return ArtworkModel(
//            artistDisplay = model.artistDisplay,
//            dateStart = model.dateStart,
//            id = model.id,
//            imageId = model.imageId,
//            mediumDisplay = model.mediumDisplay,
//            title = model.title
//        )
//    }
//
//    fun toDomainList(initial: List<Artwork>): List<ArtworkModel> {
//        return initial.map { mapToDomainModel(it) }
//    }
//}