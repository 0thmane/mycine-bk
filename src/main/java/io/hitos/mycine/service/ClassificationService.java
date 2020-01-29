package io.hitos.mycine.service;

import io.hitos.mycine.model.Classification;
import io.hitos.mycine.repository.ClassificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassificationService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClassificationRepository classificationRepository;

    public Iterable<Classification> findAll(){
        return classificationRepository.findAll();
    }
}
