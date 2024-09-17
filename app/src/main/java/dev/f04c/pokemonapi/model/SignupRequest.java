package dev.f04c.pokemonapi.model;

public class SignupRequest {

    private String username;
    private String password;
    private String name;
    private int age;
    private String address;

    public SignupRequest(String username, String password, String name, int age, String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    // Getters and setters for each field
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}