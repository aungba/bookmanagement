package com.bookmanagement.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.bookmanagement.dao.BookDao;
import com.bookmanagement.dao.BookLoanDao;
import com.bookmanagement.entity.Book;
import com.bookmanagement.entity.BookChange;
import com.bookmanagement.entity.BookLoan;
import com.bookmanagement.entity.Category;
import com.bookmanagement.entity.UserAddress;
import com.bookmanagement.entity.UserInfo;
import com.bookmanagement.entity.UserRole;
import com.bookmanagement.util.DateUtil;

@RunWith(MockitoJUnitRunner.class)
public class BookLoanServiceTest {
	
	@Mock
	private BookLoanDao bookLoanDao;
	
	@InjectMocks
	private BookLoanService bookLoanService;
	
	@Mock
	private BookDao bookDao;
	
	@Mock
	private DateUtil dateUtil;
	
	@Test
	public void TestIsBorrowable_returnFalse_normal() {
		int user_id = 1;
		boolean expected_result = false;
		int total_book = 3;
		
		doReturn(total_book).when(bookLoanDao).getBookLoanCountByUserId(user_id, true);
		
		boolean actual_result = bookLoanService.isBorrowable(user_id);
		assertThat(actual_result).isEqualTo(expected_result);
		
	}
	
	@Test
	public void TestIsBorrowable_returnTrue_normal() {
		int user_id = 1;
		boolean expected_result = true;
		int total_book = 2;
		
		doReturn(total_book).when(bookLoanDao).getBookLoanCountByUserId(user_id, true);
		
		boolean actual_result = bookLoanService.isBorrowable(user_id);
		assertThat(actual_result).isEqualTo(expected_result);
		
	}
	
	@Test
	public void TestChangeBookLoanState_borrow_normal() {
		BookChange bookChange = new BookChange(1, 1, 1);
		ResponseEntity<Object> expected_result = new ResponseEntity<Object>("OK",HttpStatus.OK);
		Book book = new Book();
		BookLoan bookLoan = new BookLoan();
		UserInfo userInfo = new UserInfo();
		book.setStatus(false);
		book.setRent_date(java.sql.Date.valueOf(this.dateUtil.formatDate(new Date())));
		this.bookDao.updateBookState(book);
		book.setBook_id(bookChange.getId());
		userInfo.setId(bookChange.getUser_id());
		bookLoan.setBook(book);
		bookLoan.setUser(userInfo);
		bookLoan.setRent_date(java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())));
		bookLoan.setDue_date(java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getDate(3))));
		bookLoan.set_loan(true);
		this.bookLoanDao.addLoan(bookLoan);
		
//		doReturn(1).when(bookDao).updateBookState(book, bookChange.getId());
//		doReturn(1).when(bookLoanDao).addLoan(bookLoan);
		
		ResponseEntity<Object> actual_result = bookLoanService.changeBookLoanState(bookChange);
		assertThat(actual_result).isEqualTo(expected_result);
		
	}
	
	@Test
	public void TestChangeBookLoanState_return_normal() {
		BookChange bookChange = new BookChange(1, 1, 0);
		ResponseEntity<Object> expected_result = new ResponseEntity<Object>("OK",HttpStatus.OK);
		BookLoan bookLoan = new BookLoan();
		bookLoan.set_loan(false);
		
		ResponseEntity<Object> actual_result = bookLoanService.changeBookLoanState(bookChange);
		assertThat(actual_result).isEqualTo(expected_result);
		
		
	}
	
	@Test
	public void TestGetBookLoanByBookId_normal() {
		UserAddress userAddress = new UserAddress(1, "No. 33 testing", "YGN", "Myanmar", "11101");
		UserRole userRole = new UserRole(1, "ADMIN", "ADMIN role");
		Category category = new Category(1, "test");
		Book book = new Book(1,"test","test","test","testing",java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())), true, "isAvailable", category,java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())));
		UserInfo userInfo = new UserInfo(1, "aungba", "$dfed23", userRole, true, userAddress, "aungba@gmail.com");
		BookLoan expected_result = new BookLoan(1, userInfo, book, java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())), java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())), true);
		int book_id = 1;
		
		doReturn(expected_result).when(bookLoanDao).getBookLoanByBookId(book_id, true);
		BookLoan actual_result = bookLoanService.getBookLoanByBookId(book_id);
		
		assertThat(actual_result).isEqualTo(expected_result);
	}
	
	@Test
	public void TestGetOverDueDate_normal() {
		UserAddress userAddress = new UserAddress(1, "No. 33 testing", "YGN", "Myanmar", "11101");
		UserRole userRole = new UserRole(1, "ADMIN", "ADMIN role");
		Category category = new Category(1, "test");
		Book book = new Book(1,"test","test","test","testing",java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())), true, "isAvailable", category, java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())));
		UserInfo userInfo = new UserInfo(1, "aungba", "$dfed23", userRole, true, userAddress, "aungba@gmail.com");
		BookLoan bookLoan = new BookLoan(1, userInfo, book, java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())), java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getCurrentDate())), true);
		List<BookLoan> expected_result = new ArrayList<BookLoan>();
		expected_result.add(bookLoan);
		Date date = this.dateUtil.getDate(14);
		
		doReturn(expected_result).when(bookLoanDao).getOverDueDate(date, true);
		
		List<BookLoan> actual_result = bookLoanService.getOverDueDate();
		assertThat(actual_result).isEqualTo(expected_result);
	}
	
	@Test
	public void TestChangeBookLoanState_notBorrowable_normal() {
		BookChange bookChange = new BookChange(1, 1, 1);
		ResponseEntity<Object> expected_result = new ResponseEntity<Object>("OK",HttpStatus.OK);
		
		doReturn(3).when(bookLoanDao).getBookLoanCountByUserId(1, true);
		
		ResponseEntity<Object> actual_result = bookLoanService.changeBookLoanState(bookChange);
		assertThat(actual_result).isEqualTo(expected_result);
	}

}
