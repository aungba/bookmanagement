import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-error-dialog',
  templateUrl: './error-dialog.component.html',
  styleUrls: ['./error-dialog.component.scss']
})
export class ErrorDialogComponent implements OnInit {

  title: string;
  content: string;

  constructor(private dialog: MatDialog, private dialogRef: MatDialogRef<ErrorDialogComponent>,
   @Inject(MAT_DIALOG_DATA) public data: any ) {
    this.title = data.title;
    this.content = data.content;
    dialogRef.disableClose = true;
    }

  ngOnInit() {
    // this.dialogRef.updateSize('400px', '100px');
  }

  onNoClick() {
    this.dialogRef.close(true);
  }

}
