import {AfterViewInit, Component} from '@angular/core';
import {DragulaService} from "ng2-dragula";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements AfterViewInit {

  userId: string;
  widgets: string;
  widgetList: number[] = [1,2,3,4];
  subs: Subscription = new Subscription();

  constructor(private dragulaService: DragulaService,
              private router: Router,
              private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => {
      this.userId = params['userId'];
      this.widgets = params['userId'];
    });

    dragulaService.createGroup(this.widgets, {
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

    this.subs.add(this.dragulaService.drop(this.widgets)
      .subscribe(({el, target, source, sibling }) => {
        if(source.id === 'widgetList') {
          el.classList.add('resize');
        }
      })
    );

  }

  ngAfterViewInit() {
  }

  ngOnDestroy() {
    this.dragulaService.destroy(this.widgets);
    this.subs.unsubscribe();
  }

}
