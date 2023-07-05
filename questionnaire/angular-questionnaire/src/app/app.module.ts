import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HttpClientModule } from "@angular/common/http";
import { CommonModule } from "@angular/common";
import { HeaderComponent } from "./components/header/header.component";
import { UserProfileComponent } from "./components/user_profile/user_profile.component";
import { ErrorComponent } from './components/error/error.component';
import {FormsModule} from "@angular/forms";
import { FilterUsersPipe } from './pipes/filter-users.pipe';
import { ModalCreateUserComponent } from './components/modal-create-user/modal-create-user.component';
import { CreateUserComponent } from './components/create-user/create-user.component';
import { FieldNameComponent } from './little-components/field-name/field-name.component';
import { TextFieldComponent } from './little-components/text-field/text-field.component';
import { TextFieldPlaceholderComponent } from './little-components/text-field-placeholder/text-field-placeholder.component';
import { HorizontalLineComponent } from './little-components/horizontal-line/horizontal-line.component';
import { DarkHLineComponent } from './little-components/dark-h-line/dark-h-line.component';
import { LightHLineComponent } from './little-components/light-h-line/light-h-line.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    UserProfileComponent,
    ErrorComponent,
    FilterUsersPipe,
    ModalCreateUserComponent,
    CreateUserComponent,
    FieldNameComponent,
    TextFieldComponent,
    TextFieldPlaceholderComponent,
    HorizontalLineComponent,
    DarkHLineComponent,
    LightHLineComponent,
  ],
    imports: [
        BrowserModule,
        HttpClientModule,
        CommonModule,
        FormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
