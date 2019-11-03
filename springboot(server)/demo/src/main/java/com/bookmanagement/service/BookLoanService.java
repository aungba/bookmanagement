package com.bookmanagement.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bookmanagement.dao.BookDao;
import com.bookmanagement.dao.BookLoanDao;
import com.bookmanagement.entity.Book;
import com.bookmanagement.entity.BookChange;
import com.bookmanagement.entity.BookLoan;
import com.bookmanagement.entity.ResponseMessage;
import com.bookmanagement.entity.UserInfo;
import com.bookmanagement.util.DateUtil;

/**
 * This is BookLoanService class
 * 
 * @author Aung Ba
 *
 */
@Service
public class BookLoanService {

	/**
	 * declare max loan
	 */
	private static final int MAX_LOAN = 3;

	/**
	 * declare due date
	 */
	private static final int DUE_DATE = 3;

	/**
	 * declare over due date
	 */
	private static final int OVER_DUE_DATE = 14;

	/**
	 * declare bookLoanDao
	 */
	@Autowired
	private BookLoanDao bookLoanDao;

	/**
	 * declare bookDao
	 */
	@Autowired
	private BookDao bookDao;

	/**
	 * declare dateUtil
	 */
	@Autowired
	private DateUtil dateUtil;

	/**
	 * This method is used to check if the user can be borrowable or not
	 * 
	 * @param user_id user id
	 * @return true or false
	 */
	public boolean isBorrowable(int user_id) {
		return this.bookLoanDao.getBookLoanCountByUserId(user_id, true) < MAX_LOAN;
	}

	/**
	 * This method is used to change book loan state
	 * 
	 * @param bookChange bookChange
	 * @return ResponseEntity
	 */
	public ResponseEntity<Object> changeBookLoanState(BookChange bookChange) {
		Book book = new Book();
		BookLoan bookLoan = new BookLoan();
		UserInfo userInfo = new UserInfo();
		if (bookChange.getStatus() == 0) {

			if (isBorrowable(bookChange.getUser_id())) {
				book.setStatus(false);
				book.setRent_date(java.sql.Date.valueOf(this.dateUtil.formatDate(new Date())));
				book.setBook_id(bookChange.getId());
				this.bookDao.updateBookState(book);

				userInfo.setId(bookChange.getUser_id());
				bookLoan.setBook(book);
				bookLoan.setUser(userInfo);
				bookLoan.setRent_date(java.sql.Date.valueOf(this.dateUtil.formatDate(new Date())));
				bookLoan.setDue_date(java.sql.Date.valueOf(this.dateUtil.formatDate(this.dateUtil.getDate(DUE_DATE))));
				bookLoan.set_loan(true);
				this.bookLoanDao.addLoan(bookLoan);
				return new ResponseEntity<Object>(new ResponseMessage("OK"), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>(new ResponseMessage("User already borrowed the limited number of books"),
						HttpStatus.BAD_REQUEST);
			}
		} else {
			book.setStatus(true);
			book.setRent_date(null);
			book.setBook_id(bookChange.getId());
			this.bookDao.updateBookState(book);
			bookLoan.set_loan(false);
			this.bookLoanDao.changeLoanState(bookChange.getId(), bookChange.getUser_id(), true, false);
			return new ResponseEntity<Object>(new ResponseMessage("OK"), HttpStatus.OK);
		}

	}

	/**
	 * This method is used to retrieve bookLoan by id from db
	 * 
	 * @param bookId book id
	 * @return bookLoan
	 */
	public BookLoan getBookLoanByBookId(int bookId) {
		BookLoan bookLoan = this.bookLoanDao.getBookLoanByBookId(bookId, true);
		bookLoan.getUser().setPassword(null);;
		return bookLoan;
	}

	/**
	 * This method is used to get over due date from db
	 * 
	 * @return bookLoanList
	 */
	public List<BookLoan> getOverDueDate() {
		return this.bookLoanDao.getOverDueDate(this.dateUtil.getDate(OVER_DUE_DATE), true);
	}

}
