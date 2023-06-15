package com.ortega.tshombo.services;


public interface Services<T> {

    void save(T data);
    void update(T data, String id);
    void delete(String id);


}
