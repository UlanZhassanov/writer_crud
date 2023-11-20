package com.writercrud.writer_crud.controller;

import com.writercrud.writer_crud.model.Label;
import com.writercrud.writer_crud.repository.GsonLabelRepositoryImpl;
import com.writercrud.writer_crud.repository.LabelRepository;

import java.util.List;

public class LabelController {
    LabelRepository labelRepository = new GsonLabelRepositoryImpl();

    public Label saveLabel(Label label){
        Label savedLabel = labelRepository.save(label);
        return savedLabel;
    }
}
