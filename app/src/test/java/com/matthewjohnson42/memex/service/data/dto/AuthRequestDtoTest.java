package com.matthewjohnson42.memex.service.data.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthRequestDtoTest {

    @Test
    void testDefaultConstructor() {
        AuthRequestDto dto = new AuthRequestDto();
        assertNotNull(dto);
        assertNull(dto.getUsername());
        assertNull(dto.getPassword());
    }

    @Test
    void testParameterizedConstructor() {
        String username = "testuser";
        String password = "testpass";
        
        AuthRequestDto dto = new AuthRequestDto(username, password);
        
        assertEquals(username, dto.getUsername());
        assertEquals(password, dto.getPassword());
    }

    @Test
    void testParameterizedConstructorWithNulls() {
        AuthRequestDto dto = new AuthRequestDto(null, null);
        
        assertNull(dto.getUsername());
        assertNull(dto.getPassword());
    }

    @Test
    void testSetUsername() {
        AuthRequestDto dto = new AuthRequestDto();
        String username = "testuser";
        
        AuthRequestDto result = dto.setUsername(username);
        
        assertSame(dto, result); // Should return this for fluent API
        assertEquals(username, dto.getUsername());
    }

    @Test
    void testSetUsernameWithNull() {
        AuthRequestDto dto = new AuthRequestDto();
        
        AuthRequestDto result = dto.setUsername(null);
        
        assertSame(dto, result);
        assertNull(dto.getUsername());
    }

    @Test
    void testSetPassword() {
        AuthRequestDto dto = new AuthRequestDto();
        String password = "testpass";
        
        AuthRequestDto result = dto.setPassword(password);
        
        assertSame(dto, result); // Should return this for fluent API
        assertEquals(password, dto.getPassword());
    }

    @Test
    void testSetPasswordWithNull() {
        AuthRequestDto dto = new AuthRequestDto();
        
        AuthRequestDto result = dto.setPassword(null);
        
        assertSame(dto, result);
        assertNull(dto.getPassword());
    }

    @Test
    void testGetUsername() {
        AuthRequestDto dto = new AuthRequestDto();
        String username = "testuser";
        dto.setUsername(username);
        
        assertEquals(username, dto.getUsername());
    }

    @Test
    void testGetPassword() {
        AuthRequestDto dto = new AuthRequestDto();
        String password = "testpass";
        dto.setPassword(password);
        
        assertEquals(password, dto.getPassword());
    }
}