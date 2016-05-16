import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Сергей on 14.05.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Введите длительность включения каждого цвета светофора  в цифрах.\n" +
                "Если длтельность введена неправильно, программа выполнится со значениями по умолчанию.");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


        try {
            System.out.println("Введите  число равное длительности включения зелленого света в минутах:");

            int greenTime = Integer.parseInt(reader.readLine()) * 60;


            System.out.println("Введите  число равное длительности красного света в минутах:");
            int redTime = Integer.parseInt(reader.readLine()) * 60;
            System.out.println("Введите  число равное длительности желтого света в минутах:");
            int yellowTime = Integer.parseInt(reader.readLine()) * 60;


            Svetofor svetofor = SvetoforBuilder.svetoforBuild(greenTime, redTime, yellowTime);

            svetofor.On();
        } catch (NumberFormatException e) {
            System.out.println("Вы ввели недопустимые данные. Будут применены параметры по умолчанию");

            Svetofor svetofor = SvetoforBuilder.defaultSvetoforBuild();
            svetofor.On();
        }
        finally {
            reader.close();
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
            System.out.println("Сейчас горит " + colorLight.displyedColor() + ", осталось " + i + " секунд");
        }
    }
}


enum Color {
    RED("красный свет"), GREEN("зеленый свет"), Yellow("желтый свет");
    private String color;

    String displyedColor() {
        return color;

    }

    Color(String displyedColor) {
        this.color = displyedColor;
    }
}

class Svetofor {
    Light green;
    Light red;
    Light yellow;


    public Svetofor(Light green, Light red, Light yellow) {
        this.green = green;
        this.red = red;
        this.yellow = yellow;

    }


    void On() {
        green.oN();
        red.oN();
        yellow.oN();
    }

}

class SvetoforBuilder {
    static Svetofor svetofor;

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
         svetofor= new Svetofor(lightGreen, lightRed, lightYellow);


        return svetofor;
    }

    static Svetofor defaultSvetoforBuild() {
        Light lightGreen = new LightBuilder()
                .lightBuilder(Color.GREEN)
                .timeBuilfer(120)
                .lightBuild();

        Light lightRed = new LightBuilder()
                .lightBuilder(Color.RED)
                .timeBuilfer(300)
                .lightBuild();
        Light lightYellow = new LightBuilder()
                .lightBuilder(Color.Yellow)
                .timeBuilfer(180)
                .lightBuild();
         svetofor = new Svetofor(lightGreen, lightRed, lightYellow);


        return svetofor;
    }

}


class LightBuilder {
    private int lightTime;
    private Color colorLight;

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

