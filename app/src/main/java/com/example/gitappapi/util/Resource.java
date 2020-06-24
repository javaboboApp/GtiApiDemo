package com.example.gitappapi.util;

import static com.example.gitappapi.util.Resource.Status.*;

public  class Resource<T> {



    public   enum Status {
        SUCCESS, ERROR, LOADING,EMPTY
    }

    public Status status;
    public Event<T> data;
    public String message;

    private Resource(Status status, Event<T> data   , String message) {
        this.status = status;
        this.data = data  ;
        this.message = message;
    }

    public static <T> Resource<T> success(T data) {
        return new Resource<>(SUCCESS, new Event<T>(data), null);
    }

    public static <T> Resource<T> error(String msg, T data) {
        return new Resource<>(ERROR, new Event<T>(data) , msg);
    }

    public static <T> Resource<T> loading( T data) {
        return new Resource<>(LOADING, new Event<T>(data), null);
    }

    public static <T> Resource<T> empty( T data) {
        return new Resource<>(EMPTY, new Event<T>(data), null);
    }




}
