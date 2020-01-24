package br.com.edumaxsantos.rest_tutorial_mongodb.repositories

import br.com.edumaxsantos.rest_tutorial_mongodb.models.Pets
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PetsRepository: MongoRepository<Pets, String> {
    fun findBy_id(_id: ObjectId): Optional<Pets>
}