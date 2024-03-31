import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EtudiantLocalisationComponent } from './etudiant-localisation.component';

describe('EtudiantLocalisationComponent', () => {
  let component: EtudiantLocalisationComponent;
  let fixture: ComponentFixture<EtudiantLocalisationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EtudiantLocalisationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EtudiantLocalisationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
