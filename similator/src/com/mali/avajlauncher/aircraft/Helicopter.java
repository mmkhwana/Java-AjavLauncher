package com.mali.avajlauncher.aircraft;

import com.mali.avajlauncher.SimulationWriter;
import com.mali.avajlauncher.tower.Coordinates;
import com.mali.avajlauncher.tower.WeatherTower;

import java.io.IOException;

public class Helicopter extends Aircraft implements Flyables {
    private WeatherTower WTower;

    public Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() throws IOException {
        String weather = WTower.getWeather(this.coordinates);

        switch (weather) {
            case "SUN":
                SimulationWriter.write("Hellicopter#" + this.name + "(" + this.id + ") : " + "Its clear sky nice weather its Josh");
                break;

            case "RAIN":
                SimulationWriter.write("Hellicopter#" + this.name + "(" + this.id + ") : "+ "Its Rain cats and dogs here give me coordinates");
                break;

            case "FOG":
                SimulationWriter.write("Hellicopter#" + this.name + "(" + this.id + ") : "+ "The Fog here is making it difficult for me");
                break;

            case "SNOW":
                SimulationWriter.write("Hellicopter#" + this.name + "(" + this.id + ") : "+ "Santa is around the corner its snowing fellas");
                break;

            default:
                SimulationWriter.write("Hellicopter#" + this.name + "(" + this.id + ") : " + "weather condtions not detected mate");
                break;
        }

        if (weather.equals("SUN")) {
            this.coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 2);
        } else if (weather.equals("RAIN")) {
            this.coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 0);
        } else if (weather.equals("FOG")) {
            this.coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude() + 0,
                    coordinates.getHeight() + 0);
        } else if (weather.equals("SNOW")) {
            this.coordinates = new Coordinates(coordinates.getLongitude() + 0, coordinates.getLatitude() + 0,
                    coordinates.getHeight() - 12);
        }
        if (this.coordinates.getHeight() < 1) {
            SimulationWriter.write("Tower says: Hellicopter#" + this.name + " (" + this.id + ") : " + "Landed");
            this.WTower.unregister(this);
            SimulationWriter.write("Tower says: Hellicopter#" + this.name + " (" + this.id + ") : " + "unregistered to weather tower");
            
        }
    }

    public void registerTower(WeatherTower wTower) throws IOException {

        this.WTower = wTower;
        this.WTower.register(this);
        SimulationWriter.write("Tower says: Hellicopter#" + this.name + " (" + this.id + ") "+ "registered to weather tower");
    }
}
