import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {DragulaService} from "ng2-dragula";
import {Subscription} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {WidgetModel} from "../../models/widget.model";
import {WidgetService} from "../../services/widget.service";
import {UserWidgetService} from "../../services/user-widget.service";
import {UserDashboardModel} from "../../models/user-dashboard.model";
import {UserDashboardService} from "../../services/user-dashboard.service";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {UserWidgetModel} from "../../models/user-widget.model";

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class UserDashboardComponent implements OnInit {

  userId: string;
  widgets: string;
  currentDashboardId: string;
  widgetList: WidgetModel[] = [];
  userDashboardList: UserDashboardModel[] = [];
  userWidgetList: UserWidgetModel[] = [];
  subs: Subscription = new Subscription();

  constructor(private dragulaService: DragulaService,
              private widgetService: WidgetService,
              private userDashboardService: UserDashboardService,
              private userWidgetService: UserWidgetService,
              private router: Router,
              private route: ActivatedRoute,
              private modalService: NgbModal) {

    this.route.queryParams.subscribe(params => {
      this.userId = params['userId'];
      this.widgets = params['userId'];
    });

    this.widgetService.getAllWidget().subscribe(widgets => { this.widgetList = widgets; });
    this.userDashboardService.getAllUserDashboard(this.userId).subscribe(userDashboards => {
      this.userDashboardList = userDashboards;
      if (this.userDashboardList.length >= 1) {
        this.currentDashboardId = this.userDashboardList[0].userDashboardId;
      }
      this.loadUserDashboardWidgets();
    });

  }

  ngOnInit() {
    this.dragulaService.createGroup(this.widgets, {
      copy: (el: Element, source: Element): boolean => {
        return source.id === 'widgetList';
      },
      copyItem: (widget: WidgetModel) => {
        return new WidgetModel(widget.widgetId, widget.widgetName, widget.widgetType);
      },
      accepts: (el: Element, target: Element) => {
        return target.id !== 'widgetList';
      },
      removeOnSpill: true,
      moves: (el, container, handle) => {
        return handle.className === 'handle';
      }
    });

    this.subs.add(this.dragulaService.dropModel(this.widgets)
      .subscribe(({el, target, source, item,  sourceModel, targetModel}) =>
      {
        let coordinates = target.id.match(/\d+/g);

        if(source.id === 'widgetList' && this.checkContainerEmpty(coordinates)) {
          let userWidget: UserWidgetModel = {
            userWidgetId: '',
            widgetId: item.widgetId,
            userDashboardId: this.currentDashboardId,
            positionX: coordinates[0],
            positionY: coordinates[1]
          }

          this.userWidgetService.createUserWidget(this.userId, this.currentDashboardId, userWidget).subscribe(() => {
            this.loadUserDashboardWidgets();
            console.log('CREATE!');
          });
        } else if (this.checkContainerEmpty(coordinates)) {
          let userWidget: UserWidgetModel = {
            userWidgetId: item.userWidgetId,
            widgetId: item.widgetId,
            userDashboardId: this.currentDashboardId,
            positionX: coordinates[0],
            positionY: coordinates[1]
          }
          this.userWidgetService.updateUserWidget(this.userId, this.currentDashboardId, item.userWidgetId, userWidget).subscribe(() => {
            this.loadUserDashboardWidgets();
            console.log('UPDATE!');
          })
        }
      })
    );

    this.subs.add(this.dragulaService.removeModel(this.widgets)
      .subscribe(({el, container, source, item, sourceModel, sourceIndex}) => {
        console.log('Remove Model!');
        this.userWidgetService.deleteUserWidget(this.userId, this.currentDashboardId, item.userWidgetId).subscribe( () => {
          this.loadUserDashboardWidgets();
        })
      }));
  }

  loadUserDashboardWidgets() {
    this.userWidgetService.getAllUserWidget(this.userId, this.currentDashboardId).subscribe( (userDashboardWidgets) => {
      this.userWidgetList = userDashboardWidgets;

      for (let x = 1; x < 3; x++) {
        for (let y = 1; y < 3; y++){
          let zone = '(' + x + ',' + y + ')';
          document.getElementById(zone).innerHTML = '';
          console.log('clear');
        }
      }

      for (let userWidget of this.userWidgetList) {
        let widgetId = userWidget.widgetId;
        let coordinate: string = '(' + userWidget.positionX + ',' + userWidget.positionY + ')';
        for(let widget of this.widgetList) {
          if(widget.widgetId === widgetId){
            document.getElementById(coordinate).insertAdjacentHTML("afterbegin", `<div class="box resize"><span class="handle"> ${widget.widgetName} </span></div>`);
          }

        }
      }
    });
  }

  checkContainerEmpty(targetCoordinates: RegExpMatchArray): boolean {
    if(this.userWidgetList.length < 1) {
      return true;
    } else {
      for( let widget of this.userWidgetList) {
        if(widget.positionX === targetCoordinates[0] && widget.positionY === targetCoordinates[1]) {
          return false;
        }
      }
      return true;
    }
  }

  ngOnDestroy() {
    this.dragulaService.destroy(this.widgets);
    this.subs.unsubscribe();
  }

  onUsers() {
    this.router.navigateByUrl('');
  }

  // onLoadDashboard(index: number) {
  //   this.currentDashboardId = this.userDashboardList[index].userDashboardId;
  //   this.loadUserDashboardWidgets();
  //   console.log(this.currentDashboardId);
  //
  // }

  // onAddDashboard() {
  //   const modalRef = this.modalService.open(UserDashboardCreateModalComponent);
  //   modalRef.componentInstance.userId = this.userId;
  //
  //   modalRef.result.then((result) => {
  //     console.log(result);
  //     this.userDashboardService.createUserDashboard(this.userId, result).subscribe(() => this.loadUserDashboardList());
  //   }).catch((error) => {
  //     console.log(error);
  //   });
  // }

  // onDeleteDashboard() {
  //   this.userDashboardService.deleteUserDashboard(this.userId, this.currentDashboardId).subscribe(() => {
  //     this.loadUserDashboardList();
  //     this.currentDashboardId = this.userDashboardList[0].userDashboardId;
  //     this.loadUserDashboardWidgets();
  //   });
  // }

}
