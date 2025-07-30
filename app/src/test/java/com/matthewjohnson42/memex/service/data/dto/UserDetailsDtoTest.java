package com.matthewjohnson42.memex.service.data.dto;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class UserDetailsDtoTest {

    @Test
    void testDefaultConstructor() {
        UserDetailsDto dto = new UserDetailsDto();
        assertNotNull(dto);
        assertNull(dto.getUsername());
        assertNull(dto.getPassword());
        assertNotNull(dto.getAuthorities());
        assertTrue(dto.getAuthorities().isEmpty());
    }

    @Test
    void testCopyConstructor() {
        UserDetailsDto original = new UserDetailsDto();
        original.setUsername("testuser")
                .setPassword("testpass");
        Set<String> authorities = new HashSet<>();
        authorities.add("ROLE_USER");
        original.setAuthorities(authorities);
        
        UserDetailsDto copy = new UserDetailsDto(original);
        
        assertEquals(original.getUsername(), copy.getUsername());
        assertEquals(original.getPassword(), copy.getPassword());
        assertEquals(original.getAuthorities(), copy.getAuthorities());
    }

    @Test
    void testParameterizedConstructorWithAuthorities() {
        String username = "testuser";
        String password = "testpass";
        Set<String> authorities = new HashSet<>();
        authorities.add("ROLE_USER");
        authorities.add("ROLE_ADMIN");
        
        UserDetailsDto dto = new UserDetailsDto(username, password, authorities);
        
        assertEquals(username, dto.getUsername());
        assertEquals(password, dto.getPassword());
        assertEquals(authorities, dto.getAuthorities());
    }

    @Test
    void testParameterizedConstructorWithNullAuthorities() {
        String username = "testuser";
        String password = "testpass";
        
        UserDetailsDto dto = new UserDetailsDto(username, password, null);
        
        assertEquals(username, dto.getUsername());
        assertEquals(password, dto.getPassword());
        assertNotNull(dto.getAuthorities());
        assertTrue(dto.getAuthorities().isEmpty());
    }

    @Test
    void testGetId() {
        UserDetailsDto dto = new UserDetailsDto();
        String username = "testuser";
        dto.setUsername(username);
        
        assertEquals(username, dto.getId());
    }

    @Test
    void testSetId() {
        UserDetailsDto dto = new UserDetailsDto();
        String username = "testuser";
        
        UserDetailsDto result = dto.setId(username);
        
        assertSame(dto, result);
        assertEquals(username, dto.getUsername());
        assertEquals(username, dto.getId());
    }

    @Test
    void testSetUsername() {
        UserDetailsDto dto = new UserDetailsDto();
        String username = "testuser";
        
        UserDetailsDto result = dto.setUsername(username);
        
        assertSame(dto, result);
        assertEquals(username, dto.getUsername());
    }

    @Test
    void testSetUsernameWithNull() {
        UserDetailsDto dto = new UserDetailsDto();
        
        UserDetailsDto result = dto.setUsername(null);
        
        assertSame(dto, result);
        assertNull(dto.getUsername());
    }

    @Test
    void testSetPassword() {
        UserDetailsDto dto = new UserDetailsDto();
        String password = "testpass";
        
        UserDetailsDto result = dto.setPassword(password);
        
        assertSame(dto, result);
        assertEquals(password, dto.getPassword());
    }

    @Test
    void testSetPasswordWithNull() {
        UserDetailsDto dto = new UserDetailsDto();
        
        UserDetailsDto result = dto.setPassword(null);
        
        assertSame(dto, result);
        assertNull(dto.getPassword());
    }

    @Test
    void testSetAuthorities() {
        UserDetailsDto dto = new UserDetailsDto();
        Set<String> authorities = new HashSet<>();
        authorities.add("ROLE_USER");
        
        UserDetailsDto result = dto.setAuthorities(authorities);
        
        assertSame(dto, result);
        assertEquals(authorities, dto.getAuthorities());
    }

    @Test
    void testSetAuthoritiesWithNull() {
        UserDetailsDto dto = new UserDetailsDto();
        
        UserDetailsDto result = dto.setAuthorities(null);
        
        assertSame(dto, result);
        assertNull(dto.getAuthorities());
    }

    @Test
    void testGetters() {
        UserDetailsDto dto = new UserDetailsDto();
        String username = "testuser";
        String password = "testpass";
        Set<String> authorities = new HashSet<>();
        authorities.add("ROLE_USER");
        
        dto.setUsername(username)
           .setPassword(password)
           .setAuthorities(authorities);
        
        assertEquals(username, dto.getUsername());
        assertEquals(password, dto.getPassword());
        assertEquals(authorities, dto.getAuthorities());
    }
}