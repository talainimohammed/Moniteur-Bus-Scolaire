import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SuiviBusComponent } from './suivi-bus.component';

describe('SuiviBusComponent', () => {
  let component: SuiviBusComponent;
  let fixture: ComponentFixture<SuiviBusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SuiviBusComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SuiviBusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
