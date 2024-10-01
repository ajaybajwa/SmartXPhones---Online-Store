import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Review } from 'src/app/Models/Review';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  constructor(private http: HttpClient) { }

  getAllReviews(): Observable<Review[]> {
    return this.http.get<Review[]>(`http://localhost:8080/review`);
  }

  getReviewByName(name: string): Observable<Review[]> {
    return this.http.get<Review[]>(`http://localhost:8080/review/name/${name}`);
  }

  getReviewById(id: number): Observable<Review> {
    return this.http.get<Review>(`http://localhost:8080/review/${id}`);
  }

  addReview(review: Review): Observable<any> {
    return this.http.post<Review>(`http://localhost:8080/review/add`, review);
  }

  updateReview(review: Review): Observable<any> {
    return this.http.put<Review>(`http://localhost:8080/review/update`, review);
  }

  deleteReview(id: number): Observable<any> {
    return this.http.delete<Review>(`http://localhost:8080/review/delete/${id}`);
  }
}
