package com.pb.fashion.services;

import com.pb.fashion.models.ProductCategory;
import com.pb.fashion.repositories.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    //Request category list
    public List<ProductCategory> getProductCategoryList() {
        return productCategoryRepository.findAll();
    }

    //Save new category
    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    //Receive an existing id ProductCategoryClass to be updated
    public ProductCategory editProductCategory(ProductCategory productCategory){

        Optional<ProductCategory> optionalProductCategory = productCategoryRepository.findById(productCategory.getId());
        //if category exist
        if (optionalProductCategory.isPresent()){
            ProductCategory existingProductCategory = optionalProductCategory.get();
            existingProductCategory.setName(productCategory.getName());
            existingProductCategory.setImageUrl(productCategory.getImageUrl());
            return productCategoryRepository.save(existingProductCategory);
        }else {
            throw new NoSuchElementException("Product category with id" + productCategory.getId() + "not found");
        }

    }
}
