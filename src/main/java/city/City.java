package city;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String name;
    private long fullArea;
    private List<Building> buildings = new ArrayList<>();

    public City(String name, long fullArea) {
        this.name = name;
        this.fullArea = fullArea;
    }

    public void addBuilding(Building building) {
        if (building == null) {
            throw new IllegalArgumentException("Building value can't be null!");
        }

        if (countBuiltArea() + building.getArea() > fullArea) {
            throw new IllegalArgumentException("City can't be larger than " + fullArea);
        } else {
            buildings.add(building);
        }
    }

    private long countBuiltArea() {
        long area = 0;
        for (Building building : buildings) {
            area += building.getArea();
        }
        return area;
    }

    public String getName() {
        return name;
    }

    public long getFullArea() {
        return fullArea;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public Building findHighestBuilding() {
        validateBuildingsList();

        Building highest = buildings.get(0);
        for (Building building : buildings) {
            if (building.getLevels() > highest.getLevels()) {
                highest = building;
            }
        }
        return highest;
    }

    public List<Building> findBuildingsByStreet(String street) {
        validateBuildingsList();

        if (street == null || street.isEmpty()) {
            throw new IllegalArgumentException("There nothing to find, street name can't be empty!");
        }

        List<Building> filteredBuildings = new ArrayList<>();

        for (Building building : buildings) {
            if (building.getAddress().getStreet().equals(street)) {
                filteredBuildings.add(building);
            }
        }
        return filteredBuildings;
    }

    public boolean isThereBuildingWithMorePeopleThan(int numberOfPeople) {
        validateBuildingsList();

        if (numberOfPeople < 1) {
            throw new IllegalArgumentException("The number of of people can't be zero, or negative!");
        }

        for (Building building : buildings) {
            if (building.calculateNumberOfPeopleCanFit() > numberOfPeople) {
                return true;
            }
        }
        return false;
    }

    private void validateBuildingsList() {
        if (buildings == null || buildings.isEmpty()) {
            throw new IllegalArgumentException("There are no buildings on the list!");
        }
    }
}
