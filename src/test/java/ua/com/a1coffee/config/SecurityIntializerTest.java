package ua.com.a1coffee.config;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class SecurityIntializerTest {

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"SecurityInitializer\" - START.\n");
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"SecurityInitializer\" - FINISH.\n");
    }

    @Test
    public void ConstructorTest() throws Exception {
        System.out.print("-> SecurityInitializer() - ");
        assertNotNull(new SecurityIntializer());
        System.out.println("OK!");
    }
}
