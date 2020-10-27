import { Component, OnInit } from '@angular/core';
import {UserModel} from "../../models/user.model";
import {UserService} from "../../services/user.service";
import {NavigationExtras, Router} from "@angular/router";
import {UserDashboardService} from "../../services/user-dashboard.service";
import {UserDashboardModel} from "../../models/user-dashboard.model";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: UserModel[] = [];

  constructor(private userService: UserService,
              private userDashboardService: UserDashboardService,
              private router: Router) { }

  ngOnInit(): void {
    this.userService.getAllUser().subscribe(users => this.users = users);
  }

  onCreateUser():void {
    this.router.navigateByUrl('/createUser');
  }

  onDeleteUser(userId: string): void {
    this.userService.deleteUser(userId).subscribe();
    window.location.reload(); //todo re-evaluate if this reload is ok to do
  }

  onEditUser(userId: string): void {
    const navigationExtras: NavigationExtras = {
      queryParams: {
        'userId': userId
      }
    };
    this.router.navigate(['/editUser'], navigationExtras);
  }

  onDashboard(userId: string): void {
    const navigationExtras: NavigationExtras = {
      queryParams: {
        'userId': userId
      }
    };

    this.userDashboardService.getAllUserDashboard(userId).subscribe( dashboards => {
      if (dashboards.length < 1) {
        let defaultDashboard: UserDashboardModel = {userDashboardId: '', userDashboardName: 'Default', userId: userId};
        this.userDashboardService.createUserDashboard(userId,defaultDashboard).subscribe(() => {
          this.router.navigate(['/userDashboard'], navigationExtras);
        });
      } else {
        this.router.navigate(['/userDashboard'], navigationExtras);
      }
    });
  }

  // private setQueryParams(paramArray: string[]){
  //   let params: { [index: string] : {value: string}} = {};
  //   for(let param of paramArray) {
  //     params += `\'${param}\': ${param};`
  //   }
  //   console.log(params);
  //   // const navEx = NavigationExtras = {
  //   //   queryParams: {
  //   //
  //   //   }
  //   // }
  // }
}
