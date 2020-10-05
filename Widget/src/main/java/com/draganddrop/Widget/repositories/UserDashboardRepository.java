package com.draganddrop.Widget.repositories;

import com.draganddrop.Widget.entities.UserDashboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserDashboardRepository extends JpaRepository<UserDashboard, UUID> {
    List<UserDashboard> findAllByUserId(UUID userId);
}
