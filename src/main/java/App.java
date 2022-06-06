import consoleMenu.*;

import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        FillerMenuContent fillerMenuContent = new FillerMenuContent();
        Menu mainMenu = new Menu("Main");
        mainMenu.setContentMenu(fillerMenuContent.fill("Main"));
        Menu developersMenu = new Menu("Developers");
        developersMenu.setContentMenu(fillerMenuContent.fill("Developers"));
        Menu projectsMenu = new Menu("Projects");
        projectsMenu.setContentMenu(fillerMenuContent.fill("Projects"));
        Menu companiesMenu = new Menu("Companies");
        companiesMenu.setContentMenu(fillerMenuContent.fill("Companies"));
        Menu customersMenu = new Menu("Customers");
        customersMenu.setContentMenu(fillerMenuContent.fill("Customers"));
        int choice;
        do {
            mainMenu.printMenu();
            choice = mainMenu.makeChoice();
            switch (choice) {
                case 1:
                    int choiceDevelopers;
                    do {
                        developersMenu.printMenu();
                        choiceDevelopers = developersMenu.makeChoice();
                        switch (choiceDevelopers) {
                            case 2:
                                System.out.println("Вы выбрали 2. Что хотите еще?");
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
                        projectsMenu.printMenu();
                        choiceProjects = projectsMenu.makeChoice();
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
                        companiesMenu.printMenu();
                        choiceCompanies = companiesMenu.makeChoice();
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
                        customersMenu.printMenu();
                        choiceCastomers = customersMenu.makeChoice();
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