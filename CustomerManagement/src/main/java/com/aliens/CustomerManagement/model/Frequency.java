package com.aliens.CustomerManagement.model;

import lombok.Data;


public class Frequency {
    private Object value;
    private long count;

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
