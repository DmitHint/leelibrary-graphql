package org.leelibrary

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class BookController(private val bookRepository: BookRepository) {

    @QueryMapping
    fun getBooks(): List<Book> {
        return bookRepository.findAll()
    }
    // query {
    //     getBooks {
    //         id
    //         name
    //         genre
    //         authorName
    //     }
    // }

    @QueryMapping
    fun getBookById(@Argument id: Long): Book? {
        return bookRepository.findById(id).orElse(null)
    }
    // query {
    //     getBookById(id: 1) {
    //         id
    //         name
    //         genre
    //         authorName
    //     }
    // }

    @MutationMapping
    fun addBook(
        @Argument name: String,
        @Argument genre: String,
        @Argument authorName: String
    ): Book {
        val book = Book(name = name, genre = genre, authorName = authorName)
        return bookRepository.save(book)
    }
    // mutation {
    //     addBook(name: "Crime and Punishment", genre: "Literary fiction", authorName: "Fyodor Dostoevsky") {
    //         id
    //         name
    //         genre
    //         authorName
    //     }
    // }

    @MutationMapping
    fun updateBook(
        @Argument id: Long,
        @Argument name: String?,
        @Argument genre: String?,
        @Argument authorName: String?
    ): Book? {
        val existingBook = bookRepository.findById(id).orElse(null) ?: return null

        val updatedBook = existingBook.copy(
            name = name ?: existingBook.name,
            genre = genre ?: existingBook.genre,
            authorName = authorName ?: existingBook.authorName
        )

        return bookRepository.save(updatedBook)
    }
    // mutation {
    //     updateBook(id: 1, name: "New Name", genre: "New Genre", authorName: "New Author") {
    //         id
    //         name
    //         genre
    //         authorName
    //     }
    // }

    @MutationMapping
    fun deleteBook(@Argument id: Long): Boolean {
        return if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id)
            true
        } else {
            false
        }
    }
    // mutation {
    //     deleteBook(id: 1)
    // }
}
