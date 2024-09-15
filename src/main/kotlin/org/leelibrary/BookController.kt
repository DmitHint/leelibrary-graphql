package org.leelibrary

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class BookController(private val bookRepository: BookRepository) {

    @QueryMapping
    fun getBooks(): List<Book> {
        return bookRepository.findAll()
    }
//    query {
//        getBooks {
//            id
//            name
//            genre
//            authorName
//        }
//    }

    @QueryMapping
    fun getBookById(@Argument id: Long): Book? {
        return bookRepository.findById(id).orElse(null)
    }
//    query {
//        getBookById(id: 52) {
//            id
//            name
//            genre
//            authorName
//        }
//    }
}