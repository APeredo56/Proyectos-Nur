import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Geolocation } from '@capacitor/geolocation';
import { GoogleMap } from '@capacitor/google-maps';
import { NavController } from '@ionic/angular';
import { ChatService } from 'src/app/services/chat.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-send-location',
  templateUrl: './send-location.page.html',
  styleUrls: ['./send-location.page.scss'],
})
export class SendLocationPage implements OnInit {
  latitude: number | undefined;
  longitude: number | undefined;
  @ViewChild('map', { static: true }) mapRef!: ElementRef<HTMLElement>;
  googleMap!: GoogleMap;
  marker: string | undefined;

  constructor(private navController: NavController, private chatService: ChatService) { }

  ngOnInit() {
    this.getCurrentLocation().then((location) => {
      this.createMap(location.latitude, location.longitude);
    });
  }

  confirm(){
    this.chatService.msgLatitude = this.latitude!;
    this.chatService.msgLongitude = this.longitude!;
    this.chatService.locationSentObject.next("");
    this.navController.back();
  }

  async getCurrentLocation() {
    const coordinates = await Geolocation.getCurrentPosition();
    const latitude = coordinates.coords.latitude;
    const longitude = coordinates.coords.longitude;
    return {latitude: latitude, longitude: longitude};
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
      },
    });
    this.googleMap.setOnMapClickListener((event) => {
      if (this.marker) {
        this.googleMap.removeMarker(this.marker);
      } 
      this.addMarker(event.latitude, event.longitude);
    });
  }

  async addMarker(latitude: number, longitude: number) {
    this.marker = await this.googleMap.addMarker({
      coordinate: {
        lat: latitude,
        lng: longitude,
      },
    });
    this.latitude = latitude;
    this.longitude = longitude;
  }

  ngOnDestroy() {
    this.marker = undefined;
    this.googleMap.destroy();
  }

}
