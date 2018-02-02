package bookcatalog.dao;

import bookcatalog.model.Book;

import java.util.List;

public interface BookDao {
    List<Book> listBooks();
    void addBook(Book book);
    Book getBookById(int id);
    void deleteBook(int id);
    void readBook(int id);
    void updateBook(Book book);
    List<Book> searchToTitle(String title);
}