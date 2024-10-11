import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VendorService {

  constructor(private http: HttpClient) { }

  getAllVendors(): Observable<any> {
    return this.http.get<any>("https://smartx-back.azurewebsites.net/vendors");
  }

  getVendorById(id: string): Observable<any> {
    return this.http.get<any>(`https://smartx-back.azurewebsites.net/vendors/${id}`);
  }


  deleteVendorById(id: any): Observable<any> {
    return this.http.delete(`https://smartx-back.azurewebsites.net/vendors/delete/${id}`);
  }

  updateVendor(data:any):Observable<any>{
    return this.http.put(`https://smartx-back.azurewebsites.net/vendors/update`,data);
  }

  addVendor(data:any):Observable<any>{
    return this.http.post(`https://smartx-back.azurewebsites.net/vendors/add`,data);
  }

  requestStock(venderemail:any, amount:any, data:any):Observable<any>{
    return this.http.post(`https://smartx-back.azurewebsites.net/vendors/restock/${venderemail}/${amount}`, data);
  }
}