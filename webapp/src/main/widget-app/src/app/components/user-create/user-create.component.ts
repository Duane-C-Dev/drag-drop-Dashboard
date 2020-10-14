import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit {

  form: FormGroup;

  constructor(private fb: FormBuilder,
              private userService: UserService,
              private router: Router) {
    this.form = this.fb.group({
      'username': ['', Validators.required],
      'firstName': ['', Validators.required],
      'lastName': ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  onSubmit() {
    this.userService.createUser(this.form.value).subscribe(() => this.router.navigateByUrl(''));
  }


}
