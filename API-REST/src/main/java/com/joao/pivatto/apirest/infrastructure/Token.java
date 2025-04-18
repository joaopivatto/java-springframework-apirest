package com.joao.pivatto.apirest.infrastructure;

public class Token {

    private String value;

    public Token(){
        this.value = "";
    }

    public Token(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
