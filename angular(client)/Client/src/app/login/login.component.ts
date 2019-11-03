import { Component, OnInit } from '@angular/core';
import { AuthService } from '@service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;
  errorMessage: string;

  constructor(
    private authService: AuthService,
    private router: Router,
  ) { }

  ngOnInit() {
  }

  login() {
    this.authService.login({'username': this.username, 'password': this.password}).subscribe(
      data => {
        this.authService.setToken(data['token']);
        this.router.navigate(['#/book']);
        localStorage.setItem('user_name', this.username);
      }, err => {
        localStorage.removeItem('user_name');
        this.errorMessage = 'Authentication was failed';
      }
    );
  }


}
