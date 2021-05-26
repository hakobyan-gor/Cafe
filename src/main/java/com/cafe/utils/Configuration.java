package com.cafe.utils;

import org.springframework.beans.factory.annotation.Value;

@org.springframework.context.annotation.Configuration
public class Configuration {

    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String SIGNING_KEY = "simple_cafe_manager_assignment";
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 365 * 24 * 60 * 60;
    public static final String AUTHORITIES_KEY = "scopes";

    public static String BASE_PATH;

    public static String PRODUCT_IMAGE_DIR_IN_PROJECT;

    public Configuration(@Value("${common.base-path}") String basePath) {
        BASE_PATH = basePath;

        productPath = BASE_PATH + "/api/product/getImage/";

        PRODUCT_IMAGE_DIR_IN_PROJECT = "C:\\Sites\\Cafe\\images\\product\\";

    }

    public static String productPath;

}
