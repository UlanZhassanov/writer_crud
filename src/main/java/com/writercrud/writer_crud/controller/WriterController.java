package com.writercrud.writer_crud.controller;

import com.writercrud.writer_crud.model.Writer;
import com.writercrud.writer_crud.repository.GsonWriterRepositoryImpl;
import com.writercrud.writer_crud.repository.WriterRepository;

import java.util.List;

public class WriterController {
    WriterRepository writerRepository = new GsonWriterRepositoryImpl("src/main/resources/data/writers.json");

    public Writer saveWriter(Writer writer){
        Writer savedWriter = writerRepository.save(writer);
        return savedWriter;
    }

    public List<Writer> getAllWriters(){
        List<Writer> writers = writerRepository.getAll();
        return writers;
    }

    public Writer getWriterById(Integer id){
        Writer writer = writerRepository.getById(id);
        return writer;
    }

    public Writer updateWriter(Writer writer){
        Writer updatedWriter = writerRepository.update(writer);
        return updatedWriter;
    }

    public void deleteWriter(Integer id){
        writerRepository.deleteById(id);
    }
}
