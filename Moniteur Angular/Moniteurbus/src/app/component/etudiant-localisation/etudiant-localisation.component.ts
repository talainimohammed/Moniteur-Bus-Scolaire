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
import { Userdata } from '../../models/userdata';
import { Bus } from '../../models/bus';
import { BusService } from '../../services/bus.service';
@Component({
  selector: 'app-etudiant-localisation',
  templateUrl: './etudiant-localisation.component.html',
  styleUrl: './etudiant-localisation.component.css'
})
export class EtudiantLocalisationComponent implements OnInit{

  etudiant:Etudiant=new Etudiant();
  idetudiant=0;
  idchauffeur=0;
  center: google.maps.LatLngLiteral = {lat: 24, lng: 12};
  currentpos:SuiviBus=new SuiviBus();
  destinationpos:google.maps.LatLngLiteral = {lat: 0, lng: 0};
  userData:Userdata=new Userdata();
  bus:Bus=new Bus();
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
  
    constructor(private suiviBus:SuiviBusService,private busService:BusService,private etudiantService:EtudiantService,private http: HttpClient,private route:ActivatedRoute){}
  
    origin = { lat: 33.70038747096962, lng: -7.370860713061521 };
    destination = { lat: 25, lng: 13 };
    @ViewChild(GoogleMap, { static: false }) map?: GoogleMap;
    ngAfterViewInit() {
         
    }

    ngOnInit() {
      if(this.route.snapshot.params['id']){
        this.idetudiant=this.route.snapshot.params['id'];
      }
      this.userData= JSON.parse(localStorage.getItem('userData') as string);
      this.idchauffeur = this.userData.id ?? 0;
      this.getBusByChauffeur(this.idchauffeur);
      
      this.check=true;
      this.retrieveEtudiant(this.idetudiant);
      setInterval(() => {
        console.log('get localisation every 10s');
        if (this.bus) {
          this.getRealTimeLoc(this.bus.idBus ?? 0);
        }
      }, 5000);
    }
    getBusByChauffeur(id:any){
      this.busService.getBusByChauffeur(id).subscribe(data => {
        this.bus = data;
        navigator.geolocation.getCurrentPosition((position) => {
          this.center = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          };
          this.currentpos.idbus=this.bus.idBus ?? 0;    
          this.currentpos.latitude=this.center.lat;
          this.currentpos.longtitude=this.center.lng;
          this.saveRealTimeLoc(this.currentpos);
        });
        console.log(data);
      });
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
      this.currentpos.idbus=this.bus.idBus ?? 0;    
      this.currentpos.latitude=this.markerPositions[1].lat+0.0005;
      this.currentpos.longtitude=this.markerPositions[1].lng+0.0001;
      this.saveRealTimeLoc(this.currentpos);
      /*let loc = {lat: 33.667782574792184, lng: -7.397301689965827};
        this.markerPositions.push(loc);
        console.log(loc);*/
    }
    getRealTimeLoc(id:number){
      this.suiviBus.getRealTimeLocByBusId(id).pipe(
        delay(2000)
      ).subscribe((data:any)=>{
        this.markerPositions=[];
        this.markerPositions.push(this.markerPosition);
        if(data!=null){      
        let lastpos=data[0] as SuiviBus;
        console.log(lastpos);
        this.markerPositions.push({lat: lastpos.latitude ?? 0, lng: lastpos.longtitude ?? 0});
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
      const gdata:any = {
        idbus: this.currentpos.idbus,
        latitude: this.currentpos.latitude,
        longtitude: this.currentpos.longtitude
      };
      this.suiviBus.addRealTimeLoc(gdata).subscribe((data:any)=>{
        console.log(data);
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
