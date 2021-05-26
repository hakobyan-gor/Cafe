package com.cafe.service.product;

import com.cafe.exceptions.EntityNotFoundException;
import com.cafe.model.entity.product.Product;
import com.cafe.service.CommonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService extends CommonService<Product> {

    List<Product> findAllByIdIn(List<Long> productIdList) throws EntityNotFoundException;

}