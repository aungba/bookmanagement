package com.bookmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookmanagement.dao.BookDao;
import com.bookmanagement.entity.Book;

/**
 * This is BookService class
 * 
 * @author Aung Ba
 *
 */
@Service
public class BookService {

	/**
	 * declare bookDao
	 */
	@Autowired
	private BookDao bookDao;

	/**
	 * This method is used to retrieve all bookList from db
	 * 
	 * @return bookList
	 */
	public List<Book> getAllBookList() {
		return bookDao.getAllBookList();
	}

	/**
	 * This method is used to retrieve book by book id from db
	 * 
	 * @param book_id book id
	 * @return book
	 */
	public Book getBookById(int book_id) {
		return this.bookDao.getBookById(book_id);
	}

	/**
	 * This method is used to add book into db
	 * 
	 * @param book book
	 * @return 0 or 1
	 */
	public int addBook(Book book) {
		return this.bookDao.addBook(book);
	}

	/**
	 * This method is used to update book by id into db
	 * 
	 * @param book    book
	 * @param book_id book id
	 * @return 0 or 1
	 */
	public int updateBook(Book book, int book_id) {
		book.setBook_id(book_id);
		return this.bookDao.updateBook(book);
	}

	/**
	 * This method is used to delete book by id from db
	 * 
	 * @param id book id
	 * @return 0 or 1
	 */
	public int deleteBook(int id) {
		return this.bookDao.deleteBook(id, true);

	}

	/**
	 * This method is used to search the book
	 * 
	 * @param book book
	 * @param status 
	 * @return bookList
	 */
	public List<Book> searchBook(Book book, Boolean status) {
		return this.bookDao.searchBook(book, status);
	}

}
