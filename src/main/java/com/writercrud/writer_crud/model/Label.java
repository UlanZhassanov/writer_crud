package com.writercrud.writer_crud.model;

public class Label {
    private int id;
    private String name;
    private PostStatus status;

    public Label() {
    }

    public Label(String name, PostStatus status) {
        this.name = name;
        this.status = status;
    }

    public Label(int id, String name, PostStatus status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(PostStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Label{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
