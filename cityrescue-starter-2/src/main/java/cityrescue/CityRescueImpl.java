package cityrescue;

import cityrescue.enums.*;
import cityrescue.exceptions.*;

import java.util.*;

/**
 * CityRescueImpl (Starter)
 *
 * Your task is to implement the full specification.
 * You may add additional classes in any package(s) you like.
 */
public class CityRescueImpl implements CityRescue {

    private static class Station{
        int stationId;
        String name;
        int x;
        int y;
        int maxUnits = 0;
        ArrayList<Integer> unitIds = new ArrayList<>();
    
        public Station(int stationId, String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.stationId = stationId;
        }
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

    // TODO: add fields (map, arrays for stations/units/incidents, counters, tick, etc.)

    //Grid
    private int width;
    private int height;
    private boolean[][] obstacles;

    //Storage
    private ArrayList<Station> stations = new ArrayList<>();
    private ArrayList<Unit> units = new ArrayList<>();
    private ArrayList<Incident> incidents = new ArrayList<>();

    //ID Counters
    private int nextStationId = 1;
    private int nextUnitID = 1;
    private int nextIncidentId = 1;

    private int currentTick = 0;

    @Override
    public void initialise(int width, int height) throws InvalidGridException {
        // TODO: implement
        if (width <= 0 || height <= 0) {
            this.width = width;
            this.height = height;
            obstacles = new boolean[width][height];
        }
    }

    @Override
    public int[] getGridSize() {
        // TODO: implement
        return new int[]{width, height};
    }

    @Override
    public void addObstacle(int x, int y) throws InvalidLocationException {
        // TODO: implement
        if (x<0||y<0||x>=width||y>=height) {
            obstacles[x][y] = true;
            throw new InvalidLocationException("Out of Bounds");
        }
    }

    @Override
    public void removeObstacle(int x, int y) throws InvalidLocationException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
        if (x<0||y<0||x>=width||y>=height) {
            obstacles[x][y] = false;
            throw new InvalidLocationException("Out of Bounds");
        }
    }

    @Override
    public int addStation(String name, int x, int y) throws InvalidNameException, InvalidLocationException {
        // TODO: implement
        if (name == null || name.isBlank()) {
            if
        }
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void removeStation(int stationId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void setStationCapacity(int stationId, int maxUnits) throws IDNotRecognisedException, InvalidCapacityException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int[] getStationIds() {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public int addUnit(int stationId, UnitType type) throws IDNotRecognisedException, InvalidUnitException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public void decommissionUnit(int unitId) throws IDNotRecognisedException, IllegalStateException {
        // TODO: implement
        throw new UnsupportedOperationException("Not implemented yet");
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
