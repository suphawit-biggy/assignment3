import {Component} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import 'rxjs/add/operator/switchMap';
import {ItemsDataService} from "../../service/items-data.service";
import {Item} from "../item";

@Component({
  selector: 'items-view',
  templateUrl: 'app/items/view/items.view.component.html',
  styleUrls: ['app/items/view/items.view.component.css']
})
export class ItemsViewComponent {
  constructor(private route: ActivatedRoute, private itemDataService: ItemsDataService) {
  }

  item: Item;

  ngOnInit() {
    this.route.params
      .switchMap((params: Params) => this.itemDataService.getItem(+params['id']))
      .subscribe((item: Item) => this.item = item);
  }
}
