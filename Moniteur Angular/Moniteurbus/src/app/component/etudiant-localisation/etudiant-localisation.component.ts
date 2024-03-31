import { Component, OnInit, ViewChild } from '@angular/core';
import { Etudiant } from '../../models/etudiant';
import { SuiviBus } from '../../models/suivi-bus';
import { SuiviBusService } from '../../services/suivi-bus.service';
import { EtudiantService } from '../../services/etudiant.service';
import { delay } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { FirebaseService } from '../../services/firebase.service';
import { GoogleMap } from '@angular/google-maps';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-etudiant-localisation',
  templateUrl: './etudiant-localisation.component.html',
  styleUrl: './etudiant-localisation.component.css'
})
export class EtudiantLocalisationComponent implements OnInit{

  etudiant:Etudiant=new Etudiant();
  idetudiant=0;
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
  
    constructor(private suiviBus:FirebaseService,private etudiantService:EtudiantService,private http: HttpClient,private route:ActivatedRoute){}
  
    origin = { lat: 33.70038747096962, lng: -7.370860713061521 };
    destination = { lat: 25, lng: 13 };
    @ViewChild(GoogleMap, { static: false }) map?: GoogleMap;
    ngAfterViewInit() {
         
    }
    ngOnInit() {
      if(this.route.snapshot.params['id']){
        this.idetudiant=this.route.snapshot.params['id'];
      }
      navigator.geolocation.getCurrentPosition((position) => {
        this.center = {
          lat: position.coords.latitude,
          lng: position.coords.longitude,
        };
      });
      this.currentpos.idbus=3;    
      this.currentpos.latitude=this.center.lat;
      this.currentpos.longtitude=this.center.lng;
      this.saveRealTimeLoc(this.currentpos);
     // this.sendtofirebase();
      /*setInterval(() => {
      this.sendloc();
    }, 5000);*/
      this.check=true;
      this.retrieveEtudiant(this.idetudiant);
      this.getRealTimeLoc(3);
    }
    circleCenter: google.maps.LatLngLiteral = {lat: 33.667782574792184, lng: -7.397301689965827};
  
  // Assuming circleRadius is the radius of your circle in meters
    circleRadius: number = 50;
    addMarker(event: google.maps.MapMouseEvent) {
      if (event.latLng) {
        let markerPosition: google.maps.LatLngLiteral = event.latLng.toJSON();
        console.log(markerPosition);
        
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
      this.currentpos.idbus=3;    
      this.currentpos.latitude=this.markerPositions[1].lat+0.0005;
      this.currentpos.longtitude=this.markerPositions[1].lng+0.0001;
      this.saveRealTimeLoc(this.currentpos);
      /*let loc = {lat: 33.667782574792184, lng: -7.397301689965827};
        this.markerPositions.push(loc);
        console.log(loc);*/
    }
    getRealTimeLoc(id:number){
      this.suiviBus.getRealTimeLoc().pipe(
        delay(2000)
      ).subscribe((data:any)=>{
        this.markerPositions=[];
        this.markerPositions.push(this.markerPosition);
        if(data.length!=0){      
        let lastpos=data[id];
        console.log(data[id]);
        this.markerPositions.push({lat: lastpos.latitude, lng: lastpos.longtitude});
        }
        else{      
          this.markerPositions.push(this.markerPosition);
        }
        /*data.forEach((d: { latitude: any; longtitude: any; }) => {
          this.markerPositions.push({lat: d.latitude, lng: d.longtitude});
        });*/
        this.markerPositions.push(this.destinationpos);  
        this.origin=this.markerPositions[1];
        this.destination=this.markerPositions[2];
        this.perimeternotif(this.markerPositions[1],this.destinationpos);
        /**************trajet****************** */
        console.log(this.origin);
        console.log(this.destination);
        //this.map?.googleMap?.addListener('tilesloaded', () => {
          const directionsService = new google.maps.DirectionsService();
          const directionsRenderer = new google.maps.DirectionsRenderer();
          directionsRenderer.setMap(this.map?.googleMap ?? null);  
    
          const request: google.maps.DirectionsRequest = {
            origin: this.origin,
            destination: this.destination,
            travelMode: google.maps.TravelMode.DRIVING
          };
    
          directionsService.route(request, (result, status) => {
            if (status === google.maps.DirectionsStatus.OK) {
              directionsRenderer.setDirections(result);
              new google.maps.Marker({
                position: this.origin,
                map: this.map?.googleMap,
                label: 'Bus',
                title: 'Origin',
                icon:'/assets/img/avatars/location.png'
              });
      
              new google.maps.Marker({
                position: this.destination,
                map: this.map?.googleMap,
                label: 'B',
                title: 'Destination',
                icon:'/assets/img/avatars/end.png'
              });
            }
          });
        }); 
        
      //});
    }
    saveRealTimeLoc(currentpos:any){
      /*this.currentpos.idbus=3;    
      this.currentpos.latitude=this.markerPositions[1].lat+0.0005;
      this.currentpos.longtitude=this.markerPositions[1].lng+0.0001;*/
      let groupedData = {
        [currentpos.idbus]: {
          idbus: this.currentpos.idbus,
          latitude: this.currentpos.latitude,
          longtitude: this.currentpos.longtitude
        }
      };
      let groupedDataJson = JSON.stringify(groupedData);
      this.suiviBus.addRealTimeLoc(groupedDataJson).subscribe((data:any)=>{
        this.getRealTimeLoc(3);
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
