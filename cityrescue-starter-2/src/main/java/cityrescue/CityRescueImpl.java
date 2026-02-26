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
        private final int stationId;
        private final String name;
        private final int x;
        private final int y;
        
        private int maxUnits = 0;
        private ArrayList<Integer> unitIds = new ArrayList<>();
    
        Station(int stationId, String name, int x, int y) {
            this.stationId = stationId;
            this.name = name;
            this.x = x;
            this.y = y;
            this.maxUnits = Integer.MAX_VALUE;
        }
        boolean hasCapacity() {
            return unitIds.size() < maxUnits;
        }
        void addUnit(int unitId) {
            unitIds.add(unitId);
        }
    }

    public class Unit{}
    public class Incident{}


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
    private int nextUnitId = 1;
    private int nextIncidentId = 1;

    private int currentTick = 0;

    @Override
    public void initialise(int width, int height) throws InvalidGridException {
        // TODO: implement
        if (width <= 0 || height <= 0) {
            throw new InvalidGridException("Must be in bounds");
        }

        this.width = width;
        this.height = height;
        obstacles = new boolean[width][height];

        stations.clear();
        units.clear();
        incidents.clear();

        nextStationId = 1;
        nextUnitId = 1;
        nextIncidentId = 1;

        currentTick = 0;
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
            throw new InvalidLocationException("Out of Bounds");
        }
        obstacles[x][y] = true;
    }

    @Override
    public void removeObstacle(int x, int y) throws InvalidLocationException {
        // TODO: implement
        if (x < 0 || y <0 || x >=width || y >=height) {
            obstacles[x][y] = false;
            throw new InvalidLocationException("Out of Bounds");
        }
        obstacles[x][y] = false;
    }

    @Override
    public int addStation(String name, int x, int y) throws InvalidNameException, InvalidLocationException {
        // TODO: implement
        if (name == null || name.isBlank()) {
            throw new InvalidNameException("Station name can't be blank");
        }

        if (x < 0 || y <0 || x >=width || y >=height) {
            throw new InvalidLocationException("Location out of bounds");
        }

        if (obstacles[x][y]) {
            throw new InvalidLocationException("Location blocked by obstacles");
        }

        final int MAX_STATIONS = 20;
        if (stations.size() >= MAX_STATIONS) {
            throw new IllegalStateException("Max number of stations reached");
        }

        Station station = new Station(nextStationId, name, x, y);
        station.maxUnits = Integer.MAX_VALUE;
        stations.add(station);
        
        return nextStationId++;
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
