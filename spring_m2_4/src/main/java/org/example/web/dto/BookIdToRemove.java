package org.example.web.dto;


import javax.validation.constraints.NotNull;

public class BookIdToRemove {

    @NotNull
    private Object object;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
