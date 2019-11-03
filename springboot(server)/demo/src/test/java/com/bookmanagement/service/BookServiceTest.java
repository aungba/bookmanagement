package com.bookmanagement.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.bookmanagement.dao.BookDao;
import com.bookmanagement.entity.Book;
import com.bookmanagement.entity.Category;
import com.bookmanagement.util.DateUtil;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {
	
	@Mock
	private BookDao bookDao;
	
	@Mock
	private DateUtil dateUtil;
	
	@InjectMocks
	private BookService bookService;

	@Test
	public void testGetAllBookList_normal() {
		Category category = new Category(1, "test");
		Book book = new Book(1,"test","test","test","testing",java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())), true, "isAvailable", category, java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())));
		List<Book> expected_result = new ArrayList<Book>();
		expected_result.add(book);
		
		doReturn(expected_result).when(bookDao).getAllBookList();
		
		List<Book> actual_result = bookService.getAllBookList();
		assertThat(actual_result).isEqualTo(expected_result);
	}
	
	@Test
	public void testGetBookById_normal() {
		Category category = new Category(1, "test");
		Book book = new Book(2,"test","test","test","testing",java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())), true, "isAvailable", category, java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())));
		
		doReturn(book).when(bookDao).getBookById(2);
		
		Book actual_result = bookService.getBookById(2);
		assertThat(book).isEqualTo(actual_result);
	}
	
	@Test
	public void testDeleteBook_normal() {
		int expected_result = 1;
		doReturn(expected_result).when(bookDao).deleteBook(1, "isAvailable");
		
		int actual_result = bookService.deleteBook(1);
		assertThat(actual_result).isEqualTo(expected_result);
	}
	
	@Test
	public void testUpdateBook_normal() {
		int expected_result = 1;
		int book_id = 2;
		
		Category category = new Category(1, "test");
		Book book = new Book(2,"test","test","test","testing",java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())), true, "isAvailable", category, java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())));
		doReturn(expected_result).when(bookDao).updateBook(book, book_id);
		
		int actual_result = bookService.updateBook(book, book_id);
		assertThat(actual_result).isEqualTo(expected_result);
	}
	
	@Test
	public void testAddBook_normal() {
		int expected_result = 1;
		Category category = new Category(1, "test");
		Book book = new Book(2,"test","test","test","testing",java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())), true, "isAvailable", category, java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())));
		doReturn(expected_result).when(bookDao).addBook(book);
		
		int actual_result = bookService.addBook(book);
		assertThat(actual_result).isEqualTo(expected_result);
	}
	
	@Test
	public void testSearchBook_normal() {
		Category category = new Category(1, "test");
		Book book = new Book(1,"test","test","test","testing",java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())), true, "isAvailable", category, java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())));
		List<Book> expected_result = new ArrayList<Book>();
		expected_result.add(book);
		
		doReturn(expected_result).when(bookDao).searchBook(book, null);
		List<Book> actual_result = bookService.searchBook(book, null);
		assertThat(actual_result).isEqualTo(expected_result);
	}

}
