import {Injectable} from "@angular/core";
import 'rxjs/add/operator/map';
import {Observable} from "rxjs/Observable";
import {Subscriber} from "rxjs/Subscriber";
import {Item} from "../items/item";
@Injectable()


export class ItemsDataDBService {
  items: Item[] = [
    {
      "id": 1,
      "name": "ePhone",
      "des": "12345678901234567890123456789012345678901234567890123",
      "image": "images/[id].jpg",
      "amount": 2,
      "rate": 2.6,
      "price": 10000
    },
    {
      "id": 2,
      "name": "iPhone",
      "des": "12345678901234567890123456789012345678901234567890",
      "image": "images/[id].jpg",
      "amount": 3,
      "rate": 2.3,
      "price": 10000
    }
  ]

  getItemsData() {
    return new Observable<Item>((subscriber: Subscriber<Item[]>) => subscriber.next(this.items));
  }

  addItem(item: Item) {
    console.log(item);
    item.id = this.items.length+1;
    this.items.push(item);
    console.log(this.items);
  }

  getItem(id: number) {
    let item = this.items.find(item => item.id === +id);
    return new Observable<Item>((subscriber: Subscriber<Item>) => subscriber.next(item));
  }


}
