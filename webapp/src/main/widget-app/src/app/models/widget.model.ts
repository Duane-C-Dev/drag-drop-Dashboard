export class WidgetModel {
  constructor(widgetId: string, widgetName: string, widgetType: string) { //todo verify if this constructor is needed
    this.widgetId = widgetId;
    this.widgetName = widgetName;
    this.widgetType = widgetType;
  }

  widgetId: string;
  widgetName: string;
  widgetType: string;
}

