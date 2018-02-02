package bookcatalog.controller;

import bookcatalog.model.Book;
import bookcatalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {
    private BookService bookService;

    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String listBooks(Model model, @RequestParam(required = false) Integer page) {
        PagedListHolder<Book> pagedListHolder = new PagedListHolder<Book>(bookService.listBooks());
        pagedListHolder.setPageSize(10);
        List<Book> pagedList = pagedListHolder.getPageList();
        model.addAttribute("maxPages", pagedListHolder.getPageCount());

        if(page == null || page < 1 || page > pagedListHolder.getPageCount())
            page = 1;

        model.addAttribute("page", page);

        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            model.addAttribute("listBooks" ,pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            model.addAttribute("listBooks", pagedListHolder.getPageList());
        }

        model.addAttribute("book", new Book());
        model.addAttribute("booksearch", new Book());

        return "books";
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book) {
        String author = book.getAuthor();
        if (book.getId() == 0) {
            this.bookService.addBook(book);
        } else {
            book.setAuthor(author);
            this.bookService.updateBook(book);
        }
        return "redirect:/books";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String searchBook(@ModelAttribute("book") Book book, Model model, Integer page) {
        List<Book> bookl = bookService.searchToTitle(book.getTitle());
        model.addAttribute("bookse", bookl);
        model.addAttribute("book", new Book());

        PagedListHolder<Book> pagedListHolder = new PagedListHolder<Book>(bookService.listBooks());
        pagedListHolder.setPageSize(10);
        List<Book> pagedList = pagedListHolder.getPageList();
        model.addAttribute("maxPages", pagedListHolder.getPageCount());

        if(page == null || page < 1 || page > pagedListHolder.getPageCount())
            page = 1;

        model.addAttribute("page", page);

        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            model.addAttribute("listBooks" ,pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            model.addAttribute("listBooks", pagedListHolder.getPageList());
        }

        return "books";
    }

    @RequestMapping("edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model, @RequestParam(required = false) Integer page) {
        model.addAttribute("book", this.bookService.getBookById(id));

        PagedListHolder<Book> pagedListHolder = new PagedListHolder<Book>(bookService.listBooks());
        pagedListHolder.setPageSize(10);
        List<Book> pagedList = pagedListHolder.getPageList();
        model.addAttribute("maxPages", pagedListHolder.getPageCount());

        if(page == null || page < 1 || page > pagedListHolder.getPageCount())
            page = 1;

        model.addAttribute("page", page);

        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
            pagedListHolder.setPage(0);
            model.addAttribute("listBooks" ,pagedListHolder.getPageList());
        }
        else if(page <= pagedListHolder.getPageCount()) {
            pagedListHolder.setPage(page-1);
            model.addAttribute("listBooks", pagedListHolder.getPageList());
        }

        return "books";
    }

    @RequestMapping("remove/{id}")
    public String removeBook(@PathVariable("id") int id) {
        this.bookService.deleteBook(id);
        return "redirect:/books";
    }

    @RequestMapping("read/{id}")
    public String reedBook(@PathVariable("id") int id, Model model) {
        this.bookService.readBook(id);
        model.addAttribute("book", this.bookService.getBookById(id));
        return "redirect:/books";
    }
}
