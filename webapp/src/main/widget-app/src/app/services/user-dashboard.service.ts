import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserDashboardModel} from "../models/user-dashboard.model";

const BASE_URI = 'api/v1/users/';

@Injectable({
  providedIn: 'root'
})
export class UserDashboardService {

  constructor(private http: HttpClient) { }

  getAllUserDashboard(userId: string): Observable<UserDashboardModel[]> {
    return this.http.get<UserDashboardModel[]>(BASE_URI + userId + '/userDashboards');
  }

  getUserDashboard(userId: string, userDashboardId: string): Observable<UserDashboardModel> {
    return this.http.get<UserDashboardModel>(BASE_URI + userId + '/userDashboards/' + userDashboardId);
  }

  createUserDashboard(userId: string, userDashboard: UserDashboardModel): Observable<void> {
    return this.http.post<void>(BASE_URI + userId + '/userDashboards', userDashboard);
  }

  deleteUserDashboard(userId: string, userDashboardId: string): Observable<void> {
    return this.http.delete<void>(BASE_URI + userId + '/userDashboards/' + userDashboardId);
  }

  updateUserDashboard(userId: string, userDashboardId: string, userDashboard: UserDashboardModel): Observable<void> {
    return this.http.put<void>(BASE_URI + userId + '/userDashboards/' + userDashboardId, userDashboard)
  }
}
