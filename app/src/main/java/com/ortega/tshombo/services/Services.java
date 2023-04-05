package com.ortega.tshombo.services;

import java.util.concurrent.atomic.AtomicBoolean;

public interface Services<T> {

    public void save(T data);
    public void get(String id);
    public void update(T data, String id);
    public void delete(String id);

    public void getAll();

}
