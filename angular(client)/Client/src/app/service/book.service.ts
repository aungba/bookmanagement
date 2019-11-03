import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Book } from '@app/entity/book';
import { AuthService } from '@service/auth.service';
import { environment } from '@environments/environment';

@Injectable({
    providedIn: 'root'
})
export class BookService {
    baseUrl = environment.baseUrl;

    constructor(
        private http: HttpClient,
        private authService: AuthService
    ) {}

    getBookList(query: string): Observable<Book[]> {
        const headerOptions = {
            headers: new HttpHeaders ({
                'Content-Type': 'application/json',
                'Authorization': 'JWT ' + this.authService.getToken()
            })
        };

        if (query !== '') {
            return this.http.get<Book[]>(this.baseUrl + '/api/books/' + query, headerOptions);
        } else {
            return this.http.get<Book[]>(this.baseUrl + '/api/books/', headerOptions);
        }
    }

    getBookById(id: number): Observable<Book> {
        const headerOptions = {
            headers: new HttpHeaders ({
                'Content-Type': 'application/json',
                'Authorization': 'JWT ' + this.authService.getToken()
            })
        };
        return this.http.get<Book>(this.baseUrl + '/api/books/' + id + '/', headerOptions);
    }

    updateBook(id: number, book: Book): Observable<any> {
        const headerOptions = {
            headers: new HttpHeaders ({
                'Content-Type': 'application/json',
                'Authorization': 'JWT ' + this.authService.getToken()
            })
        };

        return this.http.put<any>(this.baseUrl + '/api/books/' + id + '/' , JSON.stringify(book), headerOptions);
    }

    deleteBook(id: number): Observable<any> {
        const headerOptions = {
            headers: new HttpHeaders ({
                'Content-Type': 'application/json',
                'Authorization': 'JWT ' + this.authService.getToken()
            })
        };
        return this.http.delete<any>(this.baseUrl + '/api/books/' + id + '/' , headerOptions);
    }

    addBook(book: Book): Observable<any> {
        const headerOptions = {
            headers: new HttpHeaders ({
                'Content-Type': 'application/json',
                'Authorization': 'JWT ' + this.authService.getToken()
            })
        };
        //return this.http.post<any>(this.baseUrl + '/api/books/', JSON.stringify(book), headerOptions);
        return this.http.post<any>(this.baseUrl +  '/api/books/add/', JSON.stringify(book), headerOptions);
    }

    // searchBook(query: string): Observable<any> {
    //     const headerOptions = {
    //         headers: new HttpHeaders ({
    //             'Content-Type': 'application/json',
    //             'Authorization': 'JWT ' + this.authService.getToken()
    //         })
    //     };
    //     return this.http.get<any>(this.baseUrl + '/api/books/' + query + '/search/' , headerOptions);
    // }

    changeBook(id: number, user_id: number, status: number): Observable<any> {
        const body = {
            'id': id,
            'user_id': user_id,
            'status': status
        };
        const headerOptions = {
            headers: new HttpHeaders ({
                'Content-Type': 'application/json',
                'Authorization': 'JWT ' + this.authService.getToken()
            })
        };
        return this.http.post<any>(this.baseUrl + '/api/bookLoan/state', JSON.stringify(body), headerOptions);
    }

    getBorrowBookById(id: number): Observable<any[]> {
        const headerOptions = {
            headers: new HttpHeaders ({
                'Content-Type': 'application/json',
                'Authorization': 'JWT ' + this.authService.getToken()
            })
        };
        return this.http.get<any[]>(this.baseUrl + '/api/bookLoan/' + id + '/loan/', headerOptions);
    }

    getOverDueDate(): Observable<any[]> {
        const headerOptions = {
            headers: new HttpHeaders ({
                'Content-Type': 'application/json',
                'Authorization': 'JWT ' + this.authService.getToken()
            })
        };
        return this.http.get<any[]>(this.baseUrl + '/api/bookLoan/get_over_due_date/', headerOptions);
    }
}
