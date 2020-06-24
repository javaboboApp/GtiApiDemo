package com.example.gitappapi.util;

public class Event<T> {
    private T content;
    private Boolean hasBennHandled = false;

    public Event(T content) {
        this.content = content;
    }

    public Boolean getHasBennHandled() {
        return hasBennHandled;
    }

    public T getContentIfNoHandled(){
        if(hasBennHandled) return null;
        this.hasBennHandled = true;
        return content;
    }

    public boolean hasBeenHandled(){
        return hasBennHandled;
    }

    public T peekContent(){
        return content;
    }


}
