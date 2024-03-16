import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChauffeurProfileComponent } from './chauffeur-profile.component';

describe('ChauffeurProfileComponent', () => {
  let component: ChauffeurProfileComponent;
  let fixture: ComponentFixture<ChauffeurProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ChauffeurProfileComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ChauffeurProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
