package consoleMenu;

import java.util.HashMap;

public class FillerMenuContent {
 public HashMap<Integer, String> fill(String name) {
     HashMap<Integer, String> menuContent = new HashMap<>();
     switch(name) {
         case "Main":
             menuContent.put(1, "Developers");
             menuContent.put(2, "Projects");
             menuContent.put(3, "Companies");
             menuContent.put(4, "Customers");
             menuContent.put(10, "Create table");
             menuContent.put(11, "Quit");
             break;
         case "Developers":
             menuContent.put(1, "вывести всех разработчиков по фамилии");
             menuContent.put(2, "по фамилии разработчика вы получите по нему такие данные:\n" +
                     "\t\t- его полное ф.и.о.;\n" +
                     " \t\t- возраст;\n" +
                     " \t\t- языки программирования, на которых он работает;\n" +
                     " \t\t- уровень знания этих языков;\n" +
                     " \t\t- компания, в которой он работает;\n" +
                     " \t\t- его заработная плата;\n" +
                     " \t\t- в каких проектах участвует." );
             menuContent.put(3, "специальный вопрос - сколько всех Java - разработчиков");
             menuContent.put(4, "специальный вопрос - список всех middle - разработчиков");
             menuContent.put(5, "добавить разработчика");
             menuContent.put(6, "изменить данные разработчика");
             menuContent.put(7, "удалить разработчика");
             menuContent.put(8, "перейти в верхнее меню");
             break;
         case "Projects":
             menuContent.put(1, "вывести все проекты  по названию");
             menuContent.put(2, "по названию проекта вы получите по нему такие данные:\n" +
                     "\t\t- его заказчика;\n" +
                     " \t\t- стоимость проекта;\n" +
                     " \t\t- дата его старта;\n" +
                     " \t\t- компания, которая его разрабатывает.");
             menuContent.put(3, "специальный вопрос -  список всех разработчиков конкретного проекта");
             menuContent.put(4, "специальный вопрос -  затратная часть конкретного проекта (сумму зарплат всех его разработчиков)");
             menuContent.put(5, "специальный вопрос -  список проектов в следующем формате:\n " +
                     "\t\tдата создания - название проекта - количество разработчиков на этом проекте");
             menuContent.put(6, "добавить проект");
             menuContent.put(7, "изменить данные проекта");
             menuContent.put(8, "удалить проект");
             menuContent.put(9, "перейти в верхнее меню");
             break;
         case "Companies":
             menuContent.put(1, "вывести все компании с указанием:\n" +
                     "\t\t- их рейтинга,\n" +
                     "\t\t- количества разработчиков, которые в ней работают");
             menuContent.put(2, "добавить компанию");
             menuContent.put(3, "изменить данные компании");
             menuContent.put(4, "удалить компанию");
             menuContent.put(5, "перейти в верхнее меню");
             break;
         case "Customers":
             menuContent.put(1, "вывести всех заказчиков с указанием:\n" +
                     "\t\t- его репутации,\n" +
                     "\t\t- перечня всех проектов, которые он заказал на разработку ");
             menuContent.put(2, "добавить заказчика");
             menuContent.put(3, "изменить данные заказчика");
             menuContent.put(4, "удалить заказчика");
             menuContent.put(5, "перейти в верхнее меню");
     }
     return menuContent;
 }
}
