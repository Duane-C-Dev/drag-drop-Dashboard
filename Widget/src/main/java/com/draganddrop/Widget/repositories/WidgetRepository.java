package com.draganddrop.Widget.repositories;

import com.draganddrop.Widget.entities.Widget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WidgetRepository extends JpaRepository<Widget, UUID> {
}
