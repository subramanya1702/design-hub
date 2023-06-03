package com.design.urlshortener.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String BASE_62_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final int SHORT_URL_ID_SIZE = 6;
    public static final int BASE_62_CHARACTERS_SIZE = 62;
    public static final int MAX_TRIES = 4;
}
