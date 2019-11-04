import { Component, OnInit, Inject, ViewChild, ElementRef } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import flatpickr from 'flatpickr';
import { Book } from '@entity/book';
import { Category } from '@entity/category';
import { Gender } from '@app/entity/gender';


@Component({
  selector: 'app-book-register-dialog',
  templateUrl: './book-register-dialog.component.html',
  styleUrls: ['./book-register-dialog.component.scss']
})
export class BookRegisterDialogComponent implements OnInit {

  bookForm: FormGroup;
  book: Book;
  categoryList: Category[];


  statusList: Gender[] = [
    {id: 'Available', value: 'Available'},
    {id: 'Closed', value: 'Closed'}
  ];

  constructor(
    private dialog: MatDialog,
    private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<BookRegisterDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) {
    dialogRef.disableClose = true;
    this.book = this.data.book;
    this.categoryList = this.data.category;
   }

  ngOnInit() {
    flatpickr('#release', {enableTime: true,
      dateFormat: 'Y-m-d'});
    this.dialogRef.updateSize('40%', '80%');
    this.bookForm = this.formBuilder.group({
      title: ['', Validators.required],
      author: ['', Validators.required],
      publisher: ['', Validators.required],
      summary: ['', Validators.required],
      releaseDate: ['', Validators.required],
      category: ['', Validators.required],
      isAvailable: ['', Validators.required]
    });

    if (!this.book.isAvailable) {
      this.book.isAvailable = 'Available';
    }

  }

  save() {
    this.book.status = true;
    this.dialogRef.close(this.book);
  }

  cancel() {
    this.bookForm.reset();
    this.bookForm.get('isAvailable').setValue('Available');
  }

  closeDialog() {
    this.dialogRef.close();
  }

}
