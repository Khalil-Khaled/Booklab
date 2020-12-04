/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booklab.services;

import java.util.List;

/**
 *
 * @author user
 */
public interface Iservices<T> {
    public void add(T t);
    public void delete(T t);
    public void modify(T t);
    public T show(int id);
    public List<T> showAll();
}
