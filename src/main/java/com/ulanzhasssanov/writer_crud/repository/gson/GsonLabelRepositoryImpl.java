package com.ulanzhasssanov.writer_crud.repository.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ulanzhasssanov.writer_crud.enums.Status;
import com.ulanzhasssanov.writer_crud.model.Label;
import com.ulanzhasssanov.writer_crud.repository.LabelRepository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonLabelRepositoryImpl implements LabelRepository {

    private final String jsonFilePath;

    public GsonLabelRepositoryImpl(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }


    @Override
    public Label getById(Integer id) {
        List<Label> labels = getAll();
        if (labels != null) {
            for (Label label : labels) {
                if (id.equals(label.getId())) {
                    return label;
                }
            }
        }
        return null; // Label with the given id not found
    }

    @Override
    public List<Label> getAll() {
        try (FileReader reader = new FileReader(jsonFilePath)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Label>>(){}.getType();

            // Deserialize the JSON file into a List<Label>
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();  // Handle the exception according to your needs
            return new ArrayList<>();
        }
    }

    @Override
    public Label save(Label label) {

        List<Label> labels = getAll();
        if (labels == null) {
            labels = new ArrayList<>();
            label.setId(1);
        } else {
            int lastLabelId = labels.get(labels.size() - 1).getId();
            label.setId(lastLabelId + 1);
        }

        labels.add(label);
        saveAll(labels);

        return label;
    }

    @Override
    public Label update(Label label) {

        List<Label> labels = getAll();

        if (labels != null) {
            for (int i = 0; i < labels.size(); i++) {
                if (labels.get(i).getId() == label.getId()) {
                    labels.set(i, label);

                    saveAll(labels);

                    return label;
                }
            }
        }

        return null;
    }

    @Override
    public void deleteById(Integer id) {
        Label label = getById(id);
        label.setStatus(Status.DELETED);

        update(label);
    }

    private void saveAll(List<Label> labels) {
        try (FileWriter writer = new FileWriter(jsonFilePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(labels, writer);
        } catch (IOException e) {
            e.printStackTrace();  // Handle the exception according to your needs
        }
    }
}
