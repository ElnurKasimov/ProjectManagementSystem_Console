package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoService {
    private List<Project> projects = new ArrayList<>();

    private PreparedStatement getAllInfoSt;
    private PreparedStatement getAllNamesSt;
    private PreparedStatement getCompanyNameByProjectNameSt;
    private PreparedStatement getCustomerNameByProjectNameSt;
    private PreparedStatement getCostDateSt;
    private PreparedStatement getListDevelopersSt;
    private PreparedStatement getQuantityDevelopersByProjectNameSt;
    private PreparedStatement getBudgetByProjectNameSt;
    private PreparedStatement getIdProjectByNameSt;


    public ProjectDaoService(Connection connection) throws SQLException {
        PreparedStatement getAllInfoSt = connection.prepareStatement(
                "SELECT * FROM projects"
        );
        try (ResultSet rs = getAllInfoSt.executeQuery()) {
            while (rs.next()) {
                Project project = new Project();
                project.setProject_id(rs.getLong("project_id"));
                project.setProject_name(rs.getString("project_name"));
                project.setCompany_id(rs.getLong("company_id"));
                project.setCustomer_id(rs.getLong("customer_id"));
                project.setCost(rs.getInt("cost"));
                String startDate = rs.getString("start_date");
                if (startDate != null) {
                    project.setStart_date(LocalDate.parse(startDate));
                }
                projects.add(project);
            }
        }

        getAllNamesSt = connection.prepareStatement(
                " SELECT project_id, project_name, start_date FROM projects"
        );

        getCompanyNameByProjectNameSt = connection.prepareStatement(
                " SELECT company_name FROM projects JOIN companies " +
                        "ON projects.company_id = companies.company_id " +
                        " WHERE  project_name LIKE   ?"
        );

        getCustomerNameByProjectNameSt = connection.prepareStatement(
                " SELECT customer_name FROM projects JOIN customers " +
                        "ON projects.customer_id = customers.customer_id " +
                        " WHERE  project_name LIKE   ?"
        );

        getCostDateSt = connection.prepareStatement(
                " SELECT cost, start_date FROM projects " +
                        " WHERE  project_name LIKE   ?"
        );

        getListDevelopersSt = connection.prepareStatement(
                "SELECT lastName, firstName FROM projects JOIN projects_developers " +
                "ON projects.project_id = projects_developers.project_id " +
                "JOIN developers ON projects_developers.developer_id = developers.developer_id " +
                "WHERE project_name  LIKE ?"
        );

        getQuantityDevelopersByProjectNameSt = connection.prepareStatement(
                "SELECT COUNT(developer_id) FROM projects JOIN projects_developers " +
                        "ON projects.project_id = projects_developers.project_id " +
                        " WHERE project_name  LIKE  ?"
        );

        getBudgetByProjectNameSt = connection.prepareStatement(
                "SELECT SUM(salary) FROM projects JOIN projects_developers " +
                        "ON projects.project_id = projects_developers.project_id " +
                        "JOIN developers ON projects_developers.developer_id = developers.developer_id " +
                        " WHERE project_name  LIKE  ?"
        );

        getIdProjectByNameSt = connection.prepareStatement(
                "SELECT project_id FROM projects " +
                        "WHERE project_name  LIKE  ?"
        );

    }

    public void getAllNames() throws SQLException {
        System.out.println("Список всех  проектов :");
        try (ResultSet rs = getAllNamesSt.executeQuery()) {
            while (rs.next()) {
                long projectID = rs.getLong("project_id");
                String projectName = rs.getString("project_name");
                System.out.println("\t" + projectID + ". " + projectName);
            }
        }
    }


    public void getInfoByName(String name ) throws SQLException {
        System.out.print("\t\tзаказан заказчиком ");
        getCustomerNameByProjectNameSt.setString(1,  name );
        try (ResultSet rs1 = getCustomerNameByProjectNameSt.executeQuery()) {
            while (rs1.next()) {
                System.out.println( rs1.getString("customer_name"));
            }
        }
        System.out.print("\t\tразрабатывается компанией ");
        getCompanyNameByProjectNameSt.setString(1, name);
        try (ResultSet rs2 = getCompanyNameByProjectNameSt.executeQuery()) {
            while (rs2.next()) {
                System.out.println( rs2.getString("company_name"));
            }
        }
        getCostDateSt.setString(1, name);
        try (ResultSet rs = getCostDateSt.executeQuery()) {
            while (rs.next()) {
                System.out.println("\t\tимеет бюджет " + rs.getInt("cost"));
                System.out.println("\t\tзапущен " + LocalDate.parse(rs.getString("start_date")));
            }
        }
    }

    public void getListDevelopers (String name) throws SQLException {
        System.out.println("\tВ проекте " + name + " задействованы следующие разработчики: ");
        getListDevelopersSt.setString(1, name);
        try (ResultSet rs1 = getListDevelopersSt.executeQuery()) {
            while (rs1.next()) {
                System.out.println(rs1.getString("lastName") + " " + rs1.getString("firstName") );
            }
        }
    }


    public void getQuantityDevelopers (String name) throws SQLException {
        getQuantityDevelopersByProjectNameSt.setString(1, name);
        try (ResultSet rs1 = getQuantityDevelopersByProjectNameSt.executeQuery()) {
            while (rs1.next()) {
                System.out.println("\t\tВ данном проекте задействовано " +  rs1.getInt("COUNT(developer_id)") + " разработчика(ов)");
            }
        }
    }

    public void getBudgetByProjectName (String name) throws SQLException {
        getBudgetByProjectNameSt.setString(1, name);
        try (ResultSet rs1 = getBudgetByProjectNameSt.executeQuery()) {
            while (rs1.next()) {
                System.out.println("\t\tБюджет данного проекта - " +  rs1.getInt("SUM(salary)"));
            }
        }
    }

    public void getProjectsListInSpecialFormat () throws SQLException {
        try (ResultSet rs = getAllNamesSt.executeQuery()) {
            while (rs.next()) {
                System.out.print("\t" + LocalDate.parse(rs.getString("start_date")));
                String projectName = rs.getString("project_name");
                System.out.print(", " + projectName);
                getQuantityDevelopersByProjectNameSt.setString(1, projectName);
                try (ResultSet rs1 = getQuantityDevelopersByProjectNameSt.executeQuery()) {
                    while (rs1.next()) {
                        System.out.println(", " + rs1.getInt("COUNT(developer_id)") + " разработчика(ов)");
                    }
                }
            }
        }
    }

    public long getIdProjectByName(String name) throws SQLException {
        getIdProjectByNameSt.setString(1, "%" + name + "%");
        int result = 0;
        try (ResultSet rs = getIdProjectByNameSt.executeQuery()) {
            rs.next();
            result = rs.getInt("project_id");
        }
        return result;
    }
}
