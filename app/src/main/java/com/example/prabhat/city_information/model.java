package com.example.prabhat.city_information;

/**
 * Created by prabhat on 14/9/17.
 */

public class model {
    String image;
    String text;
    String lang;
    String popu;
    String  Area;

    public void setlang(String s)
    {
        this.lang=s;
    }
    public String getlang()
    {
        return this.lang;
    }
    public void setArea(String s){this.Area=s;}
    public String getArea(){return  this.Area;}
    public void setpopu(String s)
    {
        this.popu=s;
    }
    public String getpopu()
    {
        return this.popu;
    }
    public void setimage(String s)
    {
        this.image=s;
    }
    public String getimage()
    {
        return this.image;
    }
    public void setText(String s)
    {
        this.text=s;
    }
    public String getText()
    {
        return this.text;
    }
}
