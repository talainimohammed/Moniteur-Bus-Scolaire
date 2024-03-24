import { Component, OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import { MapInfoWindow, MapMarker, GoogleMap } from '@angular/google-maps';
import { Observable } from 'rxjs';
import { interval } from 'rxjs';
import { takeWhile } from 'rxjs/operators';
import { SuiviBusService } from '../../services/suivi-bus.service';
import { SuiviBus } from '../../models/suivi-bus';
@Component({
  selector: 'app-suivi-bus',
  templateUrl: './suivi-bus.component.html',
  styleUrl: './suivi-bus.component.css'
})
export class SuiviBusComponent implements OnInit{
  center: google.maps.LatLngLiteral = {lat: 24, lng: 12};
  currentpos:SuiviBus=new SuiviBus();
  destinationpos:google.maps.LatLngLiteral = {lat: 33.69088747096958, lng: -7.372760713061517};
  zoom = 15;
  options: google.maps.MapOptions = {
    zoomControl: true,
    scrollwheel: false,
    disableDoubleClickZoom: true,
    mapTypeId: 'hybrid',
    maxZoom: 20,
    minZoom: 8,
  };
  markerOptions: google.maps.MarkerOptions = {draggable: false};
  markerPositions: google.maps.LatLngLiteral[] = [];
  check=false;
  markerPosition: google.maps.LatLngLiteral ={lat: 33.663996491485584, lng: -7.398519515991211};

  constructor(private suiviBus:SuiviBusService){}

  ngOnInit() {
    navigator.geolocation.getCurrentPosition((position) => {
      this.center = {
        lat: position.coords.latitude,
        lng: position.coords.longitude,
      };
    });
    this.check=true;
    this.getRealTimeLoc(2);
    //this.destinationLocation();
  }
  addMarker(event: google.maps.MapMouseEvent) {
    if (event.latLng) {
      this.markerPositions.push(event.latLng.toJSON());
      console.log(event.latLng.toJSON());
      
    }
  }
  sendloc() {
    this.saveRealTimeLoc();
    /*let loc = {lat: 33.667782574792184, lng: -7.397301689965827};
      this.markerPositions.push(loc);
      console.log(loc);*/
  }
  getRealTimeLoc(id:number){
    this.suiviBus.getRealTimeLocByBusId(id).subscribe((data:any)=>{
      this.markerPositions.push(this.markerPosition);
      data.forEach((d: { latitude: any; longtitude: any; }) => {
        this.markerPositions.push({lat: d.latitude, lng: d.longtitude});
      });
      this.markerPositions.push(this.destinationpos);  
      console.log(data);
    });
  }
  saveRealTimeLoc(){
    this.currentpos.idbus=2;
    this.currentpos.latitude=33.674535232026024;
    this.currentpos.longtitude=-7.392163348657226;
    this.suiviBus.addRealTimeLoc(this.currentpos).subscribe((data:any)=>{
      console.log(data);
    });
  }
  startLocation(){
    this.markerPosition={lat: 33.663996491485584, lng: -7.398519515991211};
    this.markerPositions.push(this.markerPosition);
  }
  destinationLocation(){
    this.markerPositions = this.markerPositions.filter(pos => pos !== this.destinationpos);
    this.markerPositions.push(this.destinationpos);  
  }
}
