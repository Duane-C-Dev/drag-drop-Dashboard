import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { UserListComponent } from './components/user-list/user-list.component';
import { UserCreateComponent } from './components/user-create/user-create.component';
import { HttpClientModule } from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { UserEditComponent } from './components/user-edit/user-edit.component';
import { UserDashboardComponent } from './components/user-dashboard/user-dashboard.component';
import { DragulaModule } from "ng2-dragula";
import { UserDashboardCreateModalComponent } from './components/user-dashboard-create-modal/user-dashboard-create-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    UserListComponent,
    UserCreateComponent,
    UserEditComponent,
    UserDashboardComponent,
    UserDashboardCreateModalComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        NgbModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        DragulaModule.forRoot()
    ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [UserDashboardCreateModalComponent]
})
export class AppModule { }
