import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormControl, Validators } from '@angular/forms';
import { User } from '@app/entity/user';
import { Book } from '@app/entity/book';


@Component({
  selector: 'app-borrow-dialog',
  templateUrl: './borrow-dialog.component.html',
  styleUrls: ['./borrow-dialog.component.scss']
})
export class BorrowDialogComponent implements OnInit {
  userList: User[];
  book: Book;
  user: string;
  book_id: string;
  is_borrow: Boolean;
  user_id: number;
  username: string;
  userControl = new FormControl('', [Validators.required]);

  constructor(
    private dialog: MatDialog,
    private dialogRef: MatDialogRef<BorrowDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    dialogRef.disableClose = true;
    this.book = this.data.book;
    this.userList = this.data.userList;
    this.is_borrow = this.data.is_borrow;
    this.user_id = this.data.user_id;
  }

  ngOnInit() {
    this.dialogRef.updateSize('30%', '45%');
    this.userList.forEach((user) => {
      if (user.id === this.user_id) {
        this.username = user.username;
      }

    });
  }
  save() {
    const body = {
      'id': this.book.book_id,
      'user_id': this.user
    };
    if (this.book.status) {
      body['status'] = 0;
      body['user_id'] = this.user;
    } else {
      body['status'] = 1;
      body['user_id'] = this.user_id.toString();
    }
    this.dialogRef.close(body);

  }

  cancel() {
    if (!this.book.status) {
      this.dialogRef.close(false);
    } else {
      this.userControl.reset();
    }
  }
  closeDialog() {
    this.dialogRef.close(false);
  }

}
