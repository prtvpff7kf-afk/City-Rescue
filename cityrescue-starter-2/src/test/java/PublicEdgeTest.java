import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import cityrescue.*;
import cityrescue.enums.*;
import cityrescue.exceptions.*;

public class PublicEdgeTest {

    private CityRescue cr;

    @BeforeEach
    void setUp() throws Exception {
        cr = new CityRescueImpl();
        cr.initialise(5, 5);
    }

    @Test
    void AddObstaclesAtCorners() throws Exception {
        
        cr.addObstacle(0,0);
        cr.addObstacle(0,4);
        cr.addObstacle(4,0);
        cr.addObstacle(4,4);
        assertTrue(cr.getStatus().contains("OBSTACLES=4"));
    }

    @Test
    void UnitStuckByObstacles() throws Exception {

        int Station = cr.addStation("Test", 1, 1);
        int Ambulance = cr.addUnit(Station, UnitType.AMBULANCE);

        // obstacles so the unit so there are no legal moves
        cr.addObstacle(1,0);
        cr.addObstacle(2,1);
        cr.addObstacle(1,2);
        cr.addObstacle(0,1);

        // assertions pass - > the unit is stuck
        int Incident = cr.reportIncident(IncidentType.MEDICAL, 3, 3, 3);
        cr.dispatch();
        assertTrue(cr.viewUnit(Ambulance).contains("LOC=(1,1)"));
        cr.tick();
        assertTrue(cr.viewUnit(Ambulance).contains("LOC=(1,1)"));
        
    }
}
