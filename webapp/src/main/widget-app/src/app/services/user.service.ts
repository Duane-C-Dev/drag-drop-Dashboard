import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserModel} from "../models/user.model";

@Injectable({
  providedIn: 'root'
})

const BASE_URI = 'api/v1/users/';

export class UserService {

  constructor(private http: HttpClient) { }

  getAllUser(): Observable<UserModel[]> {
    return this.http.get<UserModel[]>(BASE_URI);
  }

  getUser(userId: string): Observable<UserModel> {
    return this.http.get<UserModel>(BASE_URI + userId);
  }

  createUser(user: UserModel): Observable<void> {
    return this.http.post<void>(BASE_URI, user);
  }

  deleteUser(userId: string): Observable<void> {
    return this.http.delete<void>(BASE_URI + userId);
  }

  update(userId: string, user: UserModel): Observable<void> {
    return this.http.put<void>(BASE_URI + userId, user);
  }
}
