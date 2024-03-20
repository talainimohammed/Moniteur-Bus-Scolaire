import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusProfileComponent } from './bus-profile.component';

describe('BusProfileComponent', () => {
  let component: BusProfileComponent;
  let fixture: ComponentFixture<BusProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BusProfileComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BusProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
