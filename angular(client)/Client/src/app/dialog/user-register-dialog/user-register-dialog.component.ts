import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { User } from '@entity/user';
import { Gender } from '@entity/gender';


@Component({
  selector: 'app-user-register-dialog',
  templateUrl: './user-register-dialog.component.html',
  styleUrls: ['./user-register-dialog.component.scss']
})
export class UserRegisterDialogComponent implements OnInit {
  registerForm: FormGroup;

  genderList: Gender[] = [
    {id: 'M', value: 'Male'},
    {id: 'F', value: 'Female'}
  ];

  roleList: Gender[] = [
    {id: true, value: 'Admin'},
    {id: false, value: 'User'}
  ];

  user: User;
  constructor(
    private dialog: MatDialog,
    private formBuilder: FormBuilder,
    private dialogRef: MatDialogRef<UserRegisterDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    dialogRef.disableClose = true;
     this.user = data;
  }

  ngOnInit() {
    this.dialogRef.updateSize('40%', '80%');
   // this.user = new User();
   // this.user.gender = 'F';
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      phone: ['', Validators.required],
      mail: ['', Validators.required],
      age: ['', Validators.required],
      gender: [''],
      role: ['', Validators.required],
      nrc: ['', Validators.required]
    });


  }

  save() {
    this.dialogRef.close(this.user);
  }

  cancel() {
    this.registerForm.reset();
  }

  closeDialog() {
    this.dialogRef.close();
  }

}
