package com.example.demo;

public interface ApiClientInterface<T> {
    T execute(T t);
}
