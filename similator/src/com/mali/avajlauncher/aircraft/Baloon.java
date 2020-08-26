package com.mali.avajlauncher.aircraft;

import com.mali.avajlauncher.SimulationWriter;
import com.mali.avajlauncher.tower.Coordinates;
import com.mali.avajlauncher.tower.WeatherTower;

import java.io.IOException;

public class Baloon extends Aircraft implements Flyables{
    private WeatherTower WTower;

    public Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() throws IOException {
        String weather = WTower.getWeather(this.coordinates);
        switch (weather) {
            case "SUN":
                SimulationWriter.write("Baloon#" + this.name + "(" + this.id + ")" + "Its clear sky nice weather its Josh");
                break;

            case "RAIN":
                SimulationWriter.write("Baloon#" + this.name + "(" + this.id + ")"+ "Its Rain cats and dogs here give me coordinates");
                break;

            case "FOG":
                SimulationWriter.write("Baloon#" + this.name + "(" + this.id + ")" + "The Fog here is making it difficult for me");
                break;

            case "SNOW":
                SimulationWriter.write("Baloon#" + this.name + "(" + this.id + ")" + "Santa is around the corner its snowing fellas");
                break;

            default:
                SimulationWriter.write("Baloon#" + this.name + "(" + this.id + ")" + "weather condtions not detected mate");
                break;
        }

        if (weather.equals("SUN")) {
            this.coordinates = new Coordinates(coordinates.getHeight() + 4, coordinates.getLatitude() + 0,
                    coordinates.getLongitude() + 2);
        } else if (weather.equals("RAIN")) {
            this.coordinates = new Coordinates(coordinates.getHeight() - 5, coordinates.getLatitude() + 0,
                    coordinates.getLongitude() + 0);
        } else if (weather.equals("FOG")) {
            this.coordinates = new Coordinates(coordinates.getHeight() - 3, coordinates.getLatitude() + 0,
                    coordinates.getLongitude() + 0);
        } else if (weather.equals("SNOW")) {
            this.coordinates = new Coordinates(coordinates.getHeight() - 15, coordinates.getLatitude() + 0,
                    coordinates.getLongitude() + 0);
        }
        if (this.coordinates.getHeight() == 0) {
            this.WTower.unregister(this);
            SimulationWriter.write("Tower says: Baloon#" + this.name + " (" + this.id + ") " + "unregistered to weather tower");
            SimulationWriter.write("Baloon#" + this.name + " (" + this.id + ") " + "Landed");
        }

    }

    public void registerTower(WeatherTower wTower) {
        this.WTower = wTower;
        this.WTower.register(this);
        try {
            SimulationWriter.write("Tower says: Baloon#" + this.name + " (" + this.id + ") " + "registered to weather tower");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
