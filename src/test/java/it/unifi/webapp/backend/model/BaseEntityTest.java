package it.unifi.webapp.backend.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class BaseEntityTest {

    private FakeBaseEntity e1, e2, e3; 	//FakeBaseEntity is a class declared inside this test just because the scope
    //is inside this test

    @Before //@Before means that this setUp() method is called before everytime a test is executed (just before the execution)
    public void setUp() {
        String uuid1 = UUID.randomUUID().toString();
        String uuid2 = UUID.randomUUID().toString();
        e1 = new FakeBaseEntity( uuid1 );
        e2 = new FakeBaseEntity( uuid2 );
        e3 = new FakeBaseEntity( uuid1 );
    }

    @Test( expected = IllegalArgumentException.class )
    public void testNullUUID() {
        new FakeBaseEntity( null );
    }

    @Test
    public void testEquals() {
        assertEquals( e1, e1 ); // tests for identity
        assertEquals( e1, e3 ); // tests for equality
    }

    @Test
    public void testHashCode() {
        assertEquals( e1.hashCode(), e1.hashCode() );
        assertEquals( e1.hashCode(), e3.hashCode() );
    }

    @Test
    public void testNotEquals() {
        assertNotEquals( e1, e2 );
        assertNotEquals( e3, e2 );
        assertNotEquals( e1, null );
    }

    class FakeBaseEntity extends BaseEntity {
        public FakeBaseEntity( String uuid ) {
            super( uuid );
        }
    }

}
