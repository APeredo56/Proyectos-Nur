import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-tabs',
  templateUrl: './tabs.page.html',
  styleUrls: ['./tabs.page.scss'],
})
export class TabsPage implements OnInit {
  isLoggedIn: boolean = false;
  authSubscription: Subscription;

  constructor(private authService: AuthService) { 
    this.authSubscription = this.authService.isLoggedIn.subscribe((res) => {
      this.isLoggedIn = res;
    });
  }

  ngOnInit() { }

  ngOnDestroy() {
    this.authSubscription.unsubscribe();
  }
}
