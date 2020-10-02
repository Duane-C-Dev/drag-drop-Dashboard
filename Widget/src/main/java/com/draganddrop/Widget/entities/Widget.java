package com.draganddrop.Widget.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "widget")
@Getter
@Setter
@Accessors(chain = true)

public class Widget {
    @Id
    @Column(name = "widget_id")
    @Type(type = "uuid-char")
    private UUID widgetId;
    @Column(name = "widget_name")
    private String widgetName;
    @Column(name = "widget_type")
    private String widgetType;

    public static Widget newInstance() {
        Widget widget = new Widget();
        widget.setWidgetId(UUID.randomUUID());
        return widget;
    }
}
