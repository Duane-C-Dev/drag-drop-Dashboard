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
@Table(name = "user_widget")
@Getter
@Setter
@Accessors(chain = true)

public class UserWidget {
    @Id
    @Column(name = "user_widget_id")
    @Type(type = "uuid-char")
    private UUID userWidgetId;
    @Column(name = "user_dashboard_id")
    @Type(type = "uuid-char")
    private UUID userDashboardId;
    @Column(name = "widget_id")
    @Type(type = "uuid-char")
    private UUID widgetId;
    @Column(name = "position_x")
    private String positionX;
    @Column(name = "position_y")
    private String positionY;

    public static UserWidget newInstance() {
        UserWidget userWidget = new UserWidget();
        userWidget.setUserWidgetId(UUID.randomUUID());
        return userWidget;
    }
}
