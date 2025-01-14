import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../models/Product';
import { environment } from 'src/environments/environment';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  searchLatitude: number | undefined;
  searchLongitude: number | undefined;
  searchRadius = 15;
  locationChangeSubject = new Subject;
  locationChange = this.locationChangeSubject.asObservable();

  constructor(private http: HttpClient) { }

  getProductsByUser(token:string){
    const options = this.getAuthHeader(token);
    return this.http.get<Product[]>(environment.baseAPIUrl + "/api/products", options);
  }

  searchProducts(latitude: number, longitude: number, radius: number){
    const formData = new FormData();
    formData.append('latitude', latitude.toString());
    formData.append('longitude', longitude.toString());
    formData.append('radius_km', radius.toString());
    return this.http.post<Product[]>(environment.baseAPIUrl + "/api/products/search", formData);
  }

  getById(id: number){
    return this.http.get<Product>(environment.baseAPIUrl + "/api/products/" + id);
  }

  addProduct(product: Product, token: string){
    product.sold = 0;
    product.status = 1;
    const formData = this.fillFormdata(product);

    const options = this.getAuthHeader(token);
    return this.http.post<Product>(environment.baseAPIUrl + "/api/products", formData, options);
  }

  addImage(productId: number, image: Blob, token: string){
    const formData = new FormData();
    formData.append('image', image);
    const options = this.getAuthHeader(token);
    return this.http.post<Product>(environment.baseAPIUrl + "/api/products/" + productId + "/image", formData,
      options);
  }

  deleteImage(imageId: number, token: string){
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });
    const options = {
      headers: headers
    };
    return this.http.delete(environment.baseAPIUrl + "/api/products/image/" + imageId, options);
  }

  updateProduct(product: Product, token: string){
    const formData = this.fillFormdata(product);
    const options = this.getAuthHeader(token);
    return this.http.put<Product>(environment.baseAPIUrl + "/api/products/" + product.id, formData, options);
  }

  deleteProduct(productId: number, token: string){
    const options = this.getAuthHeader(token);
    return this.http.delete(environment.baseAPIUrl + "/api/products/" + productId, options);
  }

  sellProduct(productId: number, token: string){
    const options = this.getAuthHeader(token);
    return this.http.post<Product>(environment.baseAPIUrl + "/api/products/" + productId + "/sold", {}, options);
  }

  private fillFormdata(product: Product): FormData {
    const formData = new FormData();
    formData.append('name', product.name);
    formData.append('description', product.description);
    formData.append('price', product.price.toString());
    formData.append('category_id', product.category_id.toString());
    formData.append('latitude', product.latitude.toString());
    formData.append('longitude', product.longitude.toString());
    formData.append('status', product.status.toString());
    formData.append('sold', product.sold.toString());
    return formData;
  }

  private getAuthHeader(token: string) {
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });
    const options = {
      headers: headers
    };
    return options;
  }

}
