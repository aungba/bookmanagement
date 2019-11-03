import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource, MatDialog, MatSlideToggleChange } from '@angular/material';
import { Router } from '@angular/router';
import { ErrorDialogComponent } from '@app/dialog/error-dialog/error-dialog.component';
import { User } from '@app/entity/user';
import { BookRegisterDialogComponent } from '@app/dialog/book-register-dialog/book-register-dialog.component';
import { BorrowDialogComponent } from '@app/dialog/borrow-dialog/borrow-dialog.component';
import { DeleteDialogComponent } from '@app/dialog/delete-dialog/delete-dialog.component';
import { Book } from '@app/entity/book';
import { BookService } from '@service/book.service';
import { CategoryService } from '@service/category.service';
import { AuthService } from '@service/auth.service';
import { Category } from '@entity/category';
import { Gender } from '@app/entity/gender';

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.scss']
})
export class BookComponent implements OnInit {
  displayedColumns: string[] = ['id', 'title', 'author', 'publisher', 'category', 'release_date', 'status', 'available', 'rent',
    'operation'];
  dataSource: MatTableDataSource<Book>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  user: User;
  userList: User[];
  categoryList: Category[];
  book: Book;
  title: string;
  author: string;
  publisher: string;
  category_id: number;
  rent_status: boolean | string;
  book_status: string;
  autoRenew: Boolean;

  rentalStatusList: Gender[] = [
    { id: true, value: 'Available' },
    { id: false, value: 'OnLoan' }
  ];

  bookStatusList: Gender[] = [
    { id: 'Available', value: 'Available' },
    { id: 'Closed', value: 'Closed' }
  ];

  // toogle_slide = new FormControl();

  constructor(
    private dialog: MatDialog,
    private router: Router,
    private bookService: BookService,
    private categoryService: CategoryService,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.autoRenew = false;
    this.search();
  }

  register() {
    this.categoryService.getAllCategoryList().subscribe(
      data => {
        this.categoryList = data;
        const dialogRef = this.dialog.open(BookRegisterDialogComponent, {
          data: {
            book: new Book(),
            category: this.categoryList
          }
        });
        dialogRef.afterClosed().subscribe(
          result => {
            if (result) {
              this.bookService.addBook(result).subscribe(
                response => {
                  this.search();
                },
                err => {
                  this.checkTokenState(err);
                }
              );
            }
          }
        );
      },
      err => {
        this.checkTokenState(err);
      }
    );
  }

  edit(id: number) {
    this.bookService.getBookById(id).subscribe(
      result => {
        this.categoryService.getAllCategoryList().subscribe(
          data => {
            this.categoryList = data;
            const dialogRef = this.dialog.open(BookRegisterDialogComponent, {
              data: {
                book: result,
                category: this.categoryList
              }
            });
            dialogRef.afterClosed().subscribe(
              updateData => {
                if (updateData) {
                  this.book = updateData;
                  if (this.book.book_id) {
                    this.bookService.updateBook(this.book.book_id, this.book).subscribe(
                      response => {
                        this.search();
                      }, err => {
                        this.checkTokenState(err);
                      }
                    );
                  }
                }
              }
            );
          }, err => {
            this.checkTokenState(err);
          }
        );
      }, err => {
        this.checkTokenState(err);
      }
    );
  }

  borrow(id: number, is_borrow: Boolean) {
    this.bookService.getBookById(id).subscribe(
      result => {
        this.authService.getUserList().subscribe(
          data => {
            this.userList = data;
            if (!is_borrow) {
              this.bookService.getBorrowBookById(id).subscribe(
                borrowList => {
                  const dialogRef = this.dialog.open(BorrowDialogComponent, {
                    data: {
                      book: result,
                      userList: this.userList,
                      is_borrow: is_borrow,
                      user_id: borrowList['user']['id']
                    }
                  });
                  dialogRef.afterClosed().subscribe(
                    loan_data => {
                      if (loan_data) {
                        this.bookService.changeBook(loan_data.id, loan_data.user_id, loan_data.status).subscribe(
                          response => {
                            this.search();
                          }, err => {
                            this.checkTokenState(err);
                          }
                        );
                      }
                    }
                  );
                }, err => {
                  this.checkTokenState(err);
                }
              );
            } else {
              const dialogRef = this.dialog.open(BorrowDialogComponent, {
                data: {
                  book: result,
                  userList: this.userList,
                  is_borrow: is_borrow,
                  user_id: 0
                }
              });
              dialogRef.afterClosed().subscribe(
                loan_data => {
                  if (loan_data) {
                    this.bookService.changeBook(loan_data.id, loan_data.user_id, loan_data.status).subscribe(
                      response => {
                        this.search();
                      }, err => {
                        this.checkTokenState(err);
                      }
                    );
                  }
                }
              );
            }
          }, err => {
            this.checkTokenState(err);
          }
        );
      }, err => {
        this.checkTokenState(err);
      }
    );
  }

