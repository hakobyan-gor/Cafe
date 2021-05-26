package com.cafe.convert;

import com.cafe.enums.ProductStatus;
import com.cafe.model.dto.product.ProductCreatingDto;
import com.cafe.model.dto.product.ProductPreviewDto;
import com.cafe.model.dto.product.ProductPreviewDtoList;
import com.cafe.model.entity.product.Product;
import com.cafe.model.entity.product.ProductMedia;
import com.cafe.model.entity.product.ProductTranslate;
import com.google.common.base.Strings;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.cafe.utils.Configuration.productPath;

public class ProductConverter {

    public static Product convertToEntity(ProductCreatingDto productCreatingDto) {
        return Product
                .builder()
                .productStatus(ProductStatus.valueOf(productCreatingDto.getProductStatus()).orElseThrow())
                .productUniqueCode(productCreatingDto.getProductUniqueCode())
                .description(productCreatingDto.getDescription())
                .ingredients(productCreatingDto.getIngredients())
                .calories(productCreatingDto.getCalories())
                .price(productCreatingDto.getPrice())
                .name(productCreatingDto.getName())
                .productTranslateList(convertToTranslateEntityList(productCreatingDto.getTranslateList()))
                .build();
    }

    private static List<ProductTranslate> convertToTranslateEntityList(List<ProductCreatingDto.ProductTranslateCreatingDto> translateList) {
        return translateList.stream().map(ProductConverter::convertToTranslateEntity).collect(Collectors.toList());
    }

    private static ProductTranslate convertToTranslateEntity(ProductCreatingDto.ProductTranslateCreatingDto productTranslateCreatingDto) {
        return ProductTranslate
                .builder()
                .description(productTranslateCreatingDto.getDescription())
                .languageName(productTranslateCreatingDto.getLanguageName())
                .name(productTranslateCreatingDto.getName())
                .build();
    }

    public static Page<ProductPreviewDtoList> convertToPreviewDtoPage(Page<Product> productPage, String languageNName) {
        return productPage.map(product -> convertToPreviewDtoListItem(product, languageNName));
    }

    private static ProductPreviewDtoList convertToPreviewDtoListItem(Product product, String languageNName) {
        Optional<ProductTranslate> optionalProductTranslate = product.getProductTranslateList().stream()
                .filter(productTranslate -> productTranslate.getLanguageName().equals(languageNName)).findFirst();
        Optional<ProductMedia> optionalProductMedia = product.getProductMediaList().stream().filter(ProductMedia::isCoverImage).findFirst();
        return ProductPreviewDtoList
                .builder()
                .name(optionalProductTranslate.isEmpty() || Strings.isNullOrEmpty(optionalProductTranslate.get().getName())
                        ? product.getName() : optionalProductTranslate.get().getName())
                .id(product.getId())
                .price(product.getPrice())
                .coverPhotoUrl(optionalProductMedia.map(productMedia -> String.format("%s%s", productPath, productMedia.getMediaUrl())).orElse(null))
                .build();
    }

    public static ProductPreviewDto convertToPreviewDto(Product product, String languageNName) {
        Optional<ProductTranslate> optionalProductTranslate = product.getProductTranslateList().stream()
                .filter(productTranslate -> productTranslate.getLanguageName().equals(languageNName)).findFirst();
        return ProductPreviewDto
                .builder()
                .name(optionalProductTranslate.isEmpty() || Strings.isNullOrEmpty(optionalProductTranslate.get().getName())
                        ? product.getName() : optionalProductTranslate.get().getName())
                .description(optionalProductTranslate.isEmpty() || Strings.isNullOrEmpty(optionalProductTranslate.get().getDescription())
                        ? product.getDescription() : optionalProductTranslate.get().getDescription())
                .ingredients(product.getIngredients())
                .calories(product.getCalories())
                .price(product.getPrice())
                .id(product.getId())
                .mediaPreviewList(convertToMediaPreviewDtoList(product.getProductMediaList()))
                .build();
    }

    private static List<ProductPreviewDto.ProductMediaPreviewDto> convertToMediaPreviewDtoList(List<ProductMedia> productMediaList) {
        return productMediaList.stream().map(ProductConverter::convertToMediaPreviewDto).collect(Collectors.toList());
    }

    private static ProductPreviewDto.ProductMediaPreviewDto convertToMediaPreviewDto(ProductMedia productMedia) {
        return ProductPreviewDto.ProductMediaPreviewDto
                .builder()
                .isCoverPhoto(productMedia.isCoverImage())
                .mediaUrl(productMedia.getMediaUrl() == null ? null : String.format("%s%s", productPath, productMedia.getMediaUrl()))
                .build();
    }

}