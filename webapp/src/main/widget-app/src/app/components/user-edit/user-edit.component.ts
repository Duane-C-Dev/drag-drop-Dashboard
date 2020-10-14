import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UserModel} from "../../models/user.model";

@Component({
  selector: 'app-user-edit',
  templateUrl: './user-edit.component.html',
  styleUrls: ['./user-edit.component.css']
})
export class UserEditComponent implements OnInit {

  form: FormGroup;
  userId: string;
  user: UserModel;

  constructor(private fb: FormBuilder,
              private userService: UserService,
              private router: Router,
              private route: ActivatedRoute) {

    this.form = this.fb.group({
      'userId': ['', Validators.required],
      'username': ['', Validators.required],
      'firstName': ['', Validators.required],
      'lastName': ['', Validators.required]
    });

    this.route.queryParams.subscribe(params => {
      this.userId = params['userId'];
    });
  }

  ngOnInit(): void {
    this.userService.getUser(this.userId).subscribe(user => {
      this.user = user;
      this.updateForm(user);
    } );
  }

  onSubmit() { //todo there is something broken when checking for unique updated username( has to be updated or server error)
    if(this.form.value.username)
    this.userService.updateUser(this.userId, this.form.value).subscribe(() => this.router.navigateByUrl(''));
  }

  private updateForm(user: UserModel): void {
    this.form.patchValue({
      userId: this.user.userId,
      firstName: this.user.firstName,
      lastName: this.user.lastName,
      username: this.user.username
    });
  }
}
