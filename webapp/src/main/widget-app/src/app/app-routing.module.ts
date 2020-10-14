import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserListComponent} from "./components/user-list/user-list.component";
import {UserCreateComponent} from "./components/user-create/user-create.component";
import {UserEditComponent} from "./components/user-edit/user-edit.component";
import {UserDashboardComponent} from "./components/user-dashboard/user-dashboard.component";
import {HashLocationStrategy, LocationStrategy} from "@angular/common";

const routes: Routes = [
  {
    path: '',
    component: UserListComponent
  },
  {
    path: 'createUser',
    component: UserCreateComponent
  },
  {
    path: 'editUser',
    component: UserEditComponent
  },
  {
    path: 'userDashboard',
    component: UserDashboardComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [
    {provide: LocationStrategy, useClass: HashLocationStrategy},
  ]
})
export class AppRoutingModule { }
