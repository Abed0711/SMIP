package com.example.abed.smit;

public class FindDoctors {

    public String name ,gender,career,phone_num,degree,age,cource,specialist;



    public FindDoctors()
    {

    }
    public FindDoctors(String name,String gender,String career,String phone_num,String degree
    ,String age,String cource,String specialist) {
        this.name = name;
        this.gender=gender;
        this.career=career;
        this.phone_num=phone_num;
        this.degree=degree;
        this.age=age;
        this.cource=cource;
        this.specialist=specialist;

    }

    public String getSpecialist() {
        return specialist;
    }

    public String getCource() {
        return cource;
    }

    public String getAge() {
        return age;
    }

    public String getDegree() {
        return degree;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public String getCareer() {
        return career;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

}