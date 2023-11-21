package com.writercrud.writer_crud.controller;

import com.writercrud.writer_crud.model.Label;
import com.writercrud.writer_crud.repository.GsonLabelRepositoryImpl;
import com.writercrud.writer_crud.repository.LabelRepository;

import java.util.List;

public class LabelController {
    LabelRepository labelRepository = new GsonLabelRepositoryImpl("src/main/resources/data/labels.json");

    public Label saveLabel(Label label){
        Label savedLabel = labelRepository.save(label);
        return savedLabel;
    }

    public List<Label> getAllLabels(){
        List<Label> labels = labelRepository.getAll();
        return labels;
    }

    public Label getLabelById(Integer id){
        Label label = labelRepository.getById(id);
        return label;
    }

    public Label updateLabel(Label label){
        Label updatedLabel = labelRepository.update(label);
        return updatedLabel;
    }

    public void deleteLabel(Integer id){
        labelRepository.deleteById(id);
    }
}
