package com.bookmanagement.restcontroller;

import java.util.List;

import javax.validation.Valid;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmanagement.entity.BookChange;
import com.bookmanagement.entity.BookLoan;
import com.bookmanagement.service.BookLoanService;

/**
 * This is BookLoanController class
 * 
 * @author Aung Ba
 *
 */
@RestController
@RequestMapping(path = "/bookLoan")
public class BookLoanController {

	/**
	 * declare bookLoanService
	 */
	@Autowired
	private BookLoanService bookLoanService;

	/**
	 * This method is used to change the bookLoan state(available or loan)
	 * 
	 * @param bookChange bookChange
	 * @return ResponseEntity
	 */
	@PostMapping("/state")
	public ResponseEntity<Object> changeBookLoanState(@Valid @RequestBody BookChange bookChange) {
		System.out.println("Calling" + bookChange.getStatus());
		return this.bookLoanService.changeBookLoanState(bookChange);
	}

	/**
	 * This method is used to get bookLoan by id
	 * 
	 * @param bookLoanId book loan id
	 * @return bookLoan
	 */
	@GetMapping("/{id}/loan")
	public BookLoan getBookLoanById(@PathVariable("id") int bookLoanId) {
		return this.bookLoanService.getBookLoanByBookId(bookLoanId);
	}

	/**
	 * This method is used to retrieve object which is over due date(over two weeks
	 * past)
	 * 
	 * @return bookLoanList
	 */
	@GetMapping("/get_over_due_date")
	public List<BookLoan> getOverDueDate() {
		return this.bookLoanService.getOverDueDate();
	}

}
