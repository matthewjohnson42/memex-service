package com.matthewjohnson42.memex.service.data.dto;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class RawTextSearchResponseDtoTest {

    @Test
    void testDefaultConstructor() {
        RawTextSearchResponseDto dto = new RawTextSearchResponseDto();
        assertNotNull(dto);
        assertNotNull(dto.getHighlights());
        assertTrue(dto.getHighlights().isEmpty());
    }

    @Test
    void testCopyConstructor() {
        RawTextSearchResponseDto original = new RawTextSearchResponseDto();
        List<String> highlights = Arrays.asList("highlight1", "highlight2");
        original.setHighlights(highlights);
        
        RawTextSearchResponseDto copy = new RawTextSearchResponseDto(original);
        
        assertEquals(original.getHighlights(), copy.getHighlights());
        assertEquals(2, copy.getHighlights().size());
        assertTrue(copy.getHighlights().contains("highlight1"));
        assertTrue(copy.getHighlights().contains("highlight2"));
    }

    @Test
    void testCopyConstructorWithEmptyHighlights() {
        RawTextSearchResponseDto original = new RawTextSearchResponseDto();
        // Original already has empty highlights from default constructor
        
        RawTextSearchResponseDto copy = new RawTextSearchResponseDto(original);
        
        assertNotNull(copy.getHighlights());
        assertTrue(copy.getHighlights().isEmpty());
    }

    @Test
    void testGetHighlights() {
        RawTextSearchResponseDto dto = new RawTextSearchResponseDto();
        List<String> highlights = Arrays.asList("highlight1", "highlight2", "highlight3");
        dto.setHighlights(highlights);
        
        assertEquals(highlights, dto.getHighlights());
        assertEquals(3, dto.getHighlights().size());
    }

    @Test
    void testSetHighlights() {
        RawTextSearchResponseDto dto = new RawTextSearchResponseDto();
        List<String> highlights = Arrays.asList("highlight1", "highlight2");
        
        RawTextSearchResponseDto result = dto.setHighlights(highlights);
        
        assertSame(dto, result); // Should return this for fluent API
        assertEquals(highlights, dto.getHighlights());
    }

    @Test
    void testSetHighlightsWithNull() {
        RawTextSearchResponseDto dto = new RawTextSearchResponseDto();
        
        RawTextSearchResponseDto result = dto.setHighlights(null);
        
        assertSame(dto, result);
        assertNull(dto.getHighlights());
    }

    @Test
    void testSetHighlightsWithEmptyList() {
        RawTextSearchResponseDto dto = new RawTextSearchResponseDto();
        List<String> emptyHighlights = new ArrayList<>();
        
        RawTextSearchResponseDto result = dto.setHighlights(emptyHighlights);
        
        assertSame(dto, result);
        assertEquals(emptyHighlights, dto.getHighlights());
        assertTrue(dto.getHighlights().isEmpty());
    }

    @Test
    void testSetHighlightsWithSingleElement() {
        RawTextSearchResponseDto dto = new RawTextSearchResponseDto();
        List<String> singleHighlight = Arrays.asList("single highlight");
        
        dto.setHighlights(singleHighlight);
        
        assertEquals(1, dto.getHighlights().size());
        assertEquals("single highlight", dto.getHighlights().get(0));
    }

    @Test
    void testInheritanceFromRawTextDto() {
        RawTextSearchResponseDto dto = new RawTextSearchResponseDto();
        
        // Test that it properly inherits from RawTextDto
        // These methods should be available from the parent class
        assertNotNull(dto.getTags());
        assertNull(dto.getTitle());
        assertNull(dto.getText());
        assertNull(dto.getId());
        assertNull(dto.getCreateDate());
        assertNull(dto.getUpdateDate());
    }

    @Test
    void testFluentApiChaining() {
        RawTextSearchResponseDto dto = new RawTextSearchResponseDto();
        List<String> highlights = Arrays.asList("highlight1", "highlight2");
        
        dto.setTitle("Test Title");
        dto.setText("Test Text");
        RawTextSearchResponseDto result = dto.setHighlights(highlights);
        
        assertSame(dto, result);
        assertEquals("Test Title", dto.getTitle());
        assertEquals("Test Text", dto.getText());
        assertEquals(highlights, dto.getHighlights());
    }
}