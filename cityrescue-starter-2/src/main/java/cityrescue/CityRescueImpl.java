package cityrescue;

import cityrescue.enums.*;
import cityrescue.exceptions.*;

import java.util.Arrays;
import java.util.ArrayList;



/**
 * CityRescueImpl (Starter)
 *
 * Your task is to implement the full specification.
 * You may add additional classes in any package(s) you like.
 */
public class CityRescueImpl implements CityRescue {

    private static class Station{
        private final int stationId;
        private final String name;
        private final int x;
        private final int y;
        
        private int maxUnits = Integer.MAX_VALUE;
        private final int[] unitIds = new int[50];
        private int UnitCount = 0;

        // Constructor
        Station(int stationId, String name, int x, int y) {
            this.stationId = stationId;
            this.name = name;
            this.x = x;
            this.y = y;
        }
        boolean hasCapacity() {
            return unitCount < maxUnits;
        }
        void addUnit(int unitId) {
            unitIDs[unitCount++] = unitId;
        }

        // removeUnit (do later)
    }

    private static class Incident{
        IncidentType type;
        IncidentStatus status;
        int severity;
        int x;
        int y;
        int incidentId;
        public Incident(int incidentId, IncidentType type, int severity, int x, int y) {
            this.incidentId = incidentId;
            this.type = type;
            this.severity = severity;
            this.x = x;
            this.y = y;
            this.status = IncidentStatus.REPORTED;
        }
    }

    //unit superclass
    private static class Unit{
        int unitId;
        int stationId;
        UnitType type;
        int x;
        int y;
        UnitStatus status = UnitStatus.IDLE;
        Integer incidentId = null;

        boolean outOfService = false;
        int assignedIncidentId = -1;

        // constructor
        Unit(int unitId, int stationId, UnitType type, int x, int y) {
            this.unitId = unitId;
            this.stationId = stationId;
            this.type = type;
            this.x = x;
            this.y = y;
        }
    }

    //Subclasses for each unit
    private class Ambulance extends Unit {
        Ambulance(int unitId, int stationId, int x, int y) {
            super(unitId, stationId, UnitType.AMBULANCE, x, y);
        }
    }

    private class FireEngine extends Unit {
        FireEngine(int unitId, int stationId, int x, int y) {
            super(unitId, stationId, UnitType.FIRE_ENGINE, x, y);
        }
    }

    private class PoliceCar extends Unit {
        PoliceCar(int unitId, int stationId, int x, int y) {
            super(unitId, stationId, UnitType.POLICE_CAR, x, y);
        }
    }


    // TODO: add fields (map, arrays for stations/units/incidents, counters, tick, etc.)

    //Grid
    private int width;
    private int height;
    private boolean[][] obstacles;

    //Storage for stations, units and incidents with arrays
    private static final int MAX_STATIONS = 20;
    private static final int MAX_UNITS = 50;
    private static final int MAX_INCIDENTS = 200;

    private station[] stations = new station[MAX_STATIONS];
    private int stationCount = 0;

    private Unit[] units = new Unit[MAX_UNITS];
    private int unitCount = 0;

    private Incident[] incidents = new Incident[MAX_INCIDENTS];
    private int incidentCount = 0;

    //ID Counters
    private int nextStationId = 1;
    private int nextUnitId = 1;
    private int nextIncidentId = 1;

    private int currentTick = 0;

    @Override
    public void initialise(int width, int height) throws InvalidGridException {
        if (width <= 0 || height <= 0) {
            throw new InvalidGridException("Must be in bounds");
        }

        this.width = width;
        this.height = height;
        obstacles = new boolean[width][height];

        stationCount = 0;
        unitCount = 0;
        incidentCount = 0;

        nextStationId = 1;
        nextUnitId = 1;
        nextIncidentId = 1;

        currentTick = 0;
        }
    

    @Override
    public int[] getGridSize() {
        return new int[]{width, height};
    }

    @Override
    public void addObstacle(int x, int y) throws InvalidLocationException {
        if (x<0||y<0||x>=width||y>=height) {
            throw new InvalidLocationException("Out of Bounds");
        }
        obstacles[x][y] = true;
    }

    @Override
    public void removeObstacle(int x, int y) throws InvalidLocationException {
        if (x < 0 || y <0 || x >=width || y >=height) {
            throw new InvalidLocationException("Out of Bounds");
        }
        obstacles[x][y] = false;
    }

