package com.cafe.controller;

import com.cafe.facade.product.ProductFacadeBuilder;
import com.cafe.model.ResponseModel;
import com.cafe.model.dto.product.ProductCreatingDto;
import com.cafe.utils.Configuration;
import com.cafe.utils.FileUtil;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController extends BaseController {

    @Lazy
    private final ProductFacadeBuilder mFacadeBuilder;

    public ProductController(ProductFacadeBuilder productFacadeBuilder) {
        this.mFacadeBuilder = productFacadeBuilder;
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping
    public ResponseModel createProduct(@Valid @RequestBody ProductCreatingDto productCreatingDto) {
        try {
            return createSuccessResult(mFacadeBuilder.createProduct(productCreatingDto), "Product created successfully.");
        } catch (Exception e) {
            return createErrorResult(e);
        }
    }

    @GetMapping("getImage/{filename:.+}")
    public byte[] serveFile(@PathVariable String filename) throws IOException {
        return FileUtil.loadFile(filename, Configuration.PRODUCT_IMAGE_DIR_IN_PROJECT);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @PostMapping("uploadProductMedia/{productId}")
    public ResponseModel uploadProductMedia(
            @PathVariable Long productId,
            @RequestParam(value = "photos", required = false) List<MultipartFile> photos,
            @RequestParam(value = "coverPhoto", required = false) MultipartFile coverPhoto) {
        try {
            return createSuccessResult(mFacadeBuilder.uploadProductMedia(productId, photos, coverPhoto), "Medias uploaded successfully.");
        } catch (Exception e) {
            return createErrorResult(e);
        }
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'WAITER')")
    @GetMapping("getAllProducts/{page}/{size}")
    public ResponseModel getAllProducts(@PathVariable int page, @PathVariable int size, @RequestHeader(value = "LanguageName") String languageName) {
        try {
            return createSuccessResult(mFacadeBuilder.getAllProducts(PageRequest.of(page, size), languageName), "Products retrieved successfully.");
        } catch (Exception e) {
            return createErrorResult(e);
        }
    }

    @PreAuthorize("hasAnyRole('MANAGER', 'WAITER')")
    @GetMapping("getProductDetails/{productId}")
    public ResponseModel geProductDetailProducts(@PathVariable Long productId, @RequestHeader(value = "LanguageName") String languageName) {
        try {
            return createSuccessResult(mFacadeBuilder.getProductDetails(productId, languageName), "Product details retrieved successfully.");
        } catch (Exception e) {
            return createErrorResult(e);
        }
    }

}