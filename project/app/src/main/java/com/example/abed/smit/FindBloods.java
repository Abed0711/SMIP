package com.example.abed.smit;

public class FindBloods {
    public String blood,name,area,district,age,mobile,gender,email,donateDate;

    public FindBloods(){

    }



    public FindBloods(String blood, String name, String area, String district, String age, String mobile
            , String gender, String email, String donateDate) {
        this.blood = blood;
        this.name = name;
        this.area=area;
        this.district=district;
        this.age=age;
        this.mobile=mobile;
        this.gender=gender;
        this.email=email;
        this.donateDate=donateDate;

    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAge() {
        return age;
    }

    public String getDistrict() {
        return district;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }
    public String getDonateDate() {
        return donateDate;
    }

}
