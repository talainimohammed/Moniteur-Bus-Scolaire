import { Component } from '@angular/core';
import { Userdata } from '../../models/userdata';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  center: google.maps.LatLngLiteral = {lat: 24, lng: 12};
  zoom = 15;
  options: google.maps.MapOptions = {
    zoomControl: true,
    scrollwheel: false,
    disableDoubleClickZoom: true,
    mapTypeId: 'hybrid',
    maxZoom: 20,
    minZoom: 8,
  };
  markerOptions: google.maps.MarkerOptions[] = [{title:'Start',draggable: false,icon:'/assets/img/avatars/start.png'},
  {title:'bus',draggable: false,icon:'/assets/img/avatars/location.png'},
  {title:'Destination',draggable: false,icon:'/assets/img/avatars/end.png'}];
  //markerOptions: google.maps.MarkerOptions = {title:'hello',draggable: false,icon:'/assets/img/avatars/location.png'};
  markerPositions: google.maps.LatLngLiteral[] = [];
  check=false;
  markerPosition: google.maps.LatLngLiteral ={lat: 0, lng: 0};
  userData:Userdata=new Userdata();
  ngOnInit() {
    navigator.geolocation.getCurrentPosition((position) => {
      this.center = {
        lat: position.coords.latitude,
        lng: position.coords.longitude,
      };
      this.markerPositions.push(this.center);
      console.log(this.markerPositions);
    });
    this.userData= JSON.parse(localStorage.getItem('userData') as string);
      const id_ecole = this.userData ? this.userData.idecole : null;
      console.log(id_ecole);
  
    
  }
}
