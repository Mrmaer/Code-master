package com.bean;

public class Bean {
    private String name;
    private String address;
    public Bean(){};
    public Bean(String name,String address){
        this.name = name;
        this.address = address;
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
