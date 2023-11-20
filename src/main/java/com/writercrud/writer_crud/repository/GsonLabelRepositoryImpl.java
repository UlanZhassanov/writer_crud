package com.writercrud.writer_crud.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.writercrud.writer_crud.model.Label;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GsonLabelRepositoryImpl implements LabelRepository {

    private Gson gson;

    public GsonLabelRepositoryImpl() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public Label getById(Integer id) {
        return null;
    }

    public List<Label> getAll() {
        return null;
    }

    public Label save(Label label) {
        try (FileWriter writer = new FileWriter("src/main/resources/data/labels.json")){
            gson.toJson(label, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return label;
    }

    public Label update(Label label) {
        return null;
    }

    public void deleteById(Integer id) {

    }
}
