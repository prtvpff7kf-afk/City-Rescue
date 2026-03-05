import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import cityrescue.*;
import cityrescue.enums.*;
import cityrescue.exceptions.*;

public class PublicTieBreaksTest {

    private CityRescue cr;

    @BeforeEach 
    void setUp() throws Exception {
        cr = new CityRescueImpl();
        cr.initialise(5,5);
    }

    @Test
    void dispatch_processesIncidents_inAscendingOrder() throws Exception {

        int Station = cr.addStation("Test",0,0);
        int FireTruck = cr.addUnit(Station, UnitType.FIRE_ENGINE);
        int Fire1 = cr.reportIncident(IncidentType.FIRE, 3, 4, 4);
        int Fire2 = cr.reportIncident(IncidentType.FIRE, 3, 3, 4);

        cr.dispatch();
        assertTrue(cr.viewIncident(Fire1).contains("STATUS=DISPATCHED"), "First fire is dispatched");
        assertTrue(cr.viewIncident(Fire1).contains("UNIT=" + FireTruck));
        
        //second fire should be left alone
        assertTrue(cr.viewIncident(Fire2).contains("STATUS=REPORTED"), "First fire is dispatched");
        assertTrue(cr.viewIncident(Fire2).contains("UNIT=-"));
    }
}
