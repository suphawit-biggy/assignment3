import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {ItemsDataService} from "../../service/items-data.service";
import {Item} from "../item";

@Component({
 selector: 'items',
 templateUrl: 'app/items/list/items.component.html',
 styleUrls:['app/items/list/items.component.css']
})
export class ItemsComponent {
	items: Item[];

  constructor (private itemDataService:ItemsDataService, private router: Router){}
  ngOnInit(){
    this.itemDataService.getItemsData()
      .subscribe(items => this.items= items);
  }

  checkDes(str: String) {
    if (str.length <= 50) {
      return str;
    } else {
      var text ="";
      var i=0;
      for(i=0;i < 47;i++) {
        text += str[i];
      }
      text += "...";
      return text;
    }
  }

	upQuantity(item:Item){
		item.amount++;
	}

	downQuantity(item:Item){
		if (item.amount >0)
			item.amount--;
	}

	showDetail(item: Item){
	  this.router.navigate(['/detail',item.id]);
  }

}
