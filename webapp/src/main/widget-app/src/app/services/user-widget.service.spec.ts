import { TestBed } from '@angular/core/testing';

import { UserWidgetService } from './user-widget.service';

describe('UserWidgetService', () => {
  let service: UserWidgetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(UserWidgetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
