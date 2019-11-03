import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Category } from '@entity/category';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-category-register-edit-dialog',
  templateUrl: './category-register-edit-dialog.component.html',
  styleUrls: ['./category-register-edit-dialog.component.scss']
})
export class CategoryRegisterEditDialogComponent implements OnInit {
  category: Category;
  categoryControl = new FormControl('', [Validators.required]);
  type: string;
  title: string;
  saveBtn: string;
  cancelBtn: string;
  isReadOnly: boolean;

  constructor(
    private dialog: MatDialog,
    private dialogRef: MatDialogRef<CategoryRegisterEditDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    dialogRef.disableClose = true;
    this.category = this.data.category;
    this.type = this.data.type;
  }

  ngOnInit() {
    this.dialogRef.updateSize('30%', '45%');
    if (this.type === 'register') {
      this.title = 'Category Register';
      this.saveBtn = 'Register';
      this.isReadOnly = false;
    } else if (this.type === 'edit') {
      this.title = 'Category Update';
      this.saveBtn = 'Update';
      this.isReadOnly = false;
    } else {
      this.title = 'Category Delete';
      this.saveBtn = 'Delete';
      this.isReadOnly = true;
    }
    this.cancelBtn = 'Cancel';
  }

  save() {
    this.dialogRef.close(this.category);
  }

  cancel() {
    if (this.type === 'delete') {
      this.dialogRef.close(false);
    } else {
      this.categoryControl.reset();
    }
  }

  closeDialog() {
    this.dialogRef.close(false);
  }

}
