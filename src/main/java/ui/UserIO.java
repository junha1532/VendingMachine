/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 *
 * @author Austin Graham
 * Date 09/14/2021
 * Purpose: interface for reading various input types
 */
public interface UserIO {
    void print(String msg);
    
    String readString(String prompt);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);
    
    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);
    
}
