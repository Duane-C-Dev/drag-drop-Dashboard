package com.draganddrop.Widget.repositories;

import com.draganddrop.Widget.entities.UserWidget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserWidgetRepository extends JpaRepository<UserWidget, UUID> {
    List<UserWidget> findAllByUserDashboardId(UUID userDashboardId);
}
