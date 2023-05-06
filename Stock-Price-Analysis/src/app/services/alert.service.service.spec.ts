import { TestBed } from '@angular/core/testing';

import { Alert.ServiceService } from './alert.service.service';

describe('Alert.ServiceService', () => {
  let service: Alert.ServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Alert.ServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