    @Override
    public int addStation(String name, int x, int y) throws InvalidNameException, InvalidLocationException {
        if (name == null || name.isBlank()) {
            throw new InvalidNameException("Station name can't be blank");
        }

        if (x < 0 || y <0 || x >=width || y >=height) {
            throw new InvalidLocationException("Location out of bounds");
        }

        if (obstacles[x][y]) {
            throw new InvalidLocationException("Location blocked by obstacles");
        }

        if (stationCount >= MAX_STATIONS)
        {
            throw new CapacityExceededException("You cannot add more stations (max 20)")
        }

        Station station = new Station(nextStationId, name, x, y);
        stations[stationCount++] = station;

        return nextStationId++

    @Override
    public void removeStation(int stationId) throws IDNotRecognisedException, IllegalStateException {
        // Find station
        Station targetStation = null;
        for (Station s : stations) {
            if (s.stationId == stationId) {
                targetStation = s;
                break;
            }
        }
        if (targetStation == null) {
            throw new IllegalStateException("Station ID not recognised");
        }
        //Check it has no units
        if (!targetStation.unitIds.isEmpty()) {
            throw new IllegalStateException("Station still has units assigned");
        }

        // Remove station
        stations.remove(targetStation);
    }

    @Override
    public void setStationCapacity(int stationId, int maxUnits) throws IDNotRecognisedException, InvalidCapacityException {
        
        // Find station
        Station targetStation = null;
        for (Station s : stations) {
            if (s.stationId == stationId) {
                targetStation = s;
                break;
            }
        }
        if (targetStation == null) {
            throw new IllegalStateException("Station ID not recognised");
        }
        
        // Validate maxUnits
        if (maxUnits <= 0) {
            throw new IDNotRecognisedException("Capacity must be positive");
        }

        if (maxUnits < targetStation.unitIds.size()) {
            throw new InvalidCapacityException("Capacity can't be less than current number of units");
        }

        //Update capacity
        targetStation.maxUnits = maxUnits;
    }
    

    @Override
    public int[] getStationIds() {
        int[] ids = new int[stationCount]

        for (int i = 0; i < stationCount; i++) {
            ids[i] = stations[i].stationId;
        }

        //Ascending order
        Arrays.sort(ids);

        return ids;
    }


@Override
    public int addUnit(int stationId, UnitType type) throws IDNotRecognisedException, InvalidUnitException, IllegalStateException {
        
        // Validate unit type
        if (type == null) {
            throw new InvalidUnitException("Unit type can't be null");
        }
        if (unitCount >= MAX_UNITS) {
            throw new CapacityExceededException("Max number of units reached");
        }
        
        Station station = null;
        for (int i = 0; i < stationCount; i++) {
            if (station[i].stationId == stationId) {
                station = stations[i];
                break;
            }
        }
        if (station == null) {
            throw new IDNotRecognisedException("Station ID is unrecognisable"); 
        }
        if (!station.hasCapacity()) {
            throw new IllegalStateException("Station is full");
        }

        int id = nextUnitId;

        Unit newUnit;
        switch (type) {
            case AMBULANCE:
                newUnit = new Ambulance(id, stationId, station.y, station.x);
                break;
            case FIRE_ENGINE:
                newUnit = new FireEngine(id, stationId, station.y, station.x);
                break;
            case POLICE_CAR:
                newUnit = new PoliceCar(id, stationId, station.y, station.x);
                break;
            default:
                throw new InvalidUnitException("Invalid unit type");
        }

        //Check capacity
        units[unitCount++] = newUnit;
        station.addUnit(id);
    }

    @Override
    public void decommissionUnit(int unitId) throws IDNotRecognisedException, IllegalStateException {
        
        //Find Unit
        Unit unit = null;
        for (Unit u : units) {
            if (u.unitId == unitId) {
                unit = u;
                break;
            }
        }
        
        if (unit == null) {
            throw new IDNotRecognisedException("Unit ID not recognised");
        }

        //Check if unit is currently busy
        if (unit.assignedIncidentId != -1) {
            throw new IllegalStateException("Unit is currently assigned to an incident");
        }

        //Remove from its station
        for (Station s : stations) {
            if (s.stationId == unit.stationId) {
                s.unitIds.remove(Integer.valueOf(unitId));
                break;
            }
        }

         //Remove from system
        units.remove(unit);
    }

    @Override
    public void transferUnit(int unitId, int newStationId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void setUnitOutOfService(int unitId, boolean outOfService) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getUnitIds() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String viewUnit(int unitId) throws IDNotRecognisedException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int reportIncident(IncidentType type, int severity, int x, int y) throws InvalidSeverityException, InvalidLocationException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void cancelIncident(int incidentId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void escalateIncident(int incidentId, int newSeverity) throws IDNotRecognisedException, InvalidSeverityException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getIncidentIds() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String viewIncident(int incidentId) throws IDNotRecognisedException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void dispatch() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void tick() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public String getStatus() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
