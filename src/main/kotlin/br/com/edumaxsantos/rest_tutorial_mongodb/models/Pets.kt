package br.com.edumaxsantos.rest_tutorial_mongodb.models

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id


data class Pets(
        @Id
        var _id: ObjectId?,
        var name: String,
        var species: String,
        var breed: String
)