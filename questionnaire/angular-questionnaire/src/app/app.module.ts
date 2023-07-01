import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HttpClientModule } from "@angular/common/http";
import { CommonModule } from "@angular/common";
import { HeaderComponent } from "./components/header/header.component";
import { UserProfileComponent } from "./components/user_profile/user_profile.component";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    UserProfileComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CommonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
