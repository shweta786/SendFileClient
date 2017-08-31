/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hiber.sendfileclient;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 *
 * @author SHWETA
 */
public class SendFileClient {

    public static void main(String[] args) {
        String fileName = "C:/Users/SHWETA/Desktop/IP.png";  //can give any name along with the path
        File file = new File(fileName);
            
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream inputStream = new BufferedInputStream(fis);
            byte[] imageBytes = new byte[(int) file.length()];
            inputStream.read(imageBytes);
            upload(file.getName(), imageBytes);
            inputStream.close();
            System.out.println("File uploaded: "+ file.getName());
        } catch (IOException ex) {
            System.out.println("Error Occured: File not found");
        }
    }

    private static void upload(String fileName, byte[] imageBytes) {

        try {
            // Call Web Service Operation
            com.hiber.service.UploadFile_Service service = new com.hiber.service.UploadFile_Service();
            com.hiber.service.UploadFile port = service.getUploadFilePort();
            port.upload(fileName, imageBytes);
        } catch (Exception ex) {
            System.out.println("Error Occured "+ ex);
        }

    }

}
