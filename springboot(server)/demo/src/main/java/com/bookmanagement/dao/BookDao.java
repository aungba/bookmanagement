package com.bookmanagement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bookmanagement.entity.Book;

/**
 * This is BookDao interface
 * 
 * @author Aung Ba
 *
 */
@Mapper
public interface BookDao {

	/**
	 * This method is used to get all book from db
	 * 
	 * @return bookList
	 */
	List<Book> getAllBookList();

	/**
	 * This method is used to get book by book_id
	 * 
	 * @param book_id book_id
	 * @return book
	 */
	Book getBookById(int book_id);

	/**
	 * This method is used to add book
	 * 
	 * @param book book
	 * @return 0 or 1
	 */
	int addBook(Book book);

	/**
	 * This method is used to update the book
	 * 
	 * @param book    book
	 * @param book_id book_id
	 * @return 0 or 1
	 */
	int updateBook(Book book, int book_id);

	/**
	 * This method is used to delete the book with book_id and status
	 * 
	 * @param book_id     book_id
	 * @param isAvailable isAvailable
	 * @return 0 or 1
	 */
	int deleteBook(@Param("book_id") int book_id, @Param("isAvailable") String isAvailable);

	/**
	 * This method is used to update the book_status
	 * 
	 * @param book    book
	 * @param book_id book_id
	 * @return 0 or 1
	 */
	int updateBookState(Book book);

	/**
	 * This method is used to search the book
	 * 
	 * @param book book
	 * @param status 
	 * @return bookList
	 */
	List<Book> searchBook(@Param("book")Book book, @Param("book_status")Boolean status);

}
