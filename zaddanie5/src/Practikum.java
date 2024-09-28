public class Practikum {
    public static void main(String[] args) {
        System.out.println("Готовим основу:");
        SoupBase soupBase = new SoupBase(2, 5);
        soupBase.printIngredients();

        System.out.println("\nИз основы приготовим вегетарианский суп и бульон:");
        Bouillon bouillon = new Bouillon(soupBase, 300);
        bouillon.printIngredients();
        VegetarianSoup vegetarianSoup = new VegetarianSoup(soupBase, 200);
        vegetarianSoup.printIngredients();

        System.out.println("\nПриготовим вегетарианский суп с нуля");
        VegetarianSoup vegetarianSoupWithoutBase = new VegetarianSoup(1.5, 5, 200);
        vegetarianSoupWithoutBase.printIngredients();

        System.out.println("\nПриготовим другой бульон для картофельного супа:");
        Bouillon anotherBouillon = new Bouillon(5, 10, 800);
        anotherBouillon.printIngredients();

        System.out.println("\nИз этого бульона приготовим суп с картошкой:");
        PotatoSoup potatoSoup = new PotatoSoup(anotherBouillon, 4, 1);
        potatoSoup.printIngredients();

        System.out.println("\nСуп с картошкой можно приготовить и из суповой основы:");
        PotatoSoup potatoSoupFromBase = new PotatoSoup(soupBase, 200, 3, 0);
        potatoSoupFromBase.printIngredients();

        System.out.println("\nА можно и совсем с нуля:");
        PotatoSoup totallyNewPotatoSoup = new PotatoSoup(5, 10, 400, 5, 1);
        totallyNewPotatoSoup.printIngredients();
    }
}

class SoupBase {
    protected double water;
    protected double salt;

    public SoupBase(double water, double salt) {
        this.water = water;
        this.salt = salt;
    }

    public void printIngredients() {
        System.out.println("Ингредиенты суповой основы:");
        System.out.println("Вода: " + water + " л.");
        System.out.println("Соль: " + salt + " г.");
    }
}

class Bouillon extends SoupBase {
    double meat;

    // Конструктор,  SoupBase
    public Bouillon(SoupBase base, double meat) {
        super(base.water, base.salt); // конст родителя
        this.meat = meat;
    }

    // Конструктор, принимающий все ингредиенты
    public Bouillon(double water, double salt, double meat) {
        super(water, salt); //конст родителя
        this.meat = meat;
    }

    @Override
    public void printIngredients() {
        System.out.println("Ингредиенты бульона:");
        System.out.println("Вода: " + water + " л.");
        System.out.println("Соль: " + salt + " г.");
        System.out.println("Мясо: " + meat + " г.");
    }
}

class VegetarianSoup extends SoupBase {
    double vegetables;

    // Конст SoupBase
    public VegetarianSoup(SoupBase base, double vegetables) {
        super(base.water, base.salt); // конст род
        this.vegetables = vegetables;
    }

    // Конструктор, принимающий все ингредиенты
    public VegetarianSoup(double water, double salt, double vegetables) {
        super(water, salt); // конст род
        this.vegetables = vegetables;
    }

    @Override
    public void printIngredients() {
        System.out.println("Ингредиенты вегетарианского супа:");
        System.out.println("Вода: " + water + " л.");
        System.out.println("Соль: " + salt + " г.");
        System.out.println("Овощи: " + vegetables + " г.");
    }
}

class PotatoSoup extends SoupBase {
    private double meat; // вес мяса в граммах
    private int potato;  // количество картофелины
    private int carrot;  // количество морковок

    // Конст 1
    public PotatoSoup(Bouillon bouillon, int potato, int carrot) {
        super(bouillon.water, bouillon.salt); //  родит класса
        this.meat = bouillon.meat;  // мясо из бульона

        this.potato = potato;
        this.carrot = carrot;
    }

    // Конст 2 (без мяса)
    public PotatoSoup(SoupBase base, double meat, int potato, int carrot) {
        super(base.water, base.salt);
        this.meat = meat;
        this.potato = potato;
        this.carrot = carrot;
    }

    // Конст 3 (если не передаем мясо, ставим 0)
    public PotatoSoup(double water, double salt, int potato, int carrot) {
        this(water, salt, 0, potato, carrot); // Передаем 0 для мяса
    }

    // Конст 4 (полный контроль)
    public PotatoSoup(double water, double salt, double meat, int potato, int carrot) {
        super(water, salt);  // конст род класса
        this.meat = meat;
        this.potato = potato;
        this.carrot = carrot;
    }

    @Override
    public void printIngredients() {
        System.out.println("Ингредиенты картофельного супа:");
        System.out.println("Вода: " + water + " л.");
        System.out.println("Соль: " + salt + " г.");
        System.out.println("Мясо: " + meat + " г.");
        System.out.println("Картошка: " + potato + " шт.");
        System.out.println("Морковь: " + carrot + " шт.");
    }
}