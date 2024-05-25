package br.com.consigned.userverificationservice.util;

import jakarta.xml.bind.DatatypeConverter;

import java.security.MessageDigest;

public class HashConverter {
    public static String getHash(String doc) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(doc.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toLowerCase();
    }
}
