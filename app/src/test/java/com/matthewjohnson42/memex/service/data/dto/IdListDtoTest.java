package com.matthewjohnson42.memex.service.data.dto;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class IdListDtoTest {

    @Test
    void testDefaultConstructor() {
        IdListDto dto = new IdListDto();
        assertNotNull(dto);
        assertNotNull(dto.getIds());
        assertTrue(dto.getIds().isEmpty());
    }

    @Test
    void testParameterizedConstructor() {
        List<String> ids = Arrays.asList("id1", "id2", "id3");
        IdListDto dto = new IdListDto(ids);
        
        assertEquals(ids, dto.getIds());
        assertEquals(3, dto.getIds().size());
    }

    @Test
    void testParameterizedConstructorWithNull() {
        IdListDto dto = new IdListDto(null);
        
        assertNull(dto.getIds());
    }

    @Test
    void testParameterizedConstructorWithEmptyList() {
        List<String> emptyIds = new ArrayList<>();
        IdListDto dto = new IdListDto(emptyIds);
        
        assertEquals(emptyIds, dto.getIds());
        assertTrue(dto.getIds().isEmpty());
    }

    @Test
    void testSetIds() {
        IdListDto dto = new IdListDto();
        List<String> ids = Arrays.asList("id1", "id2");
        
        IdListDto result = dto.setIds(ids);
        
        assertSame(dto, result); // Should return this for fluent API
        assertEquals(ids, dto.getIds());
    }

    @Test
    void testSetIdsWithNull() {
        IdListDto dto = new IdListDto();
        
        IdListDto result = dto.setIds(null);
        
        assertSame(dto, result);
        assertNull(dto.getIds());
    }

    @Test
    void testGetIds() {
        IdListDto dto = new IdListDto();
        List<String> ids = Arrays.asList("id1", "id2");
        dto.setIds(ids);
        
        assertEquals(ids, dto.getIds());
    }

    @Test
    void testAddId() {
        IdListDto dto = new IdListDto();
        String id = "newId";
        
        IdListDto result = dto.addId(id);
        
        assertSame(dto, result); // Should return this for fluent API
        assertEquals(1, dto.getIds().size());
        assertTrue(dto.getIds().contains(id));
    }

    @Test
    void testAddIdToExistingList() {
        List<String> initialIds = new ArrayList<>(Arrays.asList("id1", "id2"));
        IdListDto dto = new IdListDto(initialIds);
        String newId = "id3";
        
        dto.addId(newId);
        
        assertEquals(3, dto.getIds().size());
        assertTrue(dto.getIds().contains(newId));
        assertTrue(dto.getIds().contains("id1"));
        assertTrue(dto.getIds().contains("id2"));
    }

    @Test
    void testAddNullId() {
        IdListDto dto = new IdListDto();
        
        IdListDto result = dto.addId(null);
        
        assertSame(dto, result);
        assertEquals(1, dto.getIds().size());
        assertTrue(dto.getIds().contains(null));
    }
}