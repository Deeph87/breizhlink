package services;

import java.security.MessageDigest;

public class Password {

    private String password;

    public Password(String password){
        this.password = password;
    }

    public String encrypt() {
        String passwordEncrypted = this.password;
//        String passwordEncrypted = "";
//        try {
//            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
//            messageDigest.update(this.password.getBytes());
//            passwordEncrypted = new String(messageDigest.digest());
//        } catch (Exception exception) {}



        return passwordEncrypted;
    }
}
