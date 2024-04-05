import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MobileInterfaceComponent } from './mobile-interface.component';

describe('MobileInterfaceComponent', () => {
  let component: MobileInterfaceComponent;
  let fixture: ComponentFixture<MobileInterfaceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MobileInterfaceComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MobileInterfaceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
