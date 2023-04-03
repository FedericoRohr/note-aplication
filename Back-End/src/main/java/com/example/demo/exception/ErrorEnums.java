package com.example.demo.exception;

public enum ErrorEnums {
    USERNOTENOTFOUND("the user doesn´t have that note"),
    NOTEIDNOTFOUND("Incorrect note id"),
    CATEGORYNOTFOUND("Category doesn´t exist"),
	ARCHIVE("Alredy archived"),
	UNARCHIVE("Alredy unarchived"),
	USERNAMENOTFOUND("nonexistent user"),
	USERNAMEEXIST("User with that userName already exists"),
	WRONGUSERPASSWORD("user or password wrong"),
	INCORRECTIDPARA("the path user id is incorrect");

    private String message;

    private ErrorEnums(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
