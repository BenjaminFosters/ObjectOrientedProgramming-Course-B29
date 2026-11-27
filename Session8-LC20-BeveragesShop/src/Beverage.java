public class Beverage {
    private String id;
    private String name;
    private int price;
    private int category;

    public Beverage(String tempId, String tempName, int tempPrice, int tempCategory) {
        if (tempId.isEmpty()) {
            throw new IllegalArgumentException("ID must be supplied, this usually handled automatically.");
        }

        if (tempName.isEmpty()) {
            throw new IllegalArgumentException("Name has to have at minimum one character.");
        }
        if (tempPrice < 1) {
            throw new IllegalArgumentException("Price can't be lower than/equals to zero");
        }
        if (tempCategory < 1 || tempCategory > 3) {
            throw new IllegalArgumentException("Not your fault, it's developers.\nDear Developers... Refer to docu please, or I'll throw you to Pandaemonium, right now.");
        }

        id = tempId;
        name = tempName;
        price = tempPrice;
        category = tempCategory;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return switch (category) {
            case 1 -> "Coffee";
            case 2 -> "Tea";
            case 3 -> "Juice";
            default -> "That shouldn't be possible, unless you get a bit flip...";
        };
    }
}

class Coffee extends Beverage {
    private String beanType;

    public Coffee(String tempId, String tempName, int tempPrice, String tempBeanType) {
        if (tempBeanType.isEmpty()) {
            throw new IllegalArgumentException("Bean Type Name can't be empty.");
        }

        super(tempId, tempName, tempPrice, 1);
        beanType = tempBeanType;
    }

    public String getBeanType() {
        return beanType;
    }
}

class Tea extends Beverage {
    private String teaType;

    public Tea(String tempId, String tempName, int tempPrice, String tempTeaType) {
        if (tempTeaType.isEmpty()) {
            throw new IllegalArgumentException("Tea Type Name can't be empty.");
        }

        super(tempId, tempName, tempPrice, 2);
        teaType = tempTeaType;
    }

    public String getTeaType() {
        return teaType;
    }
}

class Juice extends Beverage {
    private String fruit;

    public Juice(String tempId, String tempName, int tempPrice, String tempFruit) {
        if (tempFruit.isEmpty()) {
            throw new IllegalArgumentException("Fruit Name can't be empty.");
        }

        super(tempId, tempName, tempPrice, 3);
        fruit = tempFruit;
    }

    public String getFruit() {
        return fruit;
    }
}