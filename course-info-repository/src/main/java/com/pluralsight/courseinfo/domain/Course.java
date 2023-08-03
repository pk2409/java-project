package com.pluralsight.courseinfo.domain;

public record Course(String id,String name,long length,String url) {
    public Course{
        filled(id);
        filled(name);
        filled(url);

    }

    //pass a string to check whether its null or blank
    private static void filled(String s){
        if(s==null || s.isBlank()){
            throw new IllegalArgumentException("no value present for the string");
        }
    }
}
