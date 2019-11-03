import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Book } from '@app/entity/book';

@Component({
  selector: 'app-delete-dialog',
  templateUrl: './delete-dialog.component.html',
  styleUrls: ['./delete-dialog.component.scss']
})
export class DeleteDialogComponent implements OnInit {
  book: Book;
  errorMessage: string;
  book_id: number;
  book_title: string;

  constructor(
    private dialog: MatDialog,
    private dialogRef: MatDialogRef<DeleteDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    dialogRef.disableClose = true;
    this.book = this.data.book;
  }

  ngOnInit() {

  }

  delete() {
    if (this.book_title !== this.book.title) {
      this.errorMessage = 'Wrong book name';
    } else {
      this.dialogRef.close(this.book);
    }
  }

  closeDialog() {
    this.dialogRef.close(false);
  }

}
