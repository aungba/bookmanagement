package com.bookmanagement.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bookmanagement.entity.BookLoan;

/**
 * This is bookLoanDao interface
 * @author Aung Ba
 *
 */
@Mapper
public interface BookLoanDao {
	
	/**
	 * This method is used to getBookLoanCount by user_id from db
	 * @param user_id user_id
	 * @param is_loan loan status
	 * @return totalCount
	 */
	int getBookLoanCountByUserId(@Param("user_id") int user_id, @Param("is_loan") boolean is_loan);

	/**
	 * This method is used to add Loan into db
	 * @param bookLoan bookLoan
	 */
	int addLoan(BookLoan bookLoan);

	/**
	 * This method is used to change loan state
	 * @param book_id book_id
	 * @param user_id user_id
	 * @param is_loan loan status
	 * @param update_loan 
	 * @param bookLoan bookLoan
	 */
	int changeLoanState(@Param("book_id") int book_id, @Param("user_id") int user_id,@Param("loan_state") boolean is_loan, @Param("loan") boolean update_loan);

	/**
	 * This method is used to getBookLoan by book_id and loan state
	 * @param bookId bookId
	 * @param is_loan loan status
	 * @return BookLoan
	 */
	BookLoan getBookLoanByBookId(@Param("book_id")int bookId, @Param("loan_state") boolean is_loan);

	/**
	 * This method is used to get books that is over due date(over two weeks past)
	 * @param date over due date
	 * @param is_loan loan status
	 * @return BookLoanList
	 */
	List<BookLoan> getOverDueDate(@Param("due_date")Date date, @Param("loan_state") boolean is_loan);

}
