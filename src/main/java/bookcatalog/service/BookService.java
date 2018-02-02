package bookcatalog.service;

import bookcatalog.model.Book;

import java.util.List;

public interface BookService {
    List<Book> listBooks();
    void addBook(Book book);
    Book getBookById(int id);
    void deleteBook(int id);
    void readBook(int id);
    void updateBook(Book book);
    List<Book> searchToTitle(String title);
}