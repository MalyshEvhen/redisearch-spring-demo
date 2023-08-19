package com.example.constraints;

public class UserConstraints {
    private UserConstraints() {
        throw new IllegalStateException("UserConstraints could not be instantiated!");
    }

    /**
     * Email validation regular expression.
     */
    public static final String EMAIL_REGEXP = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$";

    /**
     * Minimal length of user`s firstname and lastname.
     */
    public static final int MIN_NAME_LENGTH = 3;

    /**
     * Maximum length of user`s firstname  and lastname.
     */
    public static final int MAX_NAME_LENGTH = 30;
}