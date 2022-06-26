import consoleMenuService.*;
import storage.Storage;
import tables.*;

import java.sql.SQLException;
import java.util.ArrayList;
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
                                developerDaoService.getAllNames();
                                break;
                            case 2:
                                System.out.print("\tВведите фамилию : ");
                                Scanner sc12 = new Scanner(System.in);
                                String lastNameInput2 = sc12.nextLine();
                                System.out.print("\tВведите имя : ");
                                String firstNameInput2 = sc12.nextLine();
                                developerDaoService.getInfoByName(lastNameInput2, firstNameInput2);
                                break;
                            case 3:
                                developerDaoService.getQuantityJavaDevelopers();
                                break;
                            case 4:
                                developerDaoService.getListMiddleDevelopers();
                                break;
                            case 5:
                                System.out.println("\tВведите, пожалуйста следующие данные по разработчику");
                                Scanner sc15 = new Scanner(System.in);
                                System.out.print("\tВведите фамилию : ");
                                String lastNameInput5 = sc15.nextLine();
                                System.out.print("\tВведите имя : ");
                                String firstNameInput5 = sc15.nextLine();
                                int add = developerDaoService.addDeveloper(lastNameInput5, firstNameInput5);

                                break;
                            case 6:
                                System.out.println("Для внесения изменения хоть в одно поле данных необходимо обновить все поля");
                                System.out.println("Данные по какому разработчику вы планируете изменить?");
                                Scanner sc16 = new Scanner(System.in);
                                System.out.print("Введите фамилию : ");
                                String lastNameInput6 = sc16.nextLine();
                                System.out.print("Введите имя : ");
                                String firstNameInput6 = sc16.nextLine();
                                long idToDelete;
                                try {
                                    idToDelete = developerDaoService.getIdByName(lastNameInput6, firstNameInput6);
                                }  catch (SQLException e) {
                                    System.out.println("В базе данных такого разработчика не существует. Вводите корректные данные.");
                                    break;
                                }
                                developerDaoService.deleteDeveloper(idToDelete);
                                int update = developerDaoService.addDeveloper(lastNameInput6, firstNameInput6);
                                break;
                            case 7:
                                System.out.println("Внесите данные по разработчику, которого вы хотите удалить");
                                Scanner sc17 = new Scanner(System.in);
                                System.out.print("Введите фамилию : ");
                                String lastNameInput7 = sc17.nextLine();
                                System.out.print("Введите имя : ");
                                String firstNameInput7 = sc17.nextLine();
                                developerDaoService.deleteDeveloper(lastNameInput7, firstNameInput7);
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
                                Scanner sc22 = new Scanner(System.in);
                                String projectNameInput2 = sc22.nextLine();
                                projectDaoService.getInfoByName(projectNameInput2);
                                break;
                            case 3:
                                System.out.print("Введите название проекта : ");
                                Scanner sc23 = new Scanner(System.in);
                                String projectNameInput3 = sc23.nextLine();
                                System.out.println("\tВ проекте " + projectNameInput3 + " задействованы следующие разработчики: ");
                                projectDaoService.getListDevelopers(projectNameInput3);
                                break;
                            case 4:
                                System.out.print("Введите название проекта : ");
                                Scanner sc24 = new Scanner(System.in);
                                String projectNameInput4 = sc24.nextLine();
                                projectDaoService.getBudgetByProjectName(projectNameInput4);
                                break;
                            case 5:
                                projectDaoService.getProjectsListInSpecialFormat();
                                break;
                            case 6:
                                System.out.print("Введите название проекта : ");
                                Scanner sc26 = new Scanner(System.in);
                                String projectNameInput6 = sc26.nextLine();
                                int add = projectDaoService.addProject(projectNameInput6);
                                break;
                            case 7:
                                System.out.println("Для внесения изменения хоть в одно поле данных необходимо обновить все поля");
                                System.out.print("Введите название проекта, данные по которому вы планируете изменить: ");
                                Scanner sc27 = new Scanner(System.in);
                                String nameInput7 = sc27.nextLine();
                                long idToDelete;
                                try {
                                    idToDelete = projectDaoService.getIdByName(nameInput7);
                                }  catch (SQLException e) {
                                    System.out.println("В базе данных такого проекта не существует. Вводите корректные данные.");
                                    break;
                                }
                                projectDaoService.deleteProject(idToDelete);
                                int update = projectDaoService.addProject(nameInput7);
                                break;
                            case 8:
                                System.out.print("Внесите название проекта, которого вы хотите удалить :");
                                Scanner sc28 = new Scanner(System.in);
                                String nameInput8 = sc28.nextLine();
                                projectDaoService.deleteProject(nameInput8);
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
                                companyDaoService.addCompany();
                                break;
                            case 3:
                                System.out.print("Внесите название компании, которую вы хотите удалить :");
                                Scanner sc33 = new Scanner(System.in);
                                String nameInput3 = sc33.nextLine();
                                ArrayList<String> companyProjects = new CompanyDaoService(Storage.getInstance().getConnection()).getCompanyProjects(nameInput3);
                                if (companyProjects.size() == 0) {
                                    companyDaoService.deleteCompany(nameInput3);
                                    break;}
                                System.out.println("\tЭта компания разрабатывает следующие проекты:");
                                for (String project : companyProjects) {
                                    System.out.println("\t\t- " + project + ", в котором задействованы следующие разработчики:");
                                    new ProjectDaoService(Storage.getInstance().getConnection()).getListDevelopers(project);
                                }
                                System.out.println("Для того, чтобы удалить эту компанию - внесите ссответствующие изменения в таблицы developers, projects");
                                break;
                        }
                    } while (choiceCompanies != 4);
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
                                customerDaoService.addCustomer();
                                break;
                            case 3:
                                System.out.print("Внесите название заказчика, которого вы хотите удалить :");
                                Scanner sc43 = new Scanner(System.in);
                                String nameInput3 = sc43.nextLine();
                                ArrayList<String> customerProjects = customerDaoService.getProjectsNames(nameInput3);
                                if (customerProjects.size() == 0) {
                                    customerDaoService.deleteCustomer(nameInput3);
                                    break;}
                                System.out.println("\tЭтот заказчик заказал следующие проекты:");
                                for (String project : customerDaoService.getProjectsNames(nameInput3)) {
                                    System.out.println("\t\t- " + project );
                                }
                                System.out.println("Для того, чтобы удалить этого заказчика - внесите ссответствующие изменения в таблицу projects");
                                break;
                        }
                    } while (choiceCustomers != 4);
                    break;
            }
        } while (choice != 5);
    }
}