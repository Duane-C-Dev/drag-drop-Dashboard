import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {WidgetModel} from "../models/widget.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})

const BASE_URI = './api/v1/widgets/';

export class WidgetService {

  constructor(private http: HttpClient) { }

  getAllWidget(): Observable<WidgetModel[]> {
    return this.http.get<WidgetModel[]>(BASE_URI);
  }

  getWidget(widgetId: string): Observable<WidgetModel> {
    return this.http.get<WidgetModel>(BASE_URI + widgetId);
  }

  createWidget(widget: WidgetModel): Observable<void> {
    return this.http.post<void>(BASE_URI, widget);
  }

  deleteWidget(widgetId: string): Observable<void> {
    return this.http.delete<void>(BASE_URI + widgetId);
  }

  updateWidget(widgetId: string, widget: WidgetModel): Observable<void> {
    return this.http.put<void>(BASE_URI + widgetId, widget);
  }
}
