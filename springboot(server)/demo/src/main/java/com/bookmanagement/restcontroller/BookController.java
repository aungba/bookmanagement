package com.bookmanagement.restcontroller;

import java.util.List;

import javax.validation.Valid;

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

import com.bookmanagement.entity.Book;
import com.bookmanagement.entity.Category;
import com.bookmanagement.service.BookService;

/**
 * This is BookController
 * 
 * @author Aung Ba
 *
 */
@RestController
@RequestMapping(path = "/books")
public class BookController {

	/**
	 * declare bookService
	 */
	@Autowired
	BookService bookService;

	/**
	 * This method is used to retrieve all book list from db
	 * 
	 * @return allBookList
	 */
//	@PreAuthorize("hasRole('USER')")
//	@GetMapping("/")
//	public ResponseEntity<List<Book>> getAllBookList() {
//		return ResponseEntity.ok(bookService.getAllBookList());
//	}

	/**
	 * This method is used to add book to db
	 * 
	 * @param book book
	 * @return 0 or 1
	 */
	@PostMapping("/add")
	public int addBook(@Valid @RequestBody Book book) {
		return this.bookService.addBook(book);
	}

	/**
	 * This method is used to get book by id from db
	 * 
	 * @param id book id
	 * @return book
	 */
	@GetMapping("/{id}")
	public Book getBook(@PathVariable int id) {
		return this.bookService.getBookById(id);

	}

	/**
	 * This method is used to update book by id from db
	 * 
	 * @param id   book id
	 * @param book book
	 * @return 0 or 1
	 */
	@PutMapping("/{id}")
	public int updateBook(@PathVariable int id, @Valid @RequestBody Book book) {
		return this.bookService.updateBook(book, id);
	}

	/**
	 * This method is used to delete book by id from db
	 * 
	 * @param id book id
	 * @return 0 or 1
	 */
	@DeleteMapping("/{id}")
	public int deleteBook(@PathVariable int id) {
		return this.bookService.deleteBook(id);
	}

	/**
	 * This method is used to search book
	 * 
	 * @param title       book title
	 * @param author      book author
	 * @param publisher   book publisher
	 * @param category    book category
	 * @param status      book status
	 * @param isAvailable book isAvailable
	 * @return searchedBookList
	 */
	
	@GetMapping("/")
	public List<Book> searchBook(@RequestParam(required = false) String title,
			@RequestParam(required = false) String author, @RequestParam(required = false) String publisher,
			@RequestParam(required = false) Integer category, @RequestParam(required = false) Boolean status,
			@RequestParam(required = false) String isAvailable) {
		Category categoryObj = new Category();
		categoryObj.setCategory_id(category != null ? category.intValue() : 0);
		Book book = new Book();
		book.setTitle(title);
		book.setAuthor(author);
		book.setPublisher(publisher);
		book.setCategory(categoryObj);
		book.setIsAvailable(isAvailable);

		return this.bookService.searchBook(book, status);

	}

}
