package com.draganddrop.webapp.clients;

import com.draganddrop.webapp.dto.UserDashboardDto;
import com.draganddrop.webapp.dto.UserDto;
import com.draganddrop.webapp.dto.UserWidgetDto;
import com.draganddrop.webapp.dto.WidgetDto;
import lombok.Setter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import java.util.List;
import java.util.UUID;

public class BFFClient {

    private Client client = ClientBuilder.newClient();

    @Setter
    private String baseUri;

    //USERS
    private WebTarget userTarget() {
        return client.target(baseUri)
                .path("api")
                .path("v1")
                .path("users");
    }

    public List<UserDto> findAllUser() {
        return userTarget()
                .request()
                .get(new GenericType<List<UserDto>>() {
                });
    }

    public UserDto findUser(UUID userId) {
        return userTarget()
                .path(userId.toString())
                .request()
                .get(UserDto.class);
    }

    public void createUser(UserDto dto) {
        userTarget()
            .request()
            .post(Entity.json(dto), Void.class);
    }

    public void deleteUser(UUID userId) {
        userTarget()
            .path(userId.toString())
            .request()
            .delete(Void.class);
    }

    public void updateUser(UUID userId, UserDto dto) {
        userTarget()
            .path(userId.toString())
            .request()
            .put(Entity.json(dto), Void.class);
    }

    //WIDGETS
    private WebTarget widgetTarget() {
        return client.target(baseUri)
                .path("api")
                .path("v1")
                .path("widgets");
    }

    public List<WidgetDto> findAllWidget() {
        return widgetTarget()
                .request()
                .get(new GenericType<List<WidgetDto>>() {
                });
    }

    public WidgetDto findWidget(UUID widgetId) {
        return widgetTarget()
                .path(widgetId.toString())
                .request()
                .get(WidgetDto.class);
    }

    public void createWidget(WidgetDto dto) {
        widgetTarget()
            .request()
            .post(Entity.json(dto), Void.class);
    }

    public void deleteWidget(UUID widgetId) {
        widgetTarget()
            .path(widgetId.toString())
            .request()
            .delete(Void.class);
    }

    public void updateWidget(UUID widgetId, WidgetDto dto) {
        widgetTarget()
            .path(widgetId.toString())
            .request()
            .put(Entity.json(dto), Void.class);
    }

    //USER DASHBOARDS
    private WebTarget userDashboardTarget(UUID userId) {
        return client.target(baseUri)
                .path("api")
                .path("v1")
                .path("users")
                .path(userId.toString())
                .path("userDashboards");
    }

    public List<UserDashboardDto> findAllUserDashboard(UUID userId) {
        return userDashboardTarget(userId)
                .request()
                .get(new GenericType<List<UserDashboardDto>>() {
                });
    }

    public UserDashboardDto findUserDashboard(UUID userId, UUID userDashboardId) {
        return userDashboardTarget(userId)
                .path(userDashboardId.toString())
                .request()
                .get(UserDashboardDto.class);
    }

    public void createUserDashboard(UUID userId, UserDashboardDto dto) {
        userDashboardTarget(userId)
            .request()
            .post(Entity.json(dto), Void.class);
    }

    public void deleteUserDashboard(UUID userId, UUID userDashboardId) {
        userDashboardTarget(userId)
                .path(userDashboardId.toString())
                .request()
                .delete(Void.class);
    }

    public void updateUserDashboard(UUID userId, UUID userDashboardId, UserDashboardDto dto) {
        userDashboardTarget(userId)
            .path(userDashboardId.toString())
            .request()
            .put(Entity.json(dto), Void.class);
    }

    //USER WIDGETS
    private WebTarget userWidgetTarget(UUID userId, UUID userDashboardId) {
        return client.target(baseUri)
                .path("api")
                .path("v1")
                .path("users")
                .path(userId.toString())
                .path("userDashboards")
                .path(userDashboardId.toString())
                .path("userWidgets");
    }

    public List<UserWidgetDto> findAllUserWidget(UUID userId, UUID userDashboardId) {
        return userWidgetTarget(userId, userDashboardId)
                .request()
                .get(new GenericType<List<UserWidgetDto>>() {
                });
    }

    public UserWidgetDto findUserWidget(UUID userId, UUID userDashboardId, UUID userUserWidgetId) {
        return userWidgetTarget(userId, userDashboardId)
                .path(userUserWidgetId.toString())
                .request()
                .get(UserWidgetDto.class);
    }

    public void createUserWidget(UUID userId, UUID userDashboardId, UserWidgetDto dto) {
        userWidgetTarget(userId, userDashboardId)
            .request()
            .post(Entity.json(dto), Void.class);
    }

    public void deleteUserWidget(UUID userId, UUID userDashboardId, UUID userUserWidgetId) {
        userWidgetTarget(userId, userDashboardId)
                .path(userUserWidgetId.toString())
                .request()
                .delete(Void.class);
    }

    public void updateUserWidget(UUID userId, UUID userDashboardId, UUID userUserWidgetId, UserWidgetDto dto) {
        userWidgetTarget(userId, userDashboardId)
            .path(userUserWidgetId.toString())
            .request()
            .put(Entity.json(dto), Void.class);
    }
}
