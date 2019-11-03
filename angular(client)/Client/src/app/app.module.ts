import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {
  MatDialogModule,
  MatButtonModule,
  MatInputModule,
  MatRadioModule,
  MatSelectModule} from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from '@app/app-routing.module';
import { AppComponent } from '@app/app.component';
import { LoginComponent } from '@app/login/login.component';
import { ErrorDialogComponent } from '@app/dialog/error-dialog/error-dialog.component';
import { UserRegisterDialogComponent } from '@app/dialog/user-register-dialog/user-register-dialog.component';
import { BookRegisterDialogComponent } from './dialog/book-register-dialog/book-register-dialog.component';
import { BorrowDialogComponent } from './dialog/borrow-dialog/borrow-dialog.component';
import { DeleteDialogComponent } from './dialog/delete-dialog/delete-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ErrorDialogComponent,
    UserRegisterDialogComponent,
    BookRegisterDialogComponent,
    BorrowDialogComponent,
    DeleteDialogComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatDialogModule,
    MatButtonModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatInputModule,
    AngularFontAwesomeModule,
    FormsModule,
    HttpClientModule,
    MatRadioModule,
    MatSelectModule,
    ReactiveFormsModule
  ],
  providers: [],
  entryComponents: [ErrorDialogComponent, UserRegisterDialogComponent, BookRegisterDialogComponent, BorrowDialogComponent,
  DeleteDialogComponent] ,
  bootstrap: [AppComponent]
})
export class AppModule { }
