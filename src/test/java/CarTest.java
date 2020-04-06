import org.easymock.EasyMock;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    private Car myFerrari = EasyMock.createMock(Car.class);

    @Test
    public void test_instance_car() {
        assertTrue(myFerrari instanceof Car);
    }

    @Test
    public void test_default_behavior_needsFuel() {
        assertFalse(myFerrari.needsFuel(), "New test double should return False as boolean");
    }

    @Test
    public void test_default_behavior_temperature() {
        assertEquals(0.0, myFerrari.getEngineTemperature(), "New test double should return 0.0");
    }

    @Test
    public void test_stubbing_mock() {
        EasyMock.expect(myFerrari.needsFuel()).andReturn(true);
        EasyMock.replay(myFerrari);
        assertTrue(myFerrari.needsFuel());
    }

    @Test
    public void test_needsFuel_exception() {
        EasyMock.expect(myFerrari.needsFuel()).andThrow(new RuntimeException());
        EasyMock.replay(myFerrari);
        assertThrows(RuntimeException.class, () -> {
            myFerrari.needsFuel();
        });
    }

    // Zadanie 2

    @Test
    public void test_driveTo() {
        myFerrari.driveTo("Miami");
        EasyMock.expectLastCall();
        EasyMock.replay(myFerrari);
        myFerrari.driveTo("Miami");
    }

    @Test
    public void test_driveTo_exception() {
        myFerrari.driveTo(null);
        EasyMock.expectLastCall().andThrow(new RuntimeException());
        EasyMock.replay(myFerrari);
        assertThrows(RuntimeException.class, () -> myFerrari.driveTo(null));
    }

    @Test
    public void test_getEngineTemperature() {
        EasyMock.expect(myFerrari.getEngineTemperature()).andReturn(70.0);
        EasyMock.replay(myFerrari);
        assertEquals(70.0, myFerrari.getEngineTemperature());
    }

    @Test
    public void test_getEngineTemperature_2() {
        EasyMock.expect(myFerrari.getEngineTemperature()).andReturn(130.0);
        EasyMock.replay(myFerrari);
        assertEquals(130.0, myFerrari.getEngineTemperature());
    }

    @Test
    public void test_driveTo_not_enough_fuel() {
        EasyMock.expect(myFerrari.needsFuel()).andReturn(true);
        myFerrari.driveTo("New York");
        EasyMock.expectLastCall().andThrow(new RuntimeException("Can't drive to New York. Needs more fuel."));
        EasyMock.replay(myFerrari);
        assertThrows(RuntimeException.class, () -> myFerrari.driveTo("New York"));
    }
}