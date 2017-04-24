import {Injectable} from '@angular/core';
import {Http} from '@angular/http';
import 'rxjs/add/operator/map';
import {Item} from "../items/item";
@Injectable()
export class ItemsDataService {
  constructor(private http: Http){}
  getItemsData(){
    let itemArray:Item[];
    return this.http.get('app/data/stock.json')
      .map(res => res.json().items);


  }
  getItem(id:number){
    return null;
  }


  addItem(item: Item) {

  }
}
