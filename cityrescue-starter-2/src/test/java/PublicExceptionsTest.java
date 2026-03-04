import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import cityrescue.*;
import cityrescue.enums.*;
import cityrescue.exceptions.*;

public class PublicExceptionsTest {
    
    private CityRescue cr;

    @BeforeEach
    void setUp() throws Exception {
        cr = new CityRescueImpl();
        cr.initialise(5,5);
    }

    @Test   
    //out of Bounds Test
    void addObstacle_OutOfBounds() throws Exception {
        assertThrows(InvalidLocationException.class, () -> {
            cr.addObstacle(6, 7);});
    }

    @Test
    //empty validation test
    void blankName_forStation() throws Exception {
        assertThrows(InvalidNameException.class, () -> {
            cr.addStation("", 6, 7);});
    }

    @Test
    //Sets capacity of test station to impossible value
    void setStationCapacity_negative() throws Exception {
        int station = cr.addStation("Test", 6, 7);
        assertThrows(InvalidCapacityException.class, () -> {
            cr.setStationCapacity(station, -1);});
    }

    @Test
    void addInvalid_UnitType() throws Exception {
        int Station = cr.addStation("Test", 0, 0);
        assertThrows(InvalidUnitException.class, () -> {
            cr.addUnit(Station, null);
        });
    }

    @Test
    void reportIncident_withInvalidSeverity() {
        assertThrows(InvalidSeverityException.class, () -> {
            cr.reportIncident(IncidentType.FIRE, 0, 2, 2);
        });
    }

    @Test
    void viewUnit_thatDoesNotExist() {
        assertThrows(IDNotRecognisedException.class, () -> {
            cr.viewUnit(999);
            });
        }
    }