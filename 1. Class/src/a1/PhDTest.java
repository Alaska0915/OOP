package a1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class PhDTest {

    @Test
    public void testConstructor1() {
        PhD a1= new PhD("Sikai", 2025, 6);
        assertEquals("Sikai", a1.name());
        assertEquals("6/2025", a1.date());
        assertEquals(null, a1.advisor1());
        assertEquals(null, a1.advisor2());
        assertEquals(0, a1.advisees());

    }

    @Test
    public void testMutators() {
        PhD b= new PhD("Shien", 2028, 6);
        PhD b1= new PhD("b1", 1999, 12);
        PhD b2= new PhD("b2", 1980, 11);
        b.addAdvisor1(b1);
        b.addAdvisor2(b2);

        assertEquals(b1, b.advisor1());
        assertEquals(b2, b.advisor2());
        assertEquals(1, b1.advisees());
        assertEquals(1, b2.advisees());
    }

    @Test
    public void testConstructor2() {
        PhD c1= new PhD("c1", 1999, 12);
        PhD c2= new PhD("c2", 1980, 11);
        PhD c= new PhD("Shien", 2028, 6, c1, c2);

        assertEquals("Shien", c.name());
        assertEquals("6/2028", c.date());
        assertEquals(c1, c.advisor1());
        assertEquals(c2, c.advisor2());
        assertEquals(0, c.advisees());
        assertEquals(1, c1.advisees());
        assertEquals(1, c2.advisees());

    }

    @Test
    public void testD() {
        PhD d1= new PhD("d1", 1999, 12);
        PhD d2= new PhD("d2", 1980, 11);
        PhD D1= new PhD("Shien", 2028, 6, d1, d2);
        PhD D2= new PhD("Sikai", 2028, 4, d1, D1);
        d1.addAdvisor1(d2);

        assertEquals(false, D1.gotBefore(d2));
        assertEquals(true, D2.gotBefore(D1));
        assertEquals(true, D1.isSiblingOf(D2));
        assertEquals(true, D1.isSiblingOf(d1));
        assertEquals(false, d2.isSiblingOf(D2));
    }

    @Test
    public void testassert() {
        assertThrows(AssertionError.class, () -> {
            new PhD(null, 1970, 6);
        });

        assertThrows(AssertionError.class, () -> {
            new PhD("s", 1970, 6);
        });

        assertThrows(AssertionError.class, () -> {
            new PhD("Shien", 1970, 0);
        });

        assertThrows(AssertionError.class, () -> {
            new PhD("Shien", 1970, 13);
        });

        PhD e1= new PhD("e1", 1999, 12);
        PhD e2= new PhD("e2", 1980, 11);
        PhD e3= new PhD("e3", 1980, 11);

        assertThrows(AssertionError.class, () -> {
            new PhD(null, 1970, 6, e1, e2);
        });

        assertThrows(AssertionError.class, () -> {
            new PhD("s", 1970, 6, e1, e2);
        });

        assertThrows(AssertionError.class, () -> {
            new PhD("Shien", 1970, -1, e1, e2);
        });

        assertThrows(AssertionError.class, () -> {
            new PhD("Shien", 1970, 15, e1, e2);
        });

        assertThrows(AssertionError.class, () -> {
            new PhD("Shien", 1970, 1, null, e2);
        });

        assertThrows(AssertionError.class, () -> {
            new PhD("Shien", 1970, 1, e1, null);
        });

        assertThrows(AssertionError.class, () -> {
            new PhD("Shien", 1970, 1, e1, e1);
        });

        assertThrows(AssertionError.class, () -> {
            PhD f1= new PhD("Shien", 1970, 0, e1, e2);
            f1.addAdvisor1(e3);
        });

        assertThrows(AssertionError.class, () -> {
            PhD f2= new PhD("Shien", 1970, 0);
            f2.addAdvisor1(null);
        });

        assertThrows(AssertionError.class, () -> {
            PhD f3= new PhD("Shien", 1970, 0);
            f3.addAdvisor2(e3);
        });

        assertThrows(AssertionError.class, () -> {
            PhD f4= new PhD("Shien", 1970, 0, e1, e2);
            f4.addAdvisor2(e3);
        });

        assertThrows(AssertionError.class, () -> {
            PhD f5= new PhD("Shien", 1970, 0);
            f5.addAdvisor1(e1);
            f5.addAdvisor2(e1);
        });

        assertThrows(AssertionError.class, () -> {
            PhD f6= new PhD("Shien", 1970, 0);
            f6.addAdvisor1(e1);
            f6.addAdvisor2(null);
        });

        assertThrows(AssertionError.class, () -> {
            PhD g1= new PhD("Shien", 1970, 0, e1, e2);
            g1.gotBefore(null);
        });

        assertThrows(AssertionError.class, () -> {
            PhD g2= new PhD("Shien", 1970, 0, e1, e2);
            g2.isSiblingOf(null);
        });

    }
}
