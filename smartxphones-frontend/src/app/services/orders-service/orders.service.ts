import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Orders } from 'src/app/Models/orders';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private http: HttpClient) { }

  getAllOrders(): Observable<Orders[]> {
    return this.http.get<Orders[]>(`https://smartxphones-backend.azurewebsites.net/orders/admin`);
  }

  updateOrder(order: Orders) {
    return this.http.put<Orders>(`https://smartxphones-backend.azurewebsites.net/orders/update`, order);
  }

  deleteOrder(id: number) {
   return this.http.delete<Orders>(`https://smartxphones-backend.azurewebsites.net/orders/delete/${id}`);
  }

  addAnOrder(order: Orders) {
    return this.http.post<Orders>(`https://smartxphones-backend.azurewebsites.net/orders/add`, order);
  }
}
