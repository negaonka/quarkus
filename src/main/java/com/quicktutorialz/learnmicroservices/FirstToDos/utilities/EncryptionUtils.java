package com.quicktutorialz.learnmicroservices.FirstToDos.utilities;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EncryptionUtils {


    //attribute from jasypt library
    private Vertx vertx;


    /* constructor */
	/*
	 * public EncryptionUtils(){ vertx = Vertx.vertx();
	 * vertx.setPassword("mySecretEncriptionKeyBlaBla1234"); }
	 */


    private String encrypt(String text, String secretKey) {
        JsonObject jsonObject = new JsonObject().put("text", text).put("secretKey", secretKey);
        Buffer buffer = Buffer.buffer(jsonObject.encode());
        // Save the buffer to a file or use it as needed
        return "encryptedFile.txt";
    }

    private static String decrypt(Buffer encryptedBuffer, String secretKey) {
        // Read the buffer from a file or use it as needed
        JsonObject jsonObject = new JsonObject(encryptedBuffer.toString());
        String encryptedText = jsonObject.getString("text");
        // Decrypt the text using the secretKey
        return encryptedText;
    }



}
