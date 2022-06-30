package com.example.umstest;

import java.util.List;
import java.util.Random;

public class TestUtil {
    public  static <T> int getRandomObject(List<T> listObject){
        Random rand = new Random();
        int length = listObject.size();
        return rand.nextInt(length);
    }
}
