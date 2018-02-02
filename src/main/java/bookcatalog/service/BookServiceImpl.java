package bookcatalog.service;

import bookcatalog.dao.BookDao;
import bookcatalog.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Transactional
    public List<Book> listBooks() {
        return this.bookDao.listBooks();
    }

    @Transactional
    public void addBook(Book book) {
        this.bookDao.addBook(book);
    }

    @Transactional
    public void updateBook(Book book) {
        this.bookDao.updateBook(book);
    }

    @Transactional
    public Book getBookById(int id) {
        return this.bookDao.getBookById(id);
    }

    @Transactional
    public void deleteBook(int id) {
        this.bookDao.deleteBook(id);
    }

    @Transactional
    public void readBook(int id) {
        this.bookDao.readBook(id);
    }

    @Transactional
    public List<Book> searchToTitle(String title) {
        return this.bookDao.searchToTitle(title);
    }
}