package com.matthewjohnson42.memex.service.data.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthResponseDtoTest {

    @Test
    void testDefaultConstructor() {
        AuthResponseDto dto = new AuthResponseDto();
        assertNotNull(dto);
        assertNull(dto.getToken());
    }

    @Test
    void testSetToken() {
        AuthResponseDto dto = new AuthResponseDto();
        String token = "test-token";
        
        AuthResponseDto result = dto.setToken(token);
        
        assertSame(dto, result); // Should return this for fluent API
        assertEquals(token, dto.getToken());
    }

    @Test
    void testGetToken() {
        AuthResponseDto dto = new AuthResponseDto();
        String token = "test-token";
        
        dto.setToken(token);
        assertEquals(token, dto.getToken());
    }

    @Test
    void testSetTokenWithNull() {
        AuthResponseDto dto = new AuthResponseDto();
        
        AuthResponseDto result = dto.setToken(null);
        
        assertSame(dto, result);
        assertNull(dto.getToken());
    }
}