public class Main {
    public static void main(String[] args) {
        ModelQ testCar = new ModelQ();

        System.out.println("Характеристики модели:");
        System.out.println("Ускорение: " + testCar.acceleration + " км/(ч*с)");
        System.out.println("Максимальная скорость: " + testCar.maxSpeed + " км/ч");

        System.out.println("\nНачало теста!");

        System.out.println("Едем на автопилоте:");
        for (int second = 0; second < 5; second++) {
            testCar.accelerateByAutopilot();
        }
        System.out.print("Скорость Q спустя 5с на автопилоте: " + testCar.speed + " км/ч");
        checkResult(55.0, testCar.speed);

        for (int second = 0; second < 5; second++) {
            testCar.accelerateByAutopilot();
        }
        System.out.print("Скорость Q спустя ещё 5с на автопилоте: " + testCar.speed + " км/ч");
        checkResult(60.0, testCar.speed);


        System.out.println("Переходим в ручной режим:");
        for (int second = 0; second < 2; second++) {
            testCar.accelerate();
        }
        System.out.print("Скорость Q спустя 2с в ручном режиме: " + testCar.speed + " км/ч");
        checkResult(260.0, testCar.speed);

        for (int second = 0; second < 2; second++) {
            testCar.accelerate();
        }
        System.out.print("Скорость Q спустя ещё 2с в ручном режиме: " + testCar.speed + " км/ч");
        checkResult(300.0, testCar.speed);


        System.out.println("Проверяем торможение:");
        int brakingTime = 0;
        while (testCar.speed > 0) {
            testCar.brake();
            brakingTime++;
        }
        System.out.print("Время торможения до полной остановки: " + brakingTime + "c");
        checkResult(3, brakingTime);
        System.out.print("Скорость Q: " + testCar.speed + " км/ч");
        checkResult(0.0, testCar.speed);
    }

    private static void checkResult(double expect, double actual) {
        if (expect == actual) {
            System.out.println(" ✅");
        } else {
            System.out.println(" ❌");
        }
    }

    private static void checkResult(int expect, int actual) {
        if (expect == actual) {
            System.out.println(" ✅");
        } else {
            System.out.println(" ❌");
        }
    }
}

class Automobile {
    protected double speed;
    protected double maxSpeed;
    protected double acceleration;
    protected double brakingSpeed;


    public Automobile() {
        acceleration = 30;
        brakingSpeed = 120;
    }

    public void accelerate() {
        if (speed < maxSpeed) {
            speed += acceleration;
        }
    }

    public void brake() {
        if (speed > 0) {
            speed -= brakingSpeed;
        }
    }
}

class Texla extends Automobile {
    protected double autoPilotMaxSpeed;
    protected double autoPilotAcceleration;

    public Texla() {
        autoPilotMaxSpeed = 60; // Максимальная скорость для автопилота
        autoPilotAcceleration = 11; // Ускорение для автопилота
    }

    public void accelerateByAutopilot() {
        if (speed < autoPilotMaxSpeed) {
            speed += autoPilotAcceleration;
            // Ограничиваем скорость до максимальной скорости автопилота
            if (speed > autoPilotMaxSpeed) {
                speed = autoPilotMaxSpeed; // Если превышаем, устанавливаем в maxSpeed
            }
        }
    }

    @Override
    public void accelerate() {
        if (speed < maxSpeed) {
            speed += acceleration;
            // Ограничиваем скорость до максимальной скорости автомобиля
            if (speed > maxSpeed) {
                speed = maxSpeed; // Если превышаем, устанавливаем в maxSpeed
            }
        }
    }

    @Override
    public void brake() {
        if (speed > 0) {
            speed -= brakingSpeed;
            // Не позволяем скорости стать отрицательной
            if (speed < 0) {
                speed = 0;
            }
        }
    }
}

class ModelQ extends Texla {
    public ModelQ() {
        this.acceleration = 100; // ускорение модели
        this.maxSpeed = 300; // макс скорость модели
        this.brakingSpeed = 120; // скорость торможения
    }
}