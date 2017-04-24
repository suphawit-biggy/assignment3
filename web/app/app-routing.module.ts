import {Routes, RouterModule} from "@angular/router";
import {NgModule} from "@angular/core";
import {FileNotFoundComponent} from "./filenotfound/file-not-found-component";
import {ItemsAddComponent} from "./items/add/items.add.component";
import {ItemsViewComponent} from "./items/view/items.view.component";
import {ItemsComponent} from "./items/list/items.component";
const AppRoutes: Routes = [
  {path: 'add', component: ItemsAddComponent},
  {path: 'detail/:id', component: ItemsViewComponent},
  {path: 'view', component: ItemsViewComponent},
  {path: 'list', component: ItemsComponent},
  {path: '', redirectTo: '/add', pathMatch: 'full'},
  {path: '**', component: FileNotFoundComponent}
];
@NgModule({

  imports: [RouterModule.forRoot(AppRoutes)],
  exports: [RouterModule]

})
export class AppRoutingModule {
}
