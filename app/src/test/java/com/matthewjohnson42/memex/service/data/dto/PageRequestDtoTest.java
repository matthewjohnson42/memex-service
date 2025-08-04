package com.matthewjohnson42.memex.service.data.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PageRequestDtoTest {

    @Test
    void testDefaultConstructor() {
        PageRequestDto dto = new PageRequestDto();
        assertNotNull(dto);
        assertEquals(0, dto.getPageSize());
        assertEquals(0, dto.getPageNumber());
        assertNull(dto.getSort());
    }

    @Test
    void testSetPageSize() {
        PageRequestDto dto = new PageRequestDto();
        int pageSize = 10;
        
        PageRequestDto result = dto.setPageSize(pageSize);
        
        assertSame(dto, result); // Should return this for fluent API
        assertEquals(pageSize, dto.getPageSize());
    }

    @Test
    void testSetPageSizeWithZero() {
        PageRequestDto dto = new PageRequestDto();
        
        PageRequestDto result = dto.setPageSize(0);
        
        assertSame(dto, result);
        assertEquals(0, dto.getPageSize());
    }

    @Test
    void testSetPageSizeWithNegative() {
        PageRequestDto dto = new PageRequestDto();
        
        PageRequestDto result = dto.setPageSize(-1);
        
        assertSame(dto, result);
        assertEquals(-1, dto.getPageSize());
    }

    @Test
    void testGetPageSize() {
        PageRequestDto dto = new PageRequestDto();
        int pageSize = 20;
        dto.setPageSize(pageSize);
        
        assertEquals(pageSize, dto.getPageSize());
    }

    @Test
    void testSetPageNumber() {
        PageRequestDto dto = new PageRequestDto();
        int pageNumber = 5;
        
        PageRequestDto result = dto.setPageNumber(pageNumber);
        
        assertSame(dto, result); // Should return this for fluent API
        assertEquals(pageNumber, dto.getPageNumber());
    }

    @Test
    void testSetPageNumberWithZero() {
        PageRequestDto dto = new PageRequestDto();
        
        PageRequestDto result = dto.setPageNumber(0);
        
        assertSame(dto, result);
        assertEquals(0, dto.getPageNumber());
    }

    @Test
    void testSetPageNumberWithNegative() {
        PageRequestDto dto = new PageRequestDto();
        
        PageRequestDto result = dto.setPageNumber(-1);
        
        assertSame(dto, result);
        assertEquals(-1, dto.getPageNumber());
    }

    @Test
    void testGetPageNumber() {
        PageRequestDto dto = new PageRequestDto();
        int pageNumber = 3;
        dto.setPageNumber(pageNumber);
        
        assertEquals(pageNumber, dto.getPageNumber());
    }

    @Test
    void testSetSort() {
        PageRequestDto dto = new PageRequestDto();
        String sort = "name";
        
        PageRequestDto result = dto.setSort(sort);
        
        assertSame(dto, result); // Should return this for fluent API
        assertEquals(sort, dto.getSort());
    }

    @Test
    void testSetSortWithNull() {
        PageRequestDto dto = new PageRequestDto();
        
        PageRequestDto result = dto.setSort(null);
        
        assertSame(dto, result);
        assertNull(dto.getSort());
    }

    @Test
    void testGetSort() {
        PageRequestDto dto = new PageRequestDto();
        String sort = "date";
        dto.setSort(sort);
        
        assertEquals(sort, dto.getSort());
    }
}