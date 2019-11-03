import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { MatPaginator, MatSort, MatTableDataSource, MatDialog } from '@angular/material';
import { Category } from '@app/entity/category';
import { CategoryService } from '@service/category.service';
import { AuthService } from '@service/auth.service';
import {
  CategoryRegisterEditDialogComponent
} from '@app/category/dialog/category-register-edit-dialog/category-register-edit-dialog.component';
import { ErrorDialogComponent } from '@app/dialog/error-dialog/error-dialog.component';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.scss']
})
export class CategoryComponent implements OnInit {
  displayedColumns: string[] = ['id', 'category', 'operation'];
  dataSource: MatTableDataSource<Category>;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  category: Category;
  categoryList: Category[];

  constructor(
    private categoryService: CategoryService,
    private authService: AuthService,
    private dialog: MatDialog,
    private router: Router
  ) { }

  ngOnInit() {
    this.getCategoryList();
  }

  register() {
    const dialogRef = this.dialog.open(CategoryRegisterEditDialogComponent, {
      data: {
        category: new Category(),
        type: 'register'
      }
    });
    dialogRef.afterClosed().subscribe(
      result => {
        if (result) {
          this.categoryService.createCategory(result).subscribe(
            response => {
              this.getCategoryList();
            }, err => {
              this.checkTokenState(err);
            }
          );
        }
      }
    );
  }

  edit(id: number) {
    this.categoryService.getCategoryById(id).subscribe(
      result => {
        const dialogRef = this.dialog.open(CategoryRegisterEditDialogComponent, {
          data: {
            category: result,
            type: 'edit'
          }
        });
        dialogRef.afterClosed().subscribe(
          response => {
            if (response) {
              this.categoryService.updateCategory(response.id, response).subscribe(
                category => {
                  if (category) {
                    this.getCategoryList();
                  }
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

  delete(id: number) {
    this.categoryService.getCategoryById(id).subscribe(
      result => {
        const dialogRef = this.dialog.open(CategoryRegisterEditDialogComponent, {
          data: {
            category: result,
            type: 'delete'
          }
        });
        dialogRef.afterClosed().subscribe(
          response => {
            if (response) {
              this.categoryService.deleteCategory(response.id).subscribe(
                category => {
                    this.getCategoryList();
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

  getCategoryList() {
    this.categoryService.getAllCategoryList().subscribe(
      data => {
        this.categoryList = data;
        this.dataSource = new MatTableDataSource(this.categoryList);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      }, err => {
        this.checkTokenState(err);
      }
    );
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

}
