/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aplose.aploseframework.tool;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author oandrade
 */
public class UrlTools {
    public static String encodeValue(String value) {
        String result = value;
        try {
            result = URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        }catch(UnsupportedEncodingException uee){
            
        }
        return result;
    }
}
