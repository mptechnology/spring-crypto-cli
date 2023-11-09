package ch.mptechnology.crypto;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import org.springframework.util.StringUtils;

@ShellComponent
public class Crypto {

    /** The default salt as it was used by spring cloud cli to be backwards compatible. */
    public static final String HARD_CODED_SALT = "deadbeef";

    @ShellMethod("Encrypts a given message with the given key using AES 256 bit encryption")
    public String encrypt(
            String message,
            @ShellOption String key,
            @ShellOption(defaultValue = HARD_CODED_SALT) String salt) {
        validateArgument(message, "message");
        validateArgument(key, "key");
        validateArgument(salt, "salt");
        return createTextEncryptor(key, salt).encrypt(message);
    }

    @ShellMethod("Decrypts a given message with the given key using AES 256 bit encryption")
    public String decrypt(
            String message,
            @ShellOption String key,
            @ShellOption(defaultValue = HARD_CODED_SALT) String salt) {
        validateArgument(message, "message");
        validateArgument(key, "key");
        validateArgument(salt, "salt");
        return createTextEncryptor(key, salt).decrypt(message);
    }

    private static TextEncryptor createTextEncryptor(String key, String salt) {
        return Encryptors.text(key, salt);
    }

    private static void validateArgument(String message, String argumentName) {
        if (!StringUtils.hasText(message)) {
            System.err.println("Argument '" + argumentName + "' is missing!");
            System.exit(1);
        }
    }

}
