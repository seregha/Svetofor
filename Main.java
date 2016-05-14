import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Сергей on 14.05.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        try {
            System.out.println("Введите  число равное длительности включения зелленого света в минутах");

            int greenTime =10;// Integer.parseInt(reader.readLine()) * 10;


            System.out.println("Введите  число равное длительности красного света в минутах");
            int redTime = 3; //Integer.parseInt(reader.readLine()) * 10;
            System.out.println("Введите  число равное длительности желтого света в минутах");
            int yellowTime =0;// Integer.parseInt(reader.readLine()) * 10;


            Svetofor svetofor = SvetoforBuilder.svetoforBuild(greenTime, redTime, yellowTime);

            svetofor.On();
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели недопустимый символ");
        }
    }
}

class Light {
    private int lightTime;
    private Color colorLight;


    public void setLightTime(int lightTime) {
        this.lightTime = lightTime;
    }


    public void setColorLight(Color colorLight) {
        this.colorLight = colorLight;
    }

    void oN() {
        for (int i = lightTime; i > 0; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Сейчас горит " + colorLight + ", осталось " + i + " секунд");
        }
    }
}

enum Color {
    RED, GREEN, Yellow, NonColor;
}

class Svetofor {
    public Svetofor(Light green, Light red, Light yellow) {
        this.green = green;
        this.red = red;
        this.yellow = yellow;

    }

    Light green;
    Light red;
    Light yellow;

    void On() {
        green.oN();
        red.oN();
        yellow.oN();
    }

}

class SvetoforBuilder {


    static Svetofor svetoforBuild(int green, int red, int yellow) {


        Light lightGreen = new LightBuilder()
                .lightBuilder(Color.GREEN)
                .timeBuilfer(green)
                .lightBuild();

        Light lightRed = new LightBuilder()
                .lightBuilder(Color.RED)
                .timeBuilfer(red)
                .lightBuild();
        Light lightYellow = new LightBuilder()
                .lightBuilder(Color.Yellow)
                .timeBuilfer(yellow)
                .lightBuild();
        Svetofor svetofor = new Svetofor(lightGreen, lightRed, lightYellow);


        return svetofor;
    }


}

class LightBuilder {
    private int lightTime;
    private Color colorLight = Color.NonColor;

    LightBuilder timeBuilfer(int time) {
        this.lightTime = time;

        return this;
    }

    LightBuilder lightBuilder(Color color) {
        this.colorLight = color;
        return this;

    }

    Light lightBuild() {
        Light light = new Light();
        light.setColorLight(colorLight);
        light.setLightTime(lightTime);
        return light;
    }

}

