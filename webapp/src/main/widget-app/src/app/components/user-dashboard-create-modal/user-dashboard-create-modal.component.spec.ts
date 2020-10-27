import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserDashboardCreateModalComponent } from './user-dashboard-create-modal.component';

describe('UserDashboardCreateModalComponent', () => {
  let component: UserDashboardCreateModalComponent;
  let fixture: ComponentFixture<UserDashboardCreateModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserDashboardCreateModalComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserDashboardCreateModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
