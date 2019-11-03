import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '@app/entity/user';
import { environment } from '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  baseUrl = environment.baseUrl;

  setToken(token: string) {
    localStorage.setItem('login_token', token);
  }

  getToken() {
    return localStorage.getItem('login_token');
  }

  isLoggedIn() {
    return this.getToken() !== null;
  }

  removeToken() {
    localStorage.removeItem('login_token');
  }

  constructor(
    private http: HttpClient,
  ) { }

    login(user): Observable<any> {
      const headerOptions = {
        headers: new HttpHeaders ({
          'Content-Type': 'application/json'
        })
      };
      //return this.http.post(this.baseUrl + '/api/api-token-auth/', JSON.stringify(user), headerOptions);
      return this.http.post(this.baseUrl + '/api/authenticate', JSON.stringify(user), headerOptions);
    }

    getUserList(): Observable<User[]> {
      const headerOptions = {
        headers: new HttpHeaders ({
          'Content-Type': 'application/json',
          'Authorization': 'JWT ' + this.getToken()
        })
      };
      return this.http.get<User[]>(this.baseUrl + '/api/users/', headerOptions);
    }

    createUser(user: User): Observable<User> {
      const headerOptions = {
        headers: new HttpHeaders ({
          'Content-Type': 'application/json',
          'Authorization': 'JWT ' + this.getToken()
        })
      };
      return this.http.post<User>(this.baseUrl + '/api/user/users/', JSON.stringify(user), headerOptions);
    }

}
