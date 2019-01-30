package com.example.weatherproject.remote.mapper

interface Mapper<RemoteType, DataType> {

    fun mapFromRemote(remote: RemoteType): DataType


}