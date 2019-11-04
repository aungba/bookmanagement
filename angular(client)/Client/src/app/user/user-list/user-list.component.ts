import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { User } from '@entity/user';
import { AuthService } from '@service/auth.service';
import { UserRegisterDialogComponent } from '@app/dialog/user-register-dialog/user-register-dialog.component';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'mail', 'group', 'phone', 'role', 'operation'];
  user: User;

  dataSource: MatTableDataSource<User>;
  @ViewChild(MatPaginator, {static: false}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: false}) sort: MatSort;

  constructor(
    private authService: AuthService,
    private dialog: MatDialog) { }

  ngOnInit() {
    this.authService.getUserList().subscribe(
      data => {
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;
      }, err => {

      }
    );
  }

  showRegiser() {
    const dialogRef = this.dialog.open(UserRegisterDialogComponent, {
      data: new User()
    });

    dialogRef.afterClosed().subscribe(
      data => {
        if (data) {
         this.user = data;
         //alert(this.user.nrc);
        // this.authService.createUser(this.user).subscribe(
        //   result => {
        //     if (result) {
        //       this.ngOnInit();
        //     }
        //   });
        }
      });
  }
}
