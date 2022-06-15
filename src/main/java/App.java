import consoleMenuService.*;
import storage.Storage;
import tables.DeveloperDaoService;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws SQLException, InterruptedException {

        MenuService menuService = new MenuService();
        menuService.create();

        Storage storage = Storage.getInstance();
        DeveloperDaoService developerDaoService = new DeveloperDaoService(storage.getConnection());

        int choice;
        do {
            menuService.get("Main").printMenu();
            choice = menuService.get("Main").makeChoice();
            switch (choice) {
                case 1:
                    int choiceDevelopers;
                    do {
                        menuService.get("Developers").printMenu();
                        choiceDevelopers = menuService.get("Developers").makeChoice();
                        switch (choiceDevelopers) {
                            case 1:
                                developerDaoService.getAllLastNames();
                                break;
                            case 2:
                                developerDaoService.getInfoByLastName();
                                break;
                            case 3:
                                System.out.println("Вы выбрали 3. Что хотите еще?");
                                break;
                            case 4:
                                System.out.println("Вы выбрали 4. Что хотите еще?");
                        }
                    } while (choiceDevelopers != 8);
                    break;
                case 2:
                    int choiceProjects;
                    do {
                        menuService.get("Projects").printMenu();
                        choiceProjects = menuService.get("Projects").makeChoice();
                        switch (choiceProjects) {
                            case 2:
                                System.out.println("Вы выбрали 2. Что хотите еще?");
                                break;
                            case 3:
                                System.out.println("Вы выбрали 3. Что хотите еще?");
                                break;
                            case 4:
                                System.out.println("Вы выбрали 4. Что хотите еще?");
                        }
                    } while (choiceProjects != 9);
                    break;
                case 3:
                    int choiceCompanies;
                    do {
                        menuService.get("Companies").printMenu();
                        choiceCompanies = menuService.get("Companies").makeChoice();
                        switch (choiceCompanies) {
                            case 2:
                                System.out.println("Вы выбрали 2. Что хотите еще?");
                                break;
                            case 3:
                                System.out.println("Вы выбрали 3. Что хотите еще?");
                                break;
                            case 4:
                                System.out.println("Вы выбрали 4. Что хотите еще?");
                        }
                    } while (choiceCompanies != 5);
                    break;
                case 4:
                    int choiceCastomers;
                    do {
                        menuService.get("Customers").printMenu();
                        choiceCastomers = menuService.get("Customers").makeChoice();
                        switch (choiceCastomers) {
                            case 2:
                                System.out.println("Вы выбрали 2. Что хотите еще?");
                                break;
                            case 3:
                                System.out.println("Вы выбрали 3. Что хотите еще?");
                                break;
                            case 4:
                                System.out.println("Вы выбрали 4. Что хотите еще?");
                        }
                    } while (choiceCastomers != 5);
                    break;
                case 10:
                    System.out.println("Вы выбрали 10. Что хотите еще?");
            }
        } while (choice != 11);
    }


}