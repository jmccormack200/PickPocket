package com.example;

public @interface CustomAnnotation {
    String method () default "";
    String defaultValue() default "";
}
