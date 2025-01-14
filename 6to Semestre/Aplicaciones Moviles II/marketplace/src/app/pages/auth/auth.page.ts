import { HttpErrorResponse } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NavController } from '@ionic/angular';
import { User } from 'src/app/models/User';
import { AuthService } from 'src/app/services/auth.service';
import { Preferences } from '@capacitor/preferences';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.page.html',
  styleUrls: ['./auth.page.scss'],
})
export class AuthPage implements OnInit {

  @Input() triggerId: string = '';
  isLogin: boolean = true;
  loginForm: FormGroup = new FormGroup({
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });
  registerForm: FormGroup = new FormGroup({
    fullname: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });

  loginError: string = '';
  registerError: string = '';

  constructor(private navController: NavController, private authService: AuthService) { }

  ngOnInit() {}

  registerUser(){
    this.registerError = '';
    const user: User = {
      fullname: this.registerForm.get('fullname')?.value,
      email: this.registerForm.get('email')?.value,
      password: this.registerForm.get('password')?.value,
    };

    this.authService.register(user).subscribe({
      next: () => {
        const email = this.registerForm.get('email')?.value;
        const password = this.registerForm.get('password')?.value;
        this.doLogin(email, password);
      },
      error: (error: HttpErrorResponse) => {
        if(error.status == 400){
          this.registerError = 'El correo electrónico ya está registrado';
          } else{
            this.registerError = 'Ocurrió un error al registrar el usuario';
          }
      },
      complete: () => {}
    });
  }

  loginUser(){
    const email = this.loginForm.get('email')?.value;
    const password = this.loginForm.get('password')?.value;
    this.loginError = '';
    this.doLogin(email, password);    
  }

  doLogin(email: string, password: string){
    this.authService.login(email, password).subscribe({
      next: (res: any) => {
        this.authService.saveToken(res.access_token);
        
        this.authService.setLoggedIn(true);
        this.navController.back();
      },
      error: (error: HttpErrorResponse) => {
        if(error.status == 401){
          this.loginError = 'Los datos ingresados son incorrectos';
        } else{
          this.loginError = 'Ocurrió un error al iniciar sesión';
        }
      },
      complete: () => {}
    });
  }

  ionViewDidLeave() {
    this.loginForm.reset();
    this.registerForm.reset();
  }

}
