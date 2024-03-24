import { TestBed } from '@angular/core/testing';

import { SuiviBusService } from './suivi-bus.service';

describe('SuiviBusService', () => {
  let service: SuiviBusService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SuiviBusService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
