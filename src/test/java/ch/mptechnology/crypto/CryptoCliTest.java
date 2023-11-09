package ch.mptechnology.crypto;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CryptoCliTest {

    public static final String MESSAGE = "myMessage";
    public static final String SECRET = "mySecret";

    @Test
    void encryptAndDecrypt() {
        TextEncryptor textEncryptor = CryptoCli.createTextEncryptor(SECRET, CryptoCli.HARD_CODED_SALT);
        String encrypted = textEncryptor.encrypt(MESSAGE);
        assertNotEquals(MESSAGE, encrypted);
        assertNotEquals(SECRET, encrypted);
        assertEquals(64, encrypted.length());

        String decrypted = textEncryptor.decrypt(encrypted);
        assertEquals(MESSAGE, decrypted);
    }

}