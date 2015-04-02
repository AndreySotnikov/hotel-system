package project.service.login;

import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by andrey on 24.03.15.
 */
@Service
public class EncryptPassword {
    public String encrypt(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] bytesOfMessage = password.getBytes("UTF-8");
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] thedigest = md.digest(bytesOfMessage);
        return new String(thedigest);
    }
}
