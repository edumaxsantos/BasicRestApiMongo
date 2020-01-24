package br.com.edumaxsantos.rest_tutorial_mongodb.controllers

import br.com.edumaxsantos.rest_tutorial_mongodb.dto.PetsDto
import br.com.edumaxsantos.rest_tutorial_mongodb.models.Pets
import br.com.edumaxsantos.rest_tutorial_mongodb.repositories.PetsRepository
import com.fasterxml.jackson.databind.util.JSONPObject
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

@RestController
@RequestMapping("/pets")
class PetsController(@Autowired val repository: PetsRepository) {

    @GetMapping("/")
    fun getAllPets():List<PetsDto> {
        return repository.findAll().map { PetsDto(it._id.toString(), it.name, it.species, it.breed) }
    }

    @GetMapping("/{id}")
    fun getPetById(@PathVariable("id") id: ObjectId): ResponseEntity<PetsDto> {
        return repository.findBy_id(id).map {
            val petDto: PetsDto = PetsDto(it._id.toString(), it.name, it.species, it.breed)
             ResponseEntity.ok(petDto)
        }.orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Pet with ID[${id}] was not found.")
        }
    }

    @PutMapping("/{id}")
    fun modifyPetById(@PathVariable("id") id: ObjectId, @Valid @RequestBody pets: Pets) {
        pets._id = id
        repository.save(pets)
    }

    @PostMapping("/")
    fun createPet(@Valid @RequestBody pets: Pets): PetsDto {
        pets._id = ObjectId.get()
        println(pets._id.toString())
        repository.save(pets)

        return PetsDto(pets._id.toString(), pets.name, pets.species, pets.breed)
    }

    @DeleteMapping("/{id}")
    fun deletePet(@PathVariable id: ObjectId) {
        repository.findBy_id(id).ifPresent {
            repository.delete(it)
        }
    }
}