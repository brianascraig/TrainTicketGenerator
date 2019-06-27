package com.teamTwo;

public class Passenger implements Comparable{
    private String name;
    private int age;
    private char gender;

    public Passenger(String name, int age, char gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;

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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }


    @Override
    public int compareTo(Object o) {
//        if(this.getName().equalsIgnoreCase(((Passenger)o).getName()) &&
//                this.getAge() == ((Passenger)o).getAge() &&
//                this.getGender() == ((Passenger)o).getGender())
//            return 0;
//        else
        return 1;
    }
}
