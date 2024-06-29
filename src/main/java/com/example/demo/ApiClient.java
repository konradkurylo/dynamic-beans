package com.example.demo;


public class ApiClient<T> implements ApiClientInterface<T> {
    private Class<T> clazz;
    private String name;
    private String url;
    private String key;
    // standard getters, setters and constructors

    public ApiClient(Class<T> clazz, String name, String url, String key) {
        this.clazz = clazz;
        this.name = name;
        this.url = url;
        this.key = key;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getKey() {
        return key;
    }

    public String getConnectionProperties() {
        return "Connecting to " + name + " at " + url;     
    }

    @Override
    public T execute(T t) {
        System.out.println(t);
        return t;
    }
}