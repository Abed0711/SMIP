package com.example.abed.smit;

public class FindMedicine {

    public String name,company,each,mfgdate,expdate,gram;

    public FindMedicine()
    {

    }

    public FindMedicine(String name, String company, String each, String mfgdate, String expdate, String gram) {
        this.name = name;
        this.company = company;
        this.each = each;
        this.mfgdate = mfgdate;
        this.expdate = expdate;
        this.gram = gram;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getEach() {
        return each;
    }

    public String getMfgdate() {
        return mfgdate;
    }

    public String getExpdate() {
        return expdate;
    }

    public String getGram() {
        return gram;
    }
}