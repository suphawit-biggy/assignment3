import {NgModule, Injectable} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {MenuComponent} from "./menu/menu.component";
import {LocationStrategy, HashLocationStrategy} from "@angular/common";
import {FileNotFoundComponent} from "./filenotfound/file-not-found-component";
import {AppRoutingModule} from "./app-routing.module";
import {ItemsComponent} from "./items/list/items.component";
import {ItemsAddComponent} from "./items/add/items.add.component";
import {ItemsViewComponent} from "./items/view/items.view.component";
import {ItemsDataService} from "./service/items-data.service";
import {ItemsDataDBService} from "./service/items-data-db.service";


@NgModule({
  declarations: [AppComponent,
    ItemsComponent,
    ItemsAddComponent,
    ItemsViewComponent,
    MenuComponent, FileNotFoundComponent],
  imports: [BrowserModule, FormsModule, HttpModule, AppRoutingModule],
  bootstrap: [AppComponent],
  providers: [{provide: ItemsDataService, useClass: ItemsDataDBService}, {
    provide: LocationStrategy,
    useClass: HashLocationStrategy
  }]
})
export class AppModule {
}




