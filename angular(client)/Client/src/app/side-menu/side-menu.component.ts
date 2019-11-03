import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '@service/auth.service';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.scss']
})
export class SideMenuComponent implements OnInit {
  user_name: string;

  constructor(
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit() {
    this.user_name = localStorage.getItem('user_name');
  }

  logout() {
    this.authService.removeToken();
    this.router.navigate(['/']);
  }

  goBook() {
    this.router.navigate(['#/book']);
  }

  goCategory() {
    this.router.navigate(['#/category']);
  }

}
