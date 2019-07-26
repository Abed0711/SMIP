package com.example.abed.smit;



public class User {
    public String name, email,blood,district,area,mobile,age,donateDate,gender;

    public User(){

    }

    public User(String name, String email,String district,String blood,String area ,
                String mobile,String age,String donateDate,String gender) {
        this.name = name;
        this.email = email;
        this.blood = blood;
        this.district=district;
        this.area=area;
        this.mobile=mobile;
        this.age=age;
        this.donateDate=donateDate;
        this.gender=gender;
    }
}