  delete(id: number) {
    this.bookService.getBookById(id).subscribe(
      result => {
        const dialogRef = this.dialog.open(DeleteDialogComponent, {
          data: {
            book: result
          }
        });
        dialogRef.afterClosed().subscribe(
          book => {
            if (book) {
              this.book = book;
              this.bookService.deleteBook(this.book.book_id).subscribe(
                response => {
                  this.search();
                }, err => {
                  this.checkTokenState(err);
                }
              );
            }
          }
        );
      }, err => {
        this.checkTokenState(err);
      }
    );
  }

  search() {
    const query = this.generateQuery(this.title, this.author, this.publisher,
      this.category_id, this.rent_status, this.book_status);
    this.getBookList(query);
  }

  resetSearch() {
    this.title = '';
    this.author = '';
    this.publisher = '';
    this.category_id = 0;
    this.rent_status = '';
    this.book_status = '';
    this.search();
  }

  toggleClick(value: MatSlideToggleChange) {
    if (!this.autoRenew) {
      this.bookService.getOverDueDate().subscribe(
        data => {
          this.categoryService.getAllCategoryList().subscribe(
            response => {
              this.categoryList = response;
              this.dataSource = new MatTableDataSource(data);
              this.dataSource.sort = this.sort;
              this.dataSource.paginator = this.paginator;
            }, err => {
              this.checkTokenState(err);
            }
          );
        }, err => {
          this.checkTokenState(err);
        }
      );
    } else {
      this.search();
    }
  }

  getBookList(query: string) {
    this.bookService.getBookList(query).subscribe(
      data => {
        this.categoryService.getAllCategoryList().subscribe(
          response => {
            this.categoryList = response;
            this.dataSource = new MatTableDataSource(data);
            this.dataSource.sort = this.sort;
            this.dataSource.paginator = this.paginator;
          }, err => {
            this.checkTokenState(err);
          }
        );
      }, err => {
        this.checkTokenState(err);
      }
    );
  }

  generateQuery(title: string, author: string, publisher: string, category_id: number,
    rent_status: boolean | string, book_status: string) {
    const search_arr = [title, author, publisher, category_id, rent_status, book_status];
    const search_txt = ['title', 'author', 'publisher', 'category', 'status', 'isAvailable'];
    let query = '';
    let generateQuery = '';
    search_arr.forEach(function (value, index) {
      if (value || value === false) {
        query += search_txt[index] + '=' + value;
        query += '&';
      }
    });

    if (query !== '') {
      const slice_query = query.slice(0, -1);
      generateQuery += '?' + slice_query;
    }
    return generateQuery;
  }

  checkTokenState(err) {
    let title = '';
    let content = '';
    let isLogout = false;
    if (err.status === 401 && err.statusText === 'Unauthorized') {
      title = 'Error';
      content = 'Your session was expired.';
      isLogout = true;

    } else if (err.status === 403) {
      title = 'Error';
      content = err.error.detail;
      isLogout = true;
    } else {
      title = 'Error';
      content = err.error.message;
      isLogout = false;
    }
    const dialogRef = this.dialog.open(ErrorDialogComponent, {
      data: {
        title: title,
        content: content
      }
    });
    dialogRef.afterClosed().subscribe(
      isClosed => {
        if (isClosed) {
          if (isLogout) {
            this.authService.removeToken();
            localStorage.removeItem('user_name');
            this.router.navigate(['/login']);
          }

        }
      }
    );
  }

  getCategoryText(category_id) {
    for (const index in this.categoryList) {
      if (this.categoryList[index].category_id === category_id) {
        return this.categoryList[index].category_txt;
      }
    }
  }

}
