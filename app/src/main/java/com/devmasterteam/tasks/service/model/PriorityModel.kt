package com.devmasterteam.tasks.service.model

import com.google.gson.annotations.SerializedName

data class PriorityModel (

    @SerializedName("Id")
    private var id: Int = 0,

    @SerializedName("Description")
    private var description: String = ""
)

//    [
//    {
//        "Id": 1,
//        "Description": "Baixa"
//    },
//    {
//        "Id": 2,
//        "Description": "Média"
//    },
//    {
//        "Id": 3,
//        "Description": "Alta"
//    },
//    {
//        "Id": 4,
//        "Description": "Crítica"
//    }
//    ]