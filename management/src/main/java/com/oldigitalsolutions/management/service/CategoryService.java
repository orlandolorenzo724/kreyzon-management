package com.oldigitalsolutions.management.service;

import com.oldigitalsolutions.management.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends BaseService {

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
