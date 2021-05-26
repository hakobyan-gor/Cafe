package com.cafe.utils.constants;

public class AppConstants {

    public static String USERNAME_OR_PASSWORD_IS_INCORRECT(String lng) {
        return switch (lng) {
            case "en" -> "Username or password is incorrect";
            case "ru" -> "Неверное имя пользователя или пароль";
            case "hy" -> "Մուտքանունը կամ գաղտնաբառը սխալ է";
            default -> "Username or password is incorrect";
        };
    }

}