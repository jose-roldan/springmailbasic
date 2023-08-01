package ar.com.egomusic.basiccrudmaven.model;

import java.io.Serializable;

public class Friend implements Serializable {

    private String name = null;
    private String phone = null;
    private String address = null;
    private String comments = null;
    private int age = 0;
    private int friendshipLevel = 0;

    public Friend() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFriendshipLevel() {
        return friendshipLevel;
    }

    public void setFriendshipLevel(int friendshipLevel) {
        this.friendshipLevel = friendshipLevel;
    }
}
