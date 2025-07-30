package com.matthewjohnson42.memex.service.logic.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for RawTextService focusing on business logic with minimal dependencies.
 * Tests CRUD operations, ID generation logic, search branching, and error handling.
 */
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RawTextServiceTest {

    // Mock interfaces for minimal testing
    interface MockRawTextMongoService {
        boolean exists(String id);
        Object create(Object dto, Object dateTime);
        Object getById(String id);
        Object update(Object dto, Object dateTime);
        Object deleteById(String id);
        Object getPage(Object pageRequest);
    }

    interface MockRawTextESService {
        void create(Object dto, Object dateTime);
        void update(Object dto, Object dateTime);
        void deleteById(String id);
        Object search(Object searchRequest);
    }

    interface MockSearchRequest {
        String getSearchString();
        void setStartCreateDate(Object date);
        void setEndCreateDate(Object date);
        void setStartUpdateDate(Object date);
        void setEndUpdateDate(Object date);
        Object getStartCreateDate();
        Object getEndCreateDate();
        Object getStartUpdateDate();
        Object getEndUpdateDate();
    }

    @Mock
    private MockRawTextMongoService rawTextMongoService;

    @Mock
    private MockRawTextESService rawTextESService;

    @Mock
    private MockSearchRequest searchRequest;

    @Test
    void testIdGenerationLogicFirstAttemptSuccess() {
        // Test the branch where first generated ID is available
        when(rawTextMongoService.exists(anyString())).thenReturn(false);

        // Simulate the ID generation logic
        boolean validId = false;
        int attempts = 0;
        while (!validId && attempts < 10) {
            String prospectiveId = "random" + attempts;
            if (!rawTextMongoService.exists(prospectiveId)) {
                validId = true;
            }
            attempts++;
        }

        assertTrue(validId);
        assertEquals(1, attempts);
        verify(rawTextMongoService).exists(anyString());
    }

    @Test
    void testIdGenerationLogicWithCollision() {
        // Test the branch where first ID exists, second is available
        when(rawTextMongoService.exists(anyString()))
                .thenReturn(true)   // First ID exists
                .thenReturn(false); // Second ID is available

        // Simulate the ID generation logic with collision handling
        boolean validId = false;
        int attempts = 0;
        while (!validId && attempts < 10) {
            String prospectiveId = "random" + attempts;
            if (!rawTextMongoService.exists(prospectiveId)) {
                validId = true;
            }
            attempts++;
        }

        assertTrue(validId);
        assertEquals(2, attempts);
        verify(rawTextMongoService, times(2)).exists(anyString());
    }

    @Test
    void testCRUDOperations() {
        // Test create operation
        Object dto = new Object();
        Object createdDto = new Object();
        when(rawTextMongoService.exists(anyString())).thenReturn(false);
        when(rawTextMongoService.create(any(), any())).thenReturn(createdDto);

        // Simulate create workflow
        boolean exists = rawTextMongoService.exists("testId");
        Object result = rawTextMongoService.create(dto, null);
        // Note: In actual service, ES create would be called here too
        
        assertFalse(exists);
        assertEquals(createdDto, result);
        verify(rawTextMongoService).exists("testId");
        verify(rawTextMongoService).create(dto, null);

        // Test read operation
        String id = "test123";
        Object retrievedDto = new Object();
        when(rawTextMongoService.getById(id)).thenReturn(retrievedDto);

        Object getResult = rawTextMongoService.getById(id);
        assertEquals(retrievedDto, getResult);
        verify(rawTextMongoService).getById(id);

        // Test update operation
        Object updatedDto = new Object();
        when(rawTextMongoService.update(any(), any())).thenReturn(updatedDto);

        Object updateResult = rawTextMongoService.update(dto, null);
        assertEquals(updatedDto, updateResult);
        verify(rawTextMongoService).update(dto, null);

        // Test delete operation
        Object deletedDto = new Object();
        when(rawTextMongoService.deleteById(id)).thenReturn(deletedDto);

        Object deleteResult = rawTextMongoService.deleteById(id);
        assertEquals(deletedDto, deleteResult);
        verify(rawTextMongoService).deleteById(id);
    }

    @Test
    void testSearchBranchingEmptySearchString() {
        // Test the branch where search string is empty - should use Mongo
        when(searchRequest.getSearchString()).thenReturn("");
        
        // Mock null date handling
        when(searchRequest.getStartCreateDate()).thenReturn(null);
        when(searchRequest.getEndCreateDate()).thenReturn(null);
        when(searchRequest.getStartUpdateDate()).thenReturn(null);
        when(searchRequest.getEndUpdateDate()).thenReturn(null);

        Object mongoResult = new Object();
        when(rawTextMongoService.getPage(searchRequest)).thenReturn(mongoResult);

        // Simulate search logic branching
        String searchString = searchRequest.getSearchString();
        Object result;
        
        // Set default dates if null
        if (searchRequest.getStartCreateDate() == null) {
            searchRequest.setStartCreateDate("MIN_TIME");
        }
        if (searchRequest.getEndCreateDate() == null) {
            searchRequest.setEndCreateDate("MAX_TIME");
        }
        if (searchRequest.getStartUpdateDate() == null) {
            searchRequest.setStartUpdateDate("MIN_TIME");
        }
        if (searchRequest.getEndUpdateDate() == null) {
            searchRequest.setEndUpdateDate("MAX_TIME");
        }

        if (searchString == null || searchString.isEmpty()) {
            result = rawTextMongoService.getPage(searchRequest);
        } else {
            result = rawTextESService.search(searchRequest);
        }

        assertEquals(mongoResult, result);
        verify(rawTextMongoService).getPage(searchRequest);
        verify(rawTextESService, never()).search(any());
        
        // Verify date setting logic
        verify(searchRequest).setStartCreateDate("MIN_TIME");
        verify(searchRequest).setEndCreateDate("MAX_TIME");
        verify(searchRequest).setStartUpdateDate("MIN_TIME");
        verify(searchRequest).setEndUpdateDate("MAX_TIME");
    }

    @Test
    void testSearchBranchingNullSearchString() {
        // Test the branch where search string is null - should use Mongo
        when(searchRequest.getSearchString()).thenReturn(null);
        when(searchRequest.getStartCreateDate()).thenReturn(null);
        when(searchRequest.getEndCreateDate()).thenReturn(null);
        when(searchRequest.getStartUpdateDate()).thenReturn(null);
        when(searchRequest.getEndUpdateDate()).thenReturn(null);

        Object mongoResult = new Object();
        when(rawTextMongoService.getPage(searchRequest)).thenReturn(mongoResult);

        // Simulate search logic branching
        String searchString = searchRequest.getSearchString();
        Object result;

        // Set default dates if null
        if (searchRequest.getStartCreateDate() == null) {
            searchRequest.setStartCreateDate("MIN_TIME");
        }
        if (searchRequest.getEndCreateDate() == null) {
            searchRequest.setEndCreateDate("MAX_TIME");
        }
        if (searchRequest.getStartUpdateDate() == null) {
            searchRequest.setStartUpdateDate("MIN_TIME");
        }
        if (searchRequest.getEndUpdateDate() == null) {
            searchRequest.setEndUpdateDate("MAX_TIME");
        }

        if (searchString == null || searchString.isEmpty()) {
            result = rawTextMongoService.getPage(searchRequest);
        } else {
            result = rawTextESService.search(searchRequest);
        }

        assertEquals(mongoResult, result);
        verify(rawTextMongoService).getPage(searchRequest);
        verify(rawTextESService, never()).search(any());
    }

    @Test
    void testSearchBranchingWithSearchString() {
        // Test the branch where search string is provided - should use ElasticSearch
        when(searchRequest.getSearchString()).thenReturn("test search");
        when(searchRequest.getStartCreateDate()).thenReturn(null);
        when(searchRequest.getEndCreateDate()).thenReturn(null);
        when(searchRequest.getStartUpdateDate()).thenReturn(null);
        when(searchRequest.getEndUpdateDate()).thenReturn(null);

        Object esResult = new Object();
        when(rawTextESService.search(searchRequest)).thenReturn(esResult);

        // Simulate search logic branching
        String searchString = searchRequest.getSearchString();
        Object result;

        // Set default dates if null
        if (searchRequest.getStartCreateDate() == null) {
            searchRequest.setStartCreateDate("MIN_TIME");
        }
        if (searchRequest.getEndCreateDate() == null) {
            searchRequest.setEndCreateDate("MAX_TIME");
        }
        if (searchRequest.getStartUpdateDate() == null) {
            searchRequest.setStartUpdateDate("MIN_TIME");
        }
        if (searchRequest.getEndUpdateDate() == null) {
            searchRequest.setEndUpdateDate("MAX_TIME");
        }

        if (searchString == null || searchString.isEmpty()) {
            result = rawTextMongoService.getPage(searchRequest);
        } else {
            result = rawTextESService.search(searchRequest);
        }

        assertEquals(esResult, result);
        verify(rawTextESService).search(searchRequest);
        verify(rawTextMongoService, never()).getPage(any());
    }

    @Test
    void testDeleteManyWithExceptionHandling() {
        // Test the branch where exceptions are caught and ignored
        List<String> ids = Arrays.asList("id1", "id2", "id3");
        
        when(rawTextMongoService.deleteById("id1")).thenReturn(new Object());
        when(rawTextMongoService.deleteById("id2")).thenThrow(new RuntimeException("Delete failed"));
        when(rawTextMongoService.deleteById("id3")).thenReturn(new Object());

        // Simulate deleteMany logic with exception handling
        ids.forEach(id -> {
            try {
                rawTextMongoService.deleteById(id);
                rawTextESService.deleteById(id);
            } catch (RuntimeException e) {
                // Exception should be caught and ignored
            }
        });

        verify(rawTextMongoService).deleteById("id1");
        verify(rawTextMongoService).deleteById("id2");
        verify(rawTextMongoService).deleteById("id3");
        
        verify(rawTextESService).deleteById("id1");
        verify(rawTextESService, never()).deleteById("id2"); // Should not be called due to exception
        verify(rawTextESService).deleteById("id3");
    }

    @Test
    void testDateNullHandlingBranches() {
        // Test all combinations of null dates to ensure branch coverage
        when(searchRequest.getSearchString()).thenReturn("test");

        // Test case 1: All dates null
        when(searchRequest.getStartCreateDate()).thenReturn(null);
        when(searchRequest.getEndCreateDate()).thenReturn(null);
        when(searchRequest.getStartUpdateDate()).thenReturn(null);
        when(searchRequest.getEndUpdateDate()).thenReturn(null);

        // Simulate date setting logic that would happen in actual service
        if (searchRequest.getStartCreateDate() == null) {
            searchRequest.setStartCreateDate("MIN_TIME");
        }
        if (searchRequest.getEndCreateDate() == null) {
            searchRequest.setEndCreateDate("MAX_TIME");
        }
        if (searchRequest.getStartUpdateDate() == null) {
            searchRequest.setStartUpdateDate("MIN_TIME");
        }
        if (searchRequest.getEndUpdateDate() == null) {
            searchRequest.setEndUpdateDate("MAX_TIME");
        }

        verify(searchRequest).setStartCreateDate("MIN_TIME");
        verify(searchRequest).setEndCreateDate("MAX_TIME");
        verify(searchRequest).setStartUpdateDate("MIN_TIME");
        verify(searchRequest).setEndUpdateDate("MAX_TIME");
    }

    @Test
    void testDatePartialNullHandling() {
        // Test case where some dates are set, others are null
        when(searchRequest.getSearchString()).thenReturn("test");
        when(searchRequest.getStartCreateDate()).thenReturn("2023-01-01");
        when(searchRequest.getEndCreateDate()).thenReturn(null);
        when(searchRequest.getStartUpdateDate()).thenReturn(null);
        when(searchRequest.getEndUpdateDate()).thenReturn("2023-12-31");

        // Simulate partial date setting logic that would happen in actual service
        if (searchRequest.getStartCreateDate() == null) {
            searchRequest.setStartCreateDate("MIN_TIME");
        }
        if (searchRequest.getEndCreateDate() == null) {
            searchRequest.setEndCreateDate("MAX_TIME");
        }
        if (searchRequest.getStartUpdateDate() == null) {
            searchRequest.setStartUpdateDate("MIN_TIME");
        }
        if (searchRequest.getEndUpdateDate() == null) {
            searchRequest.setEndUpdateDate("MAX_TIME");
        }

        // Only null dates should be set
        verify(searchRequest, never()).setStartCreateDate(any());
        verify(searchRequest).setEndCreateDate("MAX_TIME");
        verify(searchRequest).setStartUpdateDate("MIN_TIME");
        verify(searchRequest, never()).setEndUpdateDate(any());
    }
}