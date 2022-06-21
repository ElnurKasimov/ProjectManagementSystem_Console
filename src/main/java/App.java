import consoleMenuService.*;
import storage.Storage;
import tables.*;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws SQLException, InterruptedException {

        MenuService menuService = new MenuService();
        menuService.create();

        Storage storage = Storage.getInstance();
        DeveloperDaoService developerDaoService = new DeveloperDaoService(storage.getConnection());
        ProjectDaoService projectDaoService = new ProjectDaoService(storage.getConnection());
        CompanyDaoService companyDaoService = new CompanyDaoService(storage.getConnection());
        CustomerDaoService customerDaoService = new CustomerDaoService(storage.getConnection());

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
                                System.out.print("Введите фамилию : ");
                                Scanner sc = new Scanner(System.in);
                                String lastNameInput = sc.nextLine();
                                developerDaoService.getInfoByLastName(lastNameInput);
                                developerDaoService.getSkillsByLastName(lastNameInput);
                                developerDaoService.getProjectsByLastName(lastNameInput);

                                break;
                            case 3:
                                developerDaoService.getQuantityJavaDevelopers();
                                break;
                            case 4:
                                developerDaoService.getListMiddleDevelopers();
                                break;
                        }
                    } while (choiceDevelopers != 8);
                    break;
                case 2:
                    int choiceProjects;
                    do {
                        menuService.get("Projects").printMenu();
                        choiceProjects = menuService.get("Projects").makeChoice();
                        switch (choiceProjects) {

                            case 1:
                                projectDaoService.getAllNames();
                                break;
                            case 2:
                                System.out.print("Введите название проекта : ");
                                Scanner sc2 = new Scanner(System.in);
                                String projectNameInput2 = sc2.nextLine();
                                projectDaoService.getInfoByName(projectNameInput2);
                                break;
                            case 3:
                                System.out.print("Введите название проекта : ");
                                Scanner sc3 = new Scanner(System.in);
                                String projectNameInput3 = sc3.nextLine();
                                projectDaoService.getQuantityDevelopers(projectNameInput3);
                                break;
                            case 4:
                                System.out.print("Введите название проекта : ");
                                Scanner sc4 = new Scanner(System.in);
                                String projectNameInput4 = sc4.nextLine();
                                projectDaoService.getBudgetByProjectName(projectNameInput4);
                                break;
                        }
                    } while (choiceProjects != 9);
                    break;
                case 3:
                    int choiceCompanies;
                    do {
                        menuService.get("Companies").printMenu();
                        choiceCompanies = menuService.get("Companies").makeChoice();
                        switch (choiceCompanies) {
                            case 1:
                                companyDaoService.getAllNames();
                                break;
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
                    int choiceCustomers;
                    do {
                        menuService.get("Customers").printMenu();
                        choiceCustomers = menuService.get("Customers").makeChoice();
                        switch (choiceCustomers) {
                            case 1:
                                customerDaoService.getAllNames();
                                break;
                            case 2:
                                System.out.println("Вы выбрали 2. Что хотите еще?");
                                break;
                            case 3:
                                System.out.println("Вы выбрали 3. Что хотите еще?");
                                break;
                            case 4:
                                System.out.println("Вы выбрали 4. Что хотите еще?");
                        }
                    } while (choiceCustomers != 5);
                    break;
                case 10:
                    System.out.println("Вы выбрали 10. Что хотите еще?");
            }
        } while (choice != 11);
    }


}