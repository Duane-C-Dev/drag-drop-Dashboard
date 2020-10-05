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
@Table(name = "user_dashboard")
@Getter
@Setter
@Accessors(chain = true)

public class UserDashboard {
    @Id
    @Column(name = "user_dashboard_id")
    @Type(type = "uuid-char")
    private UUID userDashboardId;
    @Column(name = "user_dashboard_name")
    private String userDashboardName;
    @Column(name = "user_id")
    @Type(type = "uuid-char")
    private UUID userId;

    public static UserDashboard newInstance() {
        UserDashboard userDashboard = new UserDashboard();
        userDashboard.setUserDashboardId(UUID.randomUUID());
        return userDashboard;
    }

}
