package com.kiet.library.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kiet.library.model.Book;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();

    public BookService() {
        books.add(new Book(201, "Effective Java", "Joshua Bloch", "Programming", 2018, true));
        books.add(new Book(202, "Clean Architecture", "Robert C. Martin", "Software Design", 2017, true));
        books.add(new Book(203, "The Pragmatic Programmer", "Andrew Hunt", "Programming", 1999, false));
        books.add(new Book(204, "Introduction to Algorithms", "Thomas H. Cormen", "Algorithms", 2009, true));
        books.add(new Book(205, "Design Patterns", "Erich Gamma", "Software Design", 1994, true));
    }

    public List<Book> getAllBooks() {
        return books;
    }

    // Returns null when no book has the given ID
    public Book getBookById(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        return null;
    }

    public Book addBook(Book book) {
        books.add(book);
        return book;
    }

    // Returns null when no book has the given ID
    public Book updateBook(int bookId, Book updatedBook) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                book.setTitle(updatedBook.getTitle());
                book.setAuthor(updatedBook.getAuthor());
                book.setCategory(updatedBook.getCategory());
                book.setPublicationYear(updatedBook.getPublicationYear());
                book.setAvailable(updatedBook.isAvailable());
                return book;
            }
        }
        return null;
    }

    // Returns false when no book has the given ID
    public boolean deleteBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                books.remove(book);
                return true;
            }
        }
        return false;
    }

    public List<Book> searchByCategory(String category) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findPublishedFromYear(int fromYear) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getPublicationYear() >= fromYear) {
                result.add(book);
            }
        }
        return result;
    }
}
