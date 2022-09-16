package com.sgut.android.artcomposeapp.data.remote

interface DomainMapper<T, DomainModel> {
    fun mapToDomainModel(model: T) : DomainModel
}