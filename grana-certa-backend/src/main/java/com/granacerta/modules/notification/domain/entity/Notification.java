package com.granacerta.modules.notification.domain.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Notification {
    private Long id;
    private Long userId;
    private String type;
    private String title;
    private String message;
    private String priority;
    private boolean read;
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}