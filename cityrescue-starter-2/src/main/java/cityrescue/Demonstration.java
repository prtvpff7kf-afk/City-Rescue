package cityrescue;

import java.util.Arrays;

import cityrescue.enums.*;
import cityrescue.exceptions.*;

public class Demonstration {
    public static void main(String[] args) throws Exception {

    CityRescue cr = new CityRescueImpl();


    cr.initialise(5,5);
    System.out.println(cr.getGridSize());
    System.out.println("\n");

    cr.addObstacle(2, 1);
    cr.addObstacle(0, 1);
    cr.addObstacle(4, 4);
    System.out.println(cr.getStatus());
    System.out.println("\n");

    cr.removeObstacle(4, 4);
    System.out.println(cr.getStatus());
    System.out.println("\n");

    int policeStation1 = cr.addStation("policeStation1", 0, 0);
    int policeStation2 = cr.addStation("policeStation2", 4, 4);
    System.out.println(Arrays.toString(cr.getStationIds()));
    System.out.println("\n");

    int PoliceCar1 = cr.addUnit(policeStation2, UnitType.POLICE_CAR);
    int PoliceCar2 = cr.addUnit(policeStation2, UnitType.POLICE_CAR);
    System.out.println(Arrays.toString(cr.getUnitIds()));
    System.out.println("\n");

    cr.transferUnit(PoliceCar1, policeStation1);
    System.out.println(cr.viewUnit(PoliceCar1));
    System.out.println("\n");

    cr.setUnitOutOfService(PoliceCar2, true);

    int Crime = cr.reportIncident(IncidentType.CRIME,2,3,3);
    System.out.println(cr.viewIncident(Crime));
    System.out.println("\n");

    cr.dispatch();
    System.out.println(cr.viewUnit(PoliceCar1));
    System.out.println(cr.viewUnit(PoliceCar2));
    System.out.println(cr.viewIncident(Crime));
    System.out.println("\n");

    for (int i = 0; i < 10; i++) {
        cr.tick();
        System.out.println(cr.getStatus());
        System.out.println("\n");
    }  

    cr.decommissionUnit(PoliceCar2);
    System.out.println(Arrays.toString(cr.getUnitIds()));
    System.out.println("\n");

    cr.removeStation(policeStation2);
    System.out.println(Arrays.toString(cr.getStationIds()));
    System.out.println("\n");

    int Crime2 = cr.reportIncident(IncidentType.CRIME, PoliceCar1, 4, 3);
    System.out.println(cr.getStatus());
    System.out.println("\n");

    cr.cancelIncident(Crime2);
    System.out.println(cr.getStatus());
    System.out.println("\n");

    }
}