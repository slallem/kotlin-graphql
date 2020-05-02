package com.example.kotlingraphql.resolvers

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.example.kotlingraphql.entity.FullReview
import com.example.kotlingraphql.entity.Review
import com.example.kotlingraphql.entity.Snack
import com.example.kotlingraphql.repository.ReviewRepository
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import org.springframework.stereotype.Component

@Component
class ReviewQueryResolver(val reviewRepository: ReviewRepository,
                          val mongoOperations: MongoOperations) : GraphQLQueryResolver {

    fun reviews(snackId: String): List<Review> {
        val query = Query()
        query.addCriteria(Criteria.where("snackId").`is`(snackId))
        return mongoOperations.find(query, Review::class.java)
    }

    fun allReviews(): List<FullReview> {
        val query = Query()
        //return mongoOperations.find(query, Review::class.java)
        //val list = reviewRepository.findAll()
        return mongoOperations.find(query, Review::class.java).map { item ->
            FullReview(
                    snack = getSnackFromId(snackId = item.snackId),
                    rating = item.rating,
                    text = item.text)
        }
    }

    private fun getSnackFromId(snackId: String): Snack {
        val query = Query()
        query.addCriteria(Criteria.where("_id").`is`(snackId))
        println("snackId is $snackId")
        return mongoOperations.find(query, Snack::class.java).first()
    }

}