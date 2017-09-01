/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.custmanager;

import com.custmanager.images.ImagesDir;
import javax.swing.ImageIcon;

/**
 *
 * @author Inderjit
 */
public class ImagesDirTest {

    public static void main(String[] args) {
        try {
        ImageIcon image = ImagesDir.getImage("login.gif");
        System.out.println("image " + image.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
