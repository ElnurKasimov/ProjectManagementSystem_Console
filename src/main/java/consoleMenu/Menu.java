package consoleMenu;

import lombok.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


@Data
public class Menu {
    private String name;
    private HashMap<Integer, String> contentMenu;

    public Menu  (String name) {
        this.name = name;
    }

    public void printMenu () {
        if(name.equals("Main")) {System.out.println("В данной БД вы можете работать с такими таблицами:");}
        else {System.out.println("В данной таблице вы можете работать с такими данными:");}
        for (Map.Entry<Integer, String> element : contentMenu.entrySet()) {
            System.out.println(element.getKey() + " - " + element.getValue());
        }
        System.out.print("Сделайте свой выбор (1,2,3 и т.д.) : ");
    }

    public int makeChoice() {
        int result = 0;
        boolean isRightChoice = true;
        Scanner sc = new Scanner(System.in);
        do {
            result = sc.nextInt();
            isRightChoice = checkChoice(result);
        } while (!isRightChoice);
        return result;
    }

    public boolean checkChoice(int actualChoice) {
        boolean result = false;
        for (Map.Entry<Integer, String> element : getContentMenu().entrySet()) {
            if (element.getKey() == actualChoice) {
                result = true;
            }
        }
        if (result) return result;
        else {
            System.out.print("Вы сделали невозможный выбор, пожалуйста повторите :");
            return result;
        }
    }

/*
    public Object createElement();
    public Object deleteElement();
*/

}
