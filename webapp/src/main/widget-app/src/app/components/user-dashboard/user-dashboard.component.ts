import {AfterViewInit, Component} from '@angular/core';
import {DragulaService} from "ng2-dragula";
import {Subscription} from "rxjs";

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements AfterViewInit {

  widgetList: number[] = [1,2,3,4];
  subs: Subscription = new Subscription();

  constructor(private dragulaService: DragulaService) {
    dragulaService.createGroup("WIDGETS", {
      copy: (el: Element, source: Element): boolean => {
        return source.id === 'widgetList';
      },
      accepts: (el: Element, target: Element) => {
        return target.id !== 'widgetList';
      },
      removeOnSpill: true,
      moves: (el, container, handle) => {
        return handle.className === 'handle';
      }
    });
  }

  ngAfterViewInit() {
  }

  ngOnDestroy() {
    this.subs.unsubscribe();
  }

}
