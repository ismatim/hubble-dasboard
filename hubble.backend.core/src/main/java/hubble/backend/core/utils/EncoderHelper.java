package hubble.backend.core.utils;

import java.util.Base64;

public final class EncoderHelper {
	
	public static String encodeToBase64(String userName, String password) {
        String formatToEncode=String.format("%s:%s",userName,password);
        byte[] encodedBytes = Base64.getEncoder().encode(formatToEncode.getBytes());
        return new String(encodedBytes);
    }
	
	public static String decodeFromBase64(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString.getBytes());
        return new String(decodedBytes);
    }
}
