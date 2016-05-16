import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Сергей on 14.05.2016.
 */
public class Main {
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore();
        semaphore.getColorOfSemaphoreInsteadOfMinut();
    }

}

class Semaphore {
    private int greenLightTime = 2;
    private int redLightTime = 3;
    private int yellowLightTime = 4;

    void getColorOfSemaphoreInsteadOfMinut() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {

                System.out.println("Введите минуту:");
                String zapros = reader.readLine();

                double minute = getAllSecund(zapros);
                if (minute < 0) {
                    System.out.println("Необходимо вводить положительно число");
                } else getNowColorofSemaphore(minute);

            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Введены недоспустимые символы!");
        }

    }

    private double getAllSecund(String setMinut) throws NumberFormatException {

        int minut = Integer.parseInt(setMinut) % 10;

        return minut + 0.001;

    }

    private void getNowColorofSemaphore(double AllSecunds) {
        if (AllSecunds > 0 && AllSecunds < greenLightTime) System.out.println(Color.GREEN.displyedColor());
        if (AllSecunds > greenLightTime && AllSecunds < redLightTime) System.out.println(Color.RED.displyedColor());
        if (AllSecunds > yellowLightTime && AllSecunds < 10) System.out.println(Color.Yellow.displyedColor());
    }


    public void setGreenLightTime(int greenLightTime) {
        this.greenLightTime = greenLightTime;
    }

    public void setRedLightTime(int redLightTime) {
        this.redLightTime = redLightTime;
    }

    public void setYellowLightTime(int yellowLightTime) {
        this.yellowLightTime = yellowLightTime;
    }
}

enum Color {
    RED(" горит красный свет"), GREEN(" горит зеленый свет"), Yellow(" горит желтый свет");
    private String color;

    String displyedColor() {
        return color;
    }

    Color(String displyedColor) {
        this.color = displyedColor;
    }
}


