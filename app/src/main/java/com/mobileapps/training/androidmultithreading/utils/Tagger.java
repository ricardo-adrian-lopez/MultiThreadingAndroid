package com.mobileapps.training.androidmultithreading.utils;

public class Tagger {

    public static String get(Object object){
        return object.getClass().getSimpleName() + "_TAG";
    }
}
