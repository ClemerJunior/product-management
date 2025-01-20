import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ProductModel} from '../models/product.model';
import {PageModel} from '../models/page.model';
import {CreateUpdateProductModel} from '../models/create-update-product.model';
import {CategoryModel} from '../models/category.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl = 'http://localhost:8080/product';
  private categoryUrl = 'http://localhost:8080/category';

  constructor(private http: HttpClient) {}

  getProducts(page: number, size: number, sort: string, filters: Record<string, string>): Observable<any> {
    let params = new HttpParams().set('page', page).set('size', size).set('sort', sort);
    Object.keys(filters).forEach((key) => {
      params = params.set(key, filters[key]);
    });
    return this.http.get<PageModel>(this.apiUrl+'/filter', { params });
  }

  insertProduct(product: CreateUpdateProductModel): Observable<ProductModel> {
    return this.http.post<ProductModel>(this.apiUrl, product);
  }

  getProduct(productoId: number): Observable<ProductModel> {
    return this.http.get<ProductModel>(`${this.apiUrl}/${productoId}`)
  }

  updateProduct(productoId: number,product: CreateUpdateProductModel): Observable<ProductModel> {
    return this.http.put<ProductModel>(`${this.apiUrl}/${productoId}`, product);
  }

  deleteProduct(productId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${productId}`);
  }

  getCategories(): Observable<CategoryModel> {
    return this.http.get<CategoryModel>(this.categoryUrl);
  }

}
