import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Сергей on 14.05.2016.
 */
public class Main {
    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore();
        semaphore.getColorOfSemaphoreDepenOnMinut();
    }

}

class Semaphore {
    private double greenLightTime = 1;
    private double yellowLightTime = 3;
    private double redLightTime = 6;
    private double endCicle = 10;
    private double startCicle = 0;

    void getColorOfSemaphoreDepenOnMinut() {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {

                System.out.println("Введите минуту:");
                String zapros = reader.readLine();
                int minutWithoutSec = getOnlyMinut(zapros);
                if (minutWithoutSec < 0) {
                    System.out.println("Необходимо вводить положительно число");
                } else getNowColorofSemaphore(minutWithoutSec);

            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Введены недоспустимые символы!");
        }

    }

    private int getOnlyMinut(String zapros) throws NumberFormatException {
        double minutsWithSec = Double.parseDouble(zapros);
        int minutWithoutSec = (int) minutsWithSec;
        return minutWithoutSec % 10;
    }

    private void getNowColorofSemaphore(double askingMinuts) {

        if (askingMinuts >= greenLightTime && askingMinuts < yellowLightTime) System.out.println("горит зеленый свет");
        if (askingMinuts >= yellowLightTime && askingMinuts < redLightTime) System.out.println("горит желтый свет");
        if (askingMinuts >= redLightTime | askingMinuts <greenLightTime) System.out.println("горит красный свет");


    }


}
