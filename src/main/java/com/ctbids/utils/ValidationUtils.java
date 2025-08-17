package com.ctbids.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class ValidationUtils {
    private static final Set<String> BANNED_WORDS = new HashSet<>(Arrays.asList(
        "cancelled", "canceled", "failed", "outbid", "closed",
        "ended", "stop", "auctioneer", "ctbids", "bid", "bidding"
    ));

    // Pattern to match any character that is not a letter, number, quotation mark, or Spanish character
    private static final Pattern SPECIAL_CHARS = Pattern.compile("[^a-zA-ZáéíóúüñÁÉÍÓÚÜÑ0-9'\"]");

    public static boolean isValidUsername(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }

        // Convert to lowercase for case-insensitive comparison
        String lowerUsername = username.toLowerCase();

        // Check for special characters
        if (SPECIAL_CHARS.matcher(username).find()) {
            return false;
        }

        // Check for banned words
        for (String bannedWord : BANNED_WORDS) {
            if (lowerUsername.contains(bannedWord.toLowerCase())) {
                return false;
            }
        }

        // Check if username contains only English and Spanish characters
        // This regex allows English letters (a-z, A-Z), Spanish letters (á-ú, Á-Ú, ñ, Ñ),
        // numbers (0-9), and quotation marks
        return username.matches("^[a-zA-ZáéíóúüñÁÉÍÓÚÜÑ0-9'\"]*$") && !SPECIAL_CHARS.matcher(username).find();
    }

    public static String getValidationError(String username) {
        if (username == null || username.trim().isEmpty()) {
            return "Username cannot be empty";
        }

        if (SPECIAL_CHARS.matcher(username).find()) {
            return "Username can only contain letters, numbers, and quotation marks";
        }

        String lowerUsername = username.toLowerCase();
        for (String bannedWord : BANNED_WORDS) {
            if (lowerUsername.contains(bannedWord.toLowerCase())) {
                return "Username cannot contain the word: " + bannedWord;
            }
        }

        if (!username.matches("^[a-zA-ZáéíóúüñÁÉÍÓÚÜÑ0-9'\"]*$")) {
            return "Username can only contain English and Spanish characters";
        }

        return null; // No validation error
    }
}
