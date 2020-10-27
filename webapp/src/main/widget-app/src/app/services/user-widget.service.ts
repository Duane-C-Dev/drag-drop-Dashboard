import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserWidgetModel} from "../models/user-widget.model";

const BASE_URI = 'api/v1/users/';

@Injectable({
  providedIn: 'root'
})
export class UserWidgetService {

  constructor(private http: HttpClient) { }

  getAllUserWidget(userId: string, userDashboardId: string): Observable<UserWidgetModel[]> {
    return this.http.get<UserWidgetModel[]>(BASE_URI + userId + '/userDashboards/' + userDashboardId + '/userWidgets');
  }

  getUserWidget(userId: string, userDashboardId: string, userWidgetId: string): Observable<UserWidgetModel> {
    return this.http.get<UserWidgetModel>(BASE_URI + userId + '/userDashboards/' + userDashboardId + '/userWidgets/' + userWidgetId);
  }

  createUserWidget(userId: string, userDashboardId: string, userWidget: UserWidgetModel): Observable<void> {
    return this.http.post<void>(BASE_URI + userId + '/userDashboards/' + userDashboardId + '/userWidgets', userWidget);
  }

  deleteUserWidget(userId: string, userDashboardId: string, userWidgetId: string): Observable<void> {
    return this.http.delete<void>(BASE_URI + userId + '/userDashboards/' + userDashboardId + '/userWidgets/' + userWidgetId);
  }

  updateUserWidget(userId: string, userDashboardId: string, userWidgetId: string, userWidget: UserWidgetModel): Observable<void> {
    return this.http.put<void>(BASE_URI + userId + '/userDashboards/' + userDashboardId + '/userWidgets/' + userWidgetId, userWidget);
  }
}
