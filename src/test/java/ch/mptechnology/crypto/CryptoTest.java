package ch.mptechnology.crypto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CryptoTest {

    public static final String MESSAGE = "myMessage";
    public static final String SECRET = "mySecret";

    @Test
    void encryptAndDecrypt() {
        Crypto crypto = new Crypto();
        String encrypted = crypto.encrypt(MESSAGE, SECRET, Crypto.HARD_CODED_SALT);
        assertNotEquals(MESSAGE, encrypted);
        assertNotEquals(SECRET, encrypted);
        assertEquals(64, encrypted.length());

        String decrypted = crypto.decrypt(encrypted, SECRET, Crypto.HARD_CODED_SALT);
        assertEquals(MESSAGE, decrypted);
    }

}