package com.ulanzhasssanov.writer_crud.model;

import com.ulanzhasssanov.writer_crud.enums.PostStatus;

import java.time.LocalDateTime;
import java.util.List;

public class Post {
    private int id;
    private String content;
    private LocalDateTime created;
    private LocalDateTime  updated;
    private List<Label> labels;
    private PostStatus status;

    public Post() {
    }

    public Post(String content, LocalDateTime created, LocalDateTime updated, List<Label> labels, PostStatus status) {
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.labels = labels;
        this.status = status;
    }

    public Post(int id, String content, LocalDateTime created, LocalDateTime updated, List<Label> labels, PostStatus status) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
        this.labels = labels;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", created='" + created + '\'' +
                ", updated='" + updated + '\'' +
                ", labels=" + labels +
                ", status=" + status +
                '}';
    }
}
