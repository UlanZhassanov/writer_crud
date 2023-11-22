package com.writercrud.writer_crud.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.writercrud.writer_crud.model.PostStatus;
import com.writercrud.writer_crud.model.Writer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GsonWriterRepositoryImpl implements WriterRepository {
    private final String jsonFilePath;

    public GsonWriterRepositoryImpl(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
    }

    @Override
    public Writer getById(Integer id) {
        Writer targetWriter = null;
        List<Writer> allWriters = getAll();
        if (allWriters != null) {
            for (Writer post : allWriters) {
                if (post.getId() == id) {
                    targetWriter = post;
                }
            }
        }
        return targetWriter;
    }

    @Override
    public List<Writer> getAll() {
        try (FileReader reader = new FileReader(jsonFilePath)){
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Writer>>(){}.getType();
            return gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Writer save(Writer writer) {
        List<Writer> writerList = getAll();

        if (writerList == null) {
            writerList = new ArrayList<>();
            writer.setId(1);
        } else {
            int lastPostlId = writerList.get(writerList.size() - 1).getId();
            writer.setId(lastPostlId + 1);
        }

        writerList.add(writer);
        saveAll(writerList);

        return writer;
    }

    @Override
    public Writer update(Writer writer) {
        List<Writer> allWriters = getAll();

        if (allWriters != null) {
            for (int i = 0; i < allWriters.size(); i++) {
                if (allWriters.get(i).getId() == writer.getId()) {
                    allWriters.set(i, writer);
                    saveAll(allWriters);
                    return allWriters.get(i);
                }
            }
        }

        return null;
    }

    @Override
    public void deleteById(Integer id) {
        Writer writer = getById(id);
        writer.setStatus(PostStatus.DELETED);

        update(writer);
    }

    private void saveAll(List<Writer> writerList) {
        try (FileWriter fileWriter = new FileWriter(jsonFilePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(writerList, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();  // Handle the exception according to your needs
        }
    }
}
