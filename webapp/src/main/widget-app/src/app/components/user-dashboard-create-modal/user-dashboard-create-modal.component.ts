import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-user-dashboard-create-modal',
  templateUrl: './user-dashboard-create-modal.component.html',
  styleUrls: ['./user-dashboard-create-modal.component.css']
})
export class UserDashboardCreateModalComponent {
  @Input() userId: string;
  form: FormGroup;

  constructor(public activeModal: NgbActiveModal,
              private fb: FormBuilder) {
    this.form = this.fb.group({
      'userId': ['' , Validators.required],
      'userDashboardName': ['', Validators.required],
    });
  }

  ngOnInit() {
    this.form.patchValue({
      'userId': this.userId
    });
  }

  onSubmit() {
    this.activeModal.close(this.form.value);
  }
}
