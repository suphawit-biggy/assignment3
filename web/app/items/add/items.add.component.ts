import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {ItemsDataService} from "../../service/items-data.service";
import {Item} from "../item";


@Component({
  selector: 'items-add',
  templateUrl: 'app/items/add/items.add.component.html',
  styleUrls: ['app/items/add/items.add.component.css']
})
export class ItemsAddComponent {
  item: any = {};

  constructor(private itemDataService: ItemsDataService, private router: Router) {
  };

  onFileChange(event, item: any) {
    var filename = event.target.files[0].name;
    console.log(filename);
    item.image = filename;
  }

  addItem(item: Item) {
    console.log(item)
    this.itemDataService.addItem(item);
    alert("Add complete");
    this.router.navigate(['/list']);
  }
}

