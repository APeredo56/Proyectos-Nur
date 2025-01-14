import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { GoogleMap } from '@capacitor/google-maps';
import { NavController } from '@ionic/angular';
import { ProductService } from 'src/app/services/product.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-select-location',
  templateUrl: './select-location.page.html',
  styleUrls: ['./select-location.page.scss'],
})
export class SelectLocationPage implements OnInit {
  @ViewChild('map', { static: true }) mapRef!: ElementRef<HTMLElement>;
  googleMap!: GoogleMap;
  circleIds: string[] = [];
  radius: number = 20;
  latitude: number | undefined;
  longitude: number | undefined;

  constructor(private productService: ProductService, private navController: NavController,) { }

  ngOnInit() {
    this.latitude = this.productService.searchLatitude;
    this.longitude = this.productService.searchLongitude;
    this.radius = this.productService.searchRadius;
    this.createMap(this.latitude!, this.longitude!);
  }

  confirm(){
    this.productService.searchLatitude = this.latitude!;
    this.productService.searchLongitude = this.longitude!;
    this.productService.searchRadius = this.radius;
    this.productService.locationChangeSubject.next("");
    this.navController.back();
  }

  onRadiusChange(event: any){
    this.radius = event.detail.value;
    this.changeCircle(this.radius);
  }

  async createMap(inititalLatitude: number, initialLongitude: number) {
    this.googleMap = await GoogleMap.create({
      id: 'my-cool-map',
      element: this.mapRef.nativeElement,
      apiKey: environment.mapsKey,
      config: {
        center: {
          lat: inititalLatitude,
          lng: initialLongitude,
        },
        zoom: 15,
        disableDefaultUI: true,
        zoomControl: true,
      },
    });
    this.drawMapCircle();
    this.googleMap.setOnCameraIdleListener((camera) => {
      this.latitude = camera.latitude;
      this.longitude = camera.longitude;
      this.changeCircle(this.radius);
    });
    this.googleMap.setOnCameraMoveStartedListener(() => {
      if (this.circleIds.length !== 0){
        this.googleMap.removeCircles(this.circleIds);
      }
      this.circleIds = [];
    });
  }

  drawMapCircle() {
    this.googleMap.addCircles(new Array({
      center: {
        lat: this.latitude!,
        lng: this.longitude!,
      },
      radius: this.radius * 1000,
      fillColor: '#AA0000',
      strokeColor: '#FF0000',
      strokeWidth: 5,
      clickable: true
    })).then((circles) => {
      this.circleIds = circles;
    });
  }
  changeCircle(meters: number) {
    this.radius = meters;
    if(this.circleIds.length !== 0){
      this.googleMap.removeCircles(this.circleIds);
    }
    this.googleMap.addCircles(new Array({
      center: {
        lat: this.latitude!,
        lng: this.longitude!,
      },
      radius: meters * 1000,
      fillColor: '#AA0000',
      strokeColor: '#FF0000',
      strokeWidth: 5,
      clickable: true
    })).then((circles) => {
      this.circleIds = circles;
    });
  }

  ngOnDestroy(): void {
    this.googleMap.destroy();
  }

}
