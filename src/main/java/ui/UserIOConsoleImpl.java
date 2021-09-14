/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.Scanner;

/**
 *
 * @author Austin Graham
 * Date: 09/14/2021
 * Purpose: implementation of reading various input types
 */
public class UserIOConsoleImpl implements UserIO{
    Scanner inRead = new Scanner(System.in);
    
    @Override
    public void print(String message){
        System.out.println(message);
    }
    
    @Override
    public String readString(String prompt){
        print(prompt);
        String userString = inRead.nextLine();
        return userString;
    }

    @Override
    public int readInt(String prompt){
        print(prompt);
        int number = Integer.parseInt(inRead.nextLine());
        return number;
    }

    @Override
    public int readInt(String prompt, int min, int max){
        print(prompt);
        int number = Integer.parseInt(inRead.nextLine());
        while(number < min || number > max){
            System.out.println("Invalid entry. Out of Bounds.");
            print(prompt);
            number = Integer.parseInt(inRead.nextLine());
        }
        return number;  
    }

    @Override
    public double readDouble(String prompt){
        print(prompt);
        double number = Double.parseDouble(inRead.nextLine());
        return number;
    }

    @Override
    public double readDouble(String prompt, double min, double max){
        print(prompt);
        double number = Double.parseDouble(inRead.nextLine());
        while(number < min || number > max){
            System.out.println("Invalid entry. Out of Bounds.");
            print(prompt);
            number = Double.parseDouble(inRead.nextLine());
        }
        return number; 
    }

    @Override
    public float readFloat(String prompt){
        print(prompt);
        float number = Float.parseFloat(inRead.nextLine());
        return number;
    }

    @Override
    public float readFloat(String prompt, float min, float max){
        print(prompt);
        float number = Float.parseFloat(inRead.nextLine());
        while(number < min || number > max){
            System.out.println("Invalid entry. Out of Bounds.");
            print(prompt);
            number = Float.parseFloat(inRead.nextLine());
        }
        return number; 
    }

    @Override
    public long readLong(String prompt){
        print(prompt);
        long number = Long.parseLong(inRead.nextLine());
        return number;
    }

    @Override
    public long readLong(String prompt, long min, long max){
        print(prompt);
        long number = Long.parseLong(inRead.nextLine());
        while(number < min || number > max){
            System.out.println("Invalid entry. Out of Bounds.");
            print(prompt);
            number = Long.parseLong(inRead.nextLine());
        }
        return number; 
    }
}
