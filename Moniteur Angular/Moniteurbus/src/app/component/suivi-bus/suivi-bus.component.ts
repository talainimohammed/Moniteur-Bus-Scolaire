import { Component, OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import { MapInfoWindow, MapMarker, GoogleMap } from '@angular/google-maps';
import { Observable } from 'rxjs';
import { interval } from 'rxjs';
import { delay, takeWhile } from 'rxjs/operators';
import { SuiviBusService } from '../../services/suivi-bus.service';
import { SuiviBus } from '../../models/suivi-bus';
import { EtudiantService } from '../../services/etudiant.service';
import { Etudiant } from '../../models/etudiant';
import { FirebaseService } from '../../services/firebase.service';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-suivi-bus',
  templateUrl: './suivi-bus.component.html',
  styleUrl: './suivi-bus.component.css'
})
export class SuiviBusComponent implements OnInit{
  etudiant:Etudiant=new Etudiant();
    center: google.maps.LatLngLiteral = {lat: 24, lng: 12};
    currentpos:SuiviBus=new SuiviBus();
    destinationpos:google.maps.LatLngLiteral = {lat: 0, lng: 0};
    //  destinationpos:google.maps.LatLngLiteral = {lat: 33.69088747096958, lng: -7.372760713061517};
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
    idEtudiant:number=0;
    constructor(private suiviBus:SuiviBusService,private etudiantService:EtudiantService,private route: ActivatedRoute){}
  
    ngOnInit() {
      if(this.route.snapshot.params['id']!=null){
        this.idEtudiant= this.route.snapshot.params['id'];
        this.retrieveEtudiant(this.idEtudiant);
        navigator.geolocation.getCurrentPosition((position) => {
          this.center = {
            lat: this.etudiant.latitude ?? position.coords.latitude,
            lng: this.etudiant.longtitude ?? position.coords.longitude,
          };
        });
      }
     // this.sendtofirebase();
      /*setInterval(() => {
      this.sendloc();
    }, 5000);*/
      this.check=true;
      this.getRealTimeLoc(this.etudiant.busId ?? 0);
    }
    circleCenter: google.maps.LatLngLiteral = {lat: 33.667782574792184, lng: -7.397301689965827};
  
  // Assuming circleRadius is the radius of your circle in meters
    circleRadius: number = 50;
    addMarker(event: google.maps.MapMouseEvent) {
      if (event.latLng) {
        let markerPosition: google.maps.LatLngLiteral = event.latLng.toJSON();
        this.markerPositions.push(markerPosition);
      }
    }
    perimeternotif(curPos:google.maps.LatLngLiteral,destPos:google.maps.LatLngLiteral){
          // Calculate the distance between the marker and the center of the circle
          let distance = google.maps.geometry.spherical.computeDistanceBetween(
            new google.maps.LatLng(curPos),
            new google.maps.LatLng(destPos)
  
          );
          // Check if the marker is inside the circle
          if (distance <= this.circleRadius) {
            console.log('The marker is inside the circle');
          } else {
            console.log('The marker is outside the circle');
          }
    }
    sendloc() {
      this.saveRealTimeLoc();
      /*let loc = {lat: 33.667782574792184, lng: -7.397301689965827};
        this.markerPositions.push(loc);
        console.log(loc);*/
    }
    getRealTimeLoc(id:number){
      this.suiviBus.getRealTimeLocByBusId(id).pipe(
        delay(2000)
      ).subscribe((data:any)=>{
        this.markerPositions=[];
        //this.markerPositions.push(this.markerPosition);
        if(data!=null){      
        let lastpos=data;
        console.log(data);
        this.markerPositions.push({lat: lastpos.latitude, lng: lastpos.longtitude});
        }
        else{      
          this.markerPositions.push(this.markerPosition);
        }
        /*data.forEach((d: { latitude: any; longtitude: any; }) => {
          this.markerPositions.push({lat: d.latitude, lng: d.longtitude});
        });*/
        this.markerPositions.push(this.destinationpos);  
        this.perimeternotif(this.markerPositions[0],this.destinationpos);
      });
    }
    saveRealTimeLoc(){
      this.currentpos.idbus=this.etudiant.busId ?? 0;    
      this.currentpos.latitude=this.markerPositions[1].lat+0.0005;
      this.currentpos.longtitude=this.markerPositions[1].lng+0.0001;
      this.suiviBus.addRealTimeLoc(this.currentpos).subscribe((data:any)=>{
        this.getRealTimeLoc(this.etudiant.busId ?? 0);
      });
    }
    startLocation(){
      this.markerPosition={lat: 33.663996491485584, lng: -7.398519515991211};
    }
    destinationLocation(){
      if(this.etudiant.latitude!=null && this.etudiant.longtitude!=null){
        this.destinationpos={lat: this.etudiant.latitude, lng: this.etudiant.longtitude}; 
      }
    }
    retrieveEtudiant(id:any){
      this.etudiantService.getEtudiant(id).subscribe(data => {
        this.etudiant = data;
        if (this.etudiant.latitude != null && this.etudiant.longtitude != null) {
          this.startLocation();
          this.destinationLocation();          
        }
      });
    }
}
