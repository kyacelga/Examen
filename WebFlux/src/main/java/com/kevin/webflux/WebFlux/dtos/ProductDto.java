package com.rafael.webflux.webflux.dtos;

import com.rafael.webflux.WebFlux.documents.Product;

public class ProductDto {

    private String code;

    private String name,type,user;

    private boolean active;

    private int [] data;

    public ProductDto() {
    }

    public ProductDto(String code, String name, String type, String user,boolean active,int data) {
        this.code = code;
        this.name = name;
        this.type = type;
        this.user = user;
        this.active = active;
        this.data = data;
    }

    public ProductDto(Product product){

        this(
          product.getCode(),
          product.getName(),
          product.getType(),
          product.getUser(),
          product.getActive(),
          product.getData(),
        );
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(string user) {
        this.user = user;
    }
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
