package com.matthewjohnson42.memex.service.logic.util;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testRandomIdLength() {
        String randomId = StringUtils.randomId();
        assertNotNull(randomId);
        assertEquals(24, randomId.length());
    }

    @Test
    void testRandomIdConsistentLength() {
        for (int i = 0; i < 10; i++) {
            String randomId = StringUtils.randomId();
            assertEquals(24, randomId.length());
        }
    }

    @Test
    void testRandomIdIsAlphaNumeric() {
        String randomId = StringUtils.randomId();
        // Check that all characters are alphanumeric (0-9 or a-z)
        for (char c : randomId.toCharArray()) {
            assertTrue((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z'), 
                "Character '" + c + "' is not alphanumeric");
        }
    }

    @Test
    void testRandomIdUniqueness() {
        Set<String> ids = new HashSet<>();
        int numTests = 100;
        
        for (int i = 0; i < numTests; i++) {
            String randomId = StringUtils.randomId();
            ids.add(randomId);
        }
        
        // Should generate unique IDs (with high probability)
        assertTrue(ids.size() > numTests * 0.95, "Should generate mostly unique IDs");
    }

    @Test
    void testRandomIdContainsDigits() {
        boolean foundDigit = false;
        String randomId = StringUtils.randomId();
        
        for (char c : randomId.toCharArray()) {
            if (c >= '0' && c <= '9') {
                foundDigit = true;
                break;
            }
        }
        
        // This may fail occasionally due to randomness, but very unlikely
        // Let's test multiple times to increase chances
        if (!foundDigit) {
            for (int i = 0; i < 10; i++) {
                randomId = StringUtils.randomId();
                for (char c : randomId.toCharArray()) {
                    if (c >= '0' && c <= '9') {
                        foundDigit = true;
                        break;
                    }
                }
                if (foundDigit) break;
            }
        }
        
        assertTrue(foundDigit, "Should contain at least one digit in multiple attempts");
    }

    @Test
    void testRandomIdContainsLetters() {
        boolean foundLetter = false;
        String randomId = StringUtils.randomId();
        
        for (char c : randomId.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                foundLetter = true;
                break;
            }
        }
        
        // This may fail occasionally due to randomness, but very unlikely
        // Let's test multiple times to increase chances
        if (!foundLetter) {
            for (int i = 0; i < 10; i++) {
                randomId = StringUtils.randomId();
                for (char c : randomId.toCharArray()) {
                    if (c >= 'a' && c <= 'z') {
                        foundLetter = true;
                        break;
                    }
                }
                if (foundLetter) break;
            }
        }
        
        assertTrue(foundLetter, "Should contain at least one letter in multiple attempts");
    }

    @Test
    void testRandomIdDoesNotContainUpperCase() {
        String randomId = StringUtils.randomId();
        for (char c : randomId.toCharArray()) {
            assertFalse(c >= 'A' && c <= 'Z', "Should not contain uppercase letters");
        }
    }

    @Test
    void testRandomIdBranchCoverage() {
        // Test to ensure both branches (numbers and letters) are covered
        Set<Character> characters = new HashSet<>();
        
        // Generate multiple IDs to ensure we hit both branches
        for (int i = 0; i < 100; i++) {
            String randomId = StringUtils.randomId();
            for (char c : randomId.toCharArray()) {
                characters.add(c);
            }
        }
        
        // Should have both numbers and letters
        boolean hasNumbers = characters.stream().anyMatch(c -> c >= '0' && c <= '9');
        boolean hasLetters = characters.stream().anyMatch(c -> c >= 'a' && c <= 'z');
        
        assertTrue(hasNumbers, "Should generate numbers");
        assertTrue(hasLetters, "Should generate letters");
    }
}