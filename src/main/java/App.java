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
                                Scanner sc2 = new Scanner(System.in);
                                String lastNameInput2 = sc2.nextLine();
                                developerDaoService.getInfoByLastName(lastNameInput2);
                                developerDaoService.getSkillsByLastName(lastNameInput2);
                                developerDaoService.getProjectsByLastName(lastNameInput2);
                                break;
                            case 3:
                                developerDaoService.getQuantityJavaDevelopers();
                                break;
                            case 4:
                                developerDaoService.getListMiddleDevelopers();
                                break;
                            case 5:
                                System.out.println("\tВведите, пожалуйста следующие данные по разработчику");
                                Scanner sc5 = new Scanner(System.in);
                                System.out.print("\tВведите фамилию : ");
                                String lastNameInput5 = sc5.nextLine();
                                System.out.print("\tВведите имя : ");
                                String firstNameInput5 = sc5.nextLine();
                                int add = developerDaoService.addDeveloper(lastNameInput5, firstNameInput5);

                                break;
                            case 6:
                                System.out.println("Для внесения изменения хоть в одно поле данных необходимо обновить все поля");
                                System.out.println("Данные по какому разработчику вы планируете изменить?");
                                Scanner sc6 = new Scanner(System.in);
                                System.out.print("Введите фамилию : ");
                                String lastNameInput6 = sc6.nextLine();
                                System.out.print("Введите имя : ");
                                String firstNameInput6 = sc6.nextLine();
                                long idToDelete;
                                try {
                                    idToDelete = developerDaoService.getIdByLastNameAndFirstName(lastNameInput6, firstNameInput6);
                                }  catch (SQLException e) {
                                    System.out.println("В базе данных такого разработчика не существует. Вводите корректные данные.");
                                    break;
                                }
                                developerDaoService.deleteDeveloper(idToDelete);
                                int update = developerDaoService.addDeveloper(lastNameInput6, firstNameInput6);
                                break;
                            case 7:
                                System.out.println("Внесите данные по разработчику, которого вы хотите удалить");
                                Scanner sc7 = new Scanner(System.in);
                                System.out.print("Введите фамилию : ");
                                String lastNameInput7 = sc7.nextLine();
                                System.out.print("Введите имя : ");
                                String firstNameInput7 = sc7.nextLine();
                                developerDaoService.deleteDeveloper(lastNameInput7, firstNameInput7);
                                System.out.println("\tРазработчик успешно удален из базы данных.");
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
                                projectDaoService.getListDevelopers(projectNameInput3);
                                break;
                            case 4:
                                System.out.print("Введите название проекта : ");
                                Scanner sc4 = new Scanner(System.in);
                                String projectNameInput4 = sc4.nextLine();
                                projectDaoService.getBudgetByProjectName(projectNameInput4);
                                break;
                            case 5:
                                projectDaoService.getProjectsListInSpecialFormat();
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
            }
        } while (choice != 5);
    }
}