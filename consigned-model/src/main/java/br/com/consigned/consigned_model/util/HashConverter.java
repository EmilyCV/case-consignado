package br.com.consigned.consigned_model.util;

import jakarta.xml.bind.DatatypeConverter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashConverter {
    public static String getHash(String document) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(document.getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash: " + e.getMessage());
        }
    }
}
