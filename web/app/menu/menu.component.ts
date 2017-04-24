import {Component} from '@angular/core';
import {ItemsDataService} from "../service/items-data.service";
import {Router} from "@angular/router";
import {Item} from "../items/item";

@Component({
  selector: 'menu',
  templateUrl: 'app/menu/menu.component.html',
  styleUrls:['app/menu/menu.component.css']

})
export class MenuComponent{
  constructor(private itemDataService: ItemsDataService, private router: Router ) {
  }
  items:Item[];
  ngOnInit() {
    this.itemDataService.getItemsData()
      .subscribe(items => this.items = items);
  }
  showDetail(item: Item){
    this.router.navigate(['/detail',item.id]);
  }
}
