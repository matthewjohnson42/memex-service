package com.matthewjohnson42.memex.service.data.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class RawTextSearchRequestDtoTest {

    @Test
    void testParameterizedConstructor() {
        String searchString = "test search";
        int pageSize = 10;
        int pageNumber = 1;
        String sort = "date";
        LocalDateTime startCreateDate = LocalDateTime.of(2023, 1, 1, 0, 0);
        LocalDateTime endCreateDate = LocalDateTime.of(2023, 12, 31, 23, 59);
        LocalDateTime startUpdateDate = LocalDateTime.of(2023, 6, 1, 0, 0);
        LocalDateTime endUpdateDate = LocalDateTime.of(2023, 6, 30, 23, 59);
        
        RawTextSearchRequestDto dto = new RawTextSearchRequestDto(
            searchString, pageSize, pageNumber, sort,
            startCreateDate, endCreateDate, startUpdateDate, endUpdateDate
        );
        
        assertEquals(searchString, dto.getSearchString());
        assertEquals(pageSize, dto.getPageSize());
        assertEquals(pageNumber, dto.getPageNumber());
        assertEquals(sort, dto.getSort());
        assertEquals(startCreateDate, dto.getStartCreateDate());
        assertEquals(endCreateDate, dto.getEndCreateDate());
        assertEquals(startUpdateDate, dto.getStartUpdateDate());
        assertEquals(endUpdateDate, dto.getEndUpdateDate());
    }

    @Test
    void testParameterizedConstructorWithNulls() {
        int pageSize = 10;
        int pageNumber = 1;
        
        RawTextSearchRequestDto dto = new RawTextSearchRequestDto(
            null, pageSize, pageNumber, null, null, null, null, null
        );
        
        assertNull(dto.getSearchString());
        assertEquals(pageSize, dto.getPageSize());
        assertEquals(pageNumber, dto.getPageNumber());
        assertNull(dto.getSort());
        assertNull(dto.getStartCreateDate());
        assertNull(dto.getEndCreateDate());
        assertNull(dto.getStartUpdateDate());
        assertNull(dto.getEndUpdateDate());
    }

    @Test
    void testSetSearchString() {
        RawTextSearchRequestDto dto = new RawTextSearchRequestDto(
            null, 10, 1, null, null, null, null, null
        );
        String searchString = "new search";
        
        RawTextSearchRequestDto result = dto.setSearchString(searchString);
        
        assertSame(dto, result);
        assertEquals(searchString, dto.getSearchString());
    }

    @Test
    void testSetSearchStringWithNull() {
        RawTextSearchRequestDto dto = new RawTextSearchRequestDto(
            "initial", 10, 1, null, null, null, null, null
        );
        
        RawTextSearchRequestDto result = dto.setSearchString(null);
        
        assertSame(dto, result);
        assertNull(dto.getSearchString());
    }

    @Test
    void testSetPageSize() {
        RawTextSearchRequestDto dto = new RawTextSearchRequestDto(
            null, 10, 1, null, null, null, null, null
        );
        int newPageSize = 20;
        
        RawTextSearchRequestDto result = dto.setPageSize(newPageSize);
        
        assertSame(dto, result);
        assertEquals(newPageSize, dto.getPageSize());
    }

    @Test
    void testSetPageNumber() {
        RawTextSearchRequestDto dto = new RawTextSearchRequestDto(
            null, 10, 1, null, null, null, null, null
        );
        int newPageNumber = 5;
        
        RawTextSearchRequestDto result = dto.setPageNumber(newPageNumber);
        
        assertSame(dto, result);
        assertEquals(newPageNumber, dto.getPageNumber());
    }

    @Test
    void testSetSort() {
        RawTextSearchRequestDto dto = new RawTextSearchRequestDto(
            null, 10, 1, null, null, null, null, null
        );
        String newSort = "title";
        
        RawTextSearchRequestDto result = dto.setSort(newSort);
        
        assertSame(dto, result);
        assertEquals(newSort, dto.getSort());
    }

    @Test
    void testSetSortWithNull() {
        RawTextSearchRequestDto dto = new RawTextSearchRequestDto(
            null, 10, 1, "initial", null, null, null, null
        );
        
        RawTextSearchRequestDto result = dto.setSort(null);
        
        assertSame(dto, result);
        assertNull(dto.getSort());
    }

    @Test
    void testSetStartCreateDate() {
        RawTextSearchRequestDto dto = new RawTextSearchRequestDto(
            null, 10, 1, null, null, null, null, null
        );
        LocalDateTime date = LocalDateTime.of(2023, 1, 1, 0, 0);
        
        RawTextSearchRequestDto result = dto.setStartCreateDate(date);
        
        assertSame(dto, result);
        assertEquals(date, dto.getStartCreateDate());
    }

    @Test
    void testSetEndCreateDate() {
        RawTextSearchRequestDto dto = new RawTextSearchRequestDto(
            null, 10, 1, null, null, null, null, null
        );
        LocalDateTime date = LocalDateTime.of(2023, 12, 31, 23, 59);
        
        RawTextSearchRequestDto result = dto.setEndCreateDate(date);
        
        assertSame(dto, result);
        assertEquals(date, dto.getEndCreateDate());
    }

    @Test
    void testSetStartUpdateDate() {
        RawTextSearchRequestDto dto = new RawTextSearchRequestDto(
            null, 10, 1, null, null, null, null, null
        );
        LocalDateTime date = LocalDateTime.of(2023, 6, 1, 0, 0);
        
        RawTextSearchRequestDto result = dto.setStartUpdateDate(date);
        
        assertSame(dto, result);
        assertEquals(date, dto.getStartUpdateDate());
    }

    @Test
    void testSetEndUpdateDate() {
        RawTextSearchRequestDto dto = new RawTextSearchRequestDto(
            null, 10, 1, null, null, null, null, null
        );
        LocalDateTime date = LocalDateTime.of(2023, 6, 30, 23, 59);
        
        RawTextSearchRequestDto result = dto.setEndUpdateDate(date);
        
        assertSame(dto, result);
        assertEquals(date, dto.getEndUpdateDate());
    }

    @Test
    void testInheritanceFromPageRequestDto() {
        RawTextSearchRequestDto dto = new RawTextSearchRequestDto(
            "search", 15, 3, "name", null, null, null, null
        );
        
        // Test that it properly inherits from PageRequestDto
        assertEquals(15, dto.getPageSize());
        assertEquals(3, dto.getPageNumber());
        assertEquals("name", dto.getSort());
    }
}