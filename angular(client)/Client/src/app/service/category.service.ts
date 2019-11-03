import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Category } from '@app/entity/category';
import { AuthService } from '@service/auth.service';
import { environment } from '@environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  baseUrl = environment.baseUrl;

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  getAllCategoryList(): Observable<Category[]> {
    const headerOptions = {
      headers: new HttpHeaders ({
        'Content-Type': 'application/json',
        'Authorization': 'JWT ' + this.authService.getToken()
      })
    };
    return this.http.get<Category[]>(this.baseUrl + '/api/categories/', headerOptions);
    //return this.http.get<Category[]>('http://localhost:8080/categories/', headerOptions);
  }

  getCategoryById(id: number): Observable<Category> {
    const headerOptions = {
      headers: new HttpHeaders ({
        'Content-Type': 'application/json',
        'Authorization': 'JWT ' + this.authService.getToken()
      })
    };
    return this.http.get<Category>(this.baseUrl + '/api/categories/' + id + '/' , headerOptions);
  }

  updateCategory(id: number, category: Category): Observable<any> {
    const headerOptions = {
      headers: new HttpHeaders ({
        'Content-Type': 'application/json',
        'Authorization': 'JWT ' + this.authService.getToken()
      })
    };
    return this.http.put<any>(this.baseUrl + '/api/categories/' + id + '/' , JSON.stringify(category), headerOptions);
  }

  deleteCategory(id: number): Observable<any> {
    const headerOptions = {
      headers: new HttpHeaders ({
        'Content-Type': 'application/json',
        'Authorization': 'JWT ' + this.authService.getToken()
      })
    };
    return this.http.delete<any>(this.baseUrl + '/api/categories/' + id + '/' , headerOptions);
  }

  createCategory(category: Category): Observable<any> {
    const headerOptions = {
      headers: new HttpHeaders ({
        'Content-Type': 'application/json',
        'Authorization': 'JWT ' + this.authService.getToken()
      })
    };
    //return this.http.post<any>(this.baseUrl + '/api/categories/', JSON.stringify(category), headerOptions);
    return this.http.post<any>(this.baseUrl + '/api/categories/add/', JSON.stringify(category), headerOptions);
  }
}
