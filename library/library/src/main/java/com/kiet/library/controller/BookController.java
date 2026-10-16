package com.kiet.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kiet.library.model.Book;
import com.kiet.library.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable int bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable int bookId, @RequestBody Book updatedBook) {
        return bookService.updateBook(bookId, updatedBook);
    }

    @DeleteMapping("/{bookId}")
    public String deleteBook(@PathVariable int bookId) {
        if (bookService.deleteBook(bookId)) {
            return "Book deleted successfully";
        }
        return "Book not found";
    }

    @GetMapping("/search")
    public List<Book> searchByCategory(@RequestParam String category) {
        return bookService.searchByCategory(category);
    }

    @GetMapping("/published")
    public List<Book> findPublishedFromYear(@RequestParam int fromYear) {
        return bookService.findPublishedFromYear(fromYear);
    }
}
