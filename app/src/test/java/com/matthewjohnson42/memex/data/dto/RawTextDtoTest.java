package com.matthewjohnson42.memex.data.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class RawTextDtoTest {

    @Test
    void testDefaultConstructor() {
        RawTextDto dto = new RawTextDto();
        assertNotNull(dto);
        assertNotNull(dto.getTags());
        assertTrue(dto.getTags().isEmpty());
        assertNull(dto.getId());
        assertNull(dto.getTitle());
        assertNull(dto.getText());
        assertNull(dto.getCreateDate());
        assertNull(dto.getUpdateDate());
    }

    @Test
    void testCopyConstructorWithNull() {
        RawTextDto dto = new RawTextDto(null);
        
        assertNotNull(dto);
        assertNotNull(dto.getTags()); // tags are initialized in the parent constructor
        assertTrue(dto.getTags().isEmpty());
        assertNull(dto.getId());
        assertNull(dto.getTitle());
        assertNull(dto.getText());
        assertNull(dto.getCreateDate());
        assertNull(dto.getUpdateDate());
    }

    @Test
    void testCopyConstructorWithValue() {
        RawTextDto original = new RawTextDto();
        original.setId("test-id");
        original.setTitle("Test Title");
        original.setText("Test Text");
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        original.setTags(tags);
        LocalDateTime now = LocalDateTime.now();
        original.setCreateDate(now);
        original.setUpdateDate(now);
        
        RawTextDto copy = new RawTextDto(original);
        
        assertEquals("test-id", copy.getId());
        assertEquals("Test Title", copy.getTitle());
        assertEquals("Test Text", copy.getText());
        assertEquals(tags, copy.getTags());
        assertEquals(now, copy.getCreateDate());
        assertEquals(now, copy.getUpdateDate());
    }

    @Test
    void testGetId() {
        RawTextDto dto = new RawTextDto();
        assertNull(dto.getId());
    }

    @Test
    void testSetId() {
        RawTextDto dto = new RawTextDto();
        String id = "test-id";
        
        RawTextDto result = dto.setId(id);
        
        assertSame(dto, result);
        assertEquals(id, dto.getId());
    }

    @Test
    void testGetTitle() {
        RawTextDto dto = new RawTextDto();
        assertNull(dto.getTitle());
    }

    @Test
    void testSetTitle() {
        RawTextDto dto = new RawTextDto();
        String title = "Test Title";
        
        RawTextDto result = dto.setTitle(title);
        
        assertSame(dto, result);
        assertEquals(title, dto.getTitle());
    }

    @Test
    void testGetText() {
        RawTextDto dto = new RawTextDto();
        assertNull(dto.getText());
    }

    @Test
    void testSetText() {
        RawTextDto dto = new RawTextDto();
        String text = "Test Text";
        
        RawTextDto result = dto.setText(text);
        
        assertSame(dto, result);
        assertEquals(text, dto.getText());
    }

    @Test
    void testGetTags() {
        RawTextDto dto = new RawTextDto();
        assertNotNull(dto.getTags());
        assertTrue(dto.getTags().isEmpty());
    }

    @Test
    void testSetTags() {
        RawTextDto dto = new RawTextDto();
        Set<String> tags = new HashSet<>();
        tags.add("tag1");
        tags.add("tag2");
        
        RawTextDto result = dto.setTags(tags);
        
        assertSame(dto, result);
        assertEquals(tags, dto.getTags());
    }

    @Test
    void testGetCreateDate() {
        RawTextDto dto = new RawTextDto();
        assertNull(dto.getCreateDate());
    }

    @Test
    void testSetCreateDate() {
        RawTextDto dto = new RawTextDto();
        LocalDateTime date = LocalDateTime.now();
        
        RawTextDto result = dto.setCreateDate(date);
        
        assertSame(dto, result);
        assertEquals(date, dto.getCreateDate());
    }

    @Test
    void testGetUpdateDate() {
        RawTextDto dto = new RawTextDto();
        assertNull(dto.getUpdateDate());
    }

    @Test
    void testSetUpdateDate() {
        RawTextDto dto = new RawTextDto();
        LocalDateTime date = LocalDateTime.now();
        
        RawTextDto result = dto.setUpdateDate(date);
        
        assertSame(dto, result);
        assertEquals(date, dto.getUpdateDate());
    }
}