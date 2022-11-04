package com.springboot.jpa.data.dao.impl;


import com.springboot.jpa.data.dao.ProductDAO;
import com.springboot.jpa.data.entity.Product;
import com.springboot.jpa.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;

    @Override
    public Product insertProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public Product selectProduct(Long number) {
        return productRepository.findById(number).get();
        // findById 의 return 값이 Optional<Product> 이므로 .get() 으로 해당 객체를 가져옴.
    }

    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Optional<Product> selectedProduct=productRepository.findById(number);

        Product updatedProduct;
        if(selectedProduct.isPresent()){
            Product product= selectedProduct.get();
            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());
            updatedProduct= productRepository.save(product);
        }else{
            throw new Exception();
        }
        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {

        Optional<Product> selectedProduct= productRepository.findById(number);
        if(selectedProduct.isPresent()){
            Product product= selectedProduct.get();
            productRepository.delete(product);
        }
    }
}
