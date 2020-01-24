package br.com.edumaxsantos.rest_tutorial_mongodb.dto

data class PetsDto(
        val _id: String,
        val name: String,
        val species: String,
        val breed: String
)