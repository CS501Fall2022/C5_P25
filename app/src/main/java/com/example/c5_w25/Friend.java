package com.example.c5_w25;

public class Friend {
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    public Friend( int newId, String newFirst, String newLast, String newEmail ) {
        setId( newId );
        setFirstName( newFirst );
        setLastName( newLast );
        setEmail(newEmail);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
