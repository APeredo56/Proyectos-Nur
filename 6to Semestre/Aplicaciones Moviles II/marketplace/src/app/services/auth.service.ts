import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { User } from '../models/User';
import { Preferences } from '@capacitor/preferences';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private isLoggedInSubject = new BehaviorSubject<boolean>(false);
  isLoggedIn = this.isLoggedInSubject.asObservable();
  myUser: User | undefined;

  constructor(private http: HttpClient) { 
    this.getToken().then((token) => {
      if(token){
        this.isLoggedInSubject.next(true);
        this.fetchUser(token);
      }
    });
  }

  setLoggedIn(isLoggedIn: boolean) {
    this.isLoggedInSubject.next(isLoggedIn);
  }

  login(email: string, password: string){
    const formData = new FormData();
    formData.append('email', email);
    formData.append('password', password);
    return this.http.post(environment.baseAPIUrl + '/api/auth/loginuser', {email, password});
  }

  register(user: User){
    const formData = new FormData();
    formData.append('fullname', user.fullname);
    formData.append('email', user.email);
    formData.append('password', user.password!);
    return this.http.post(environment.baseAPIUrl + '/api/auth/registeruser', formData);
  }

  async logout() {
    await Preferences.remove({key: 'token'});
  }

  async saveToken(token: string) {
    await Preferences.set({
      key: "token",
      value: token,
    });
    this.fetchUser(token);
  }

  async getToken() {
    return await Preferences.get({ key: 'token' }).then((res) => {
      return res.value;
    });
  }

  fetchUser(token: string){
    const headers = new HttpHeaders({
      'Authorization': 'Bearer ' + token
    });
    const options = {
      headers: headers
    };
    this.http.get<User>(environment.baseAPIUrl + '/api/auth/me', options).subscribe({
      next: (data: User) => {
        this.myUser = data;
      },
      error: (error) => {
        if(error.error.message === 'Unauthenticated'){
          this.logout();
          this.setLoggedIn(false);
        }
    }
    });
}

}
