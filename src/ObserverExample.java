// examples taken from Head First Design Patterns

import java.util.ArrayList;
import java.util.List;

interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}

interface Observer {
    public void update(float temp, float humidity, float pressure);
}

interface DisplayElement {
    public void display();
}

class WeatherData implements Subject {
    private List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        this.observers = new ArrayList<>();
    }

    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    public void removeObserver(Observer o) {
        int i = this.observers.indexOf(o);
        if (i >= 0) {
            this.observers.remove(i);
        }
    }

    public void notifyObservers() {
        for (int i = 0; i < this.observers.size(); i++) {
            Observer observer = this.observers.get(i);
            observer.update(this.temperature, this.humidity, this.pressure);
        }
    }

    public void measurementsChanged(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }
}

class CurrentConditionsDisplay implements Observer, DisplayElement {
    // default values
    private float temperature = 0.0f;
    private float humidity = 0.0f;
    private Subject weatherData;

    public CurrentConditionsDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }

    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.println("Current conditions: " + this.temperature + "F degrees and " + this.humidity + "% humidity");
    }
}

class ThirdPartyDisplay implements Observer, DisplayElement {
    // default values
    private float temperature = 0.0f;
    private float humidity = 0.0f;
    private float pressure = 0.0f;
    private Subject weatherData;

    public ThirdPartyDisplay(Subject weatherData) {
        this.weatherData = weatherData;
        this.weatherData.registerObserver(this);
    }


    public void update(float temp, float humidity, float pressure) {
        this.temperature = temp;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    public void display() {
        // do other processing

        System.out.println("Third Party Display Current conditions: " + this.temperature + "F degrees and " + this.humidity + "% humidity and " + this.pressure + " atm standard atmosphere");
    }
}

public class ObserverExample {
    public static void main(String[] args) throws InterruptedException {
        WeatherData weatherData = new WeatherData();

        // before update
        CurrentConditionsDisplay currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);
        currentConditionsDisplay.display();

        // add more displays as needed
//        ThirdPartyDisplay thirdPartyDisplay = new ThirdPartyDisplay(weatherData);
//        thirdPartyDisplay.display();

        // after update
        System.out.println();
        System.out.print("Changing temp, humidity, and pressure");
        for (int i = 1; i <= 3; i++) {
            Thread.sleep(1000L);
            System.out.print(".");
        }
        Thread.sleep(1000L);
        System.out.println();

        weatherData.measurementsChanged(36.2f, 42.0f, 69.2f);
    }
}
