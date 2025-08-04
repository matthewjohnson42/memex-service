package com.matthewjohnson42.memex.data.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DtoForEntityTest {

    @Test
    void testDefaultConstructor() {
        DtoForEntity<String> dto = new DtoForEntity<String>() {
            @Override
            public String getId() {
                return id;
            }

            @Override
            public DtoForEntity<String> setId(String id) {
                this.id = id;
                return this;
            }
        };
        
        assertNotNull(dto);
        assertNull(dto.getId());
    }

    @Test
    void testCopyConstructorWithNull() {
        DtoForEntity<String> dto = new DtoForEntity<String>(null) {
            @Override
            public String getId() {
                return id;
            }

            @Override
            public DtoForEntity<String> setId(String id) {
                this.id = id;
                return this;
            }
        };
        
        assertNotNull(dto);
        assertNull(dto.getId());
    }

    @Test
    void testCopyConstructorWithValue() {
        DtoForEntity<String> original = new DtoForEntity<String>() {
            @Override
            public String getId() {
                return id;
            }

            @Override
            public DtoForEntity<String> setId(String id) {
                this.id = id;
                return this;
            }
        };
        original.setId("test-id");
        
        DtoForEntity<String> copy = new DtoForEntity<String>(original) {
            @Override
            public String getId() {
                return id;
            }

            @Override
            public DtoForEntity<String> setId(String id) {
                this.id = id;
                return this;
            }
        };
        
        assertEquals("test-id", copy.getId());
    }
}