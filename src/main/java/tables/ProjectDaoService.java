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
    private PreparedStatement  getCostDateSt;


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
                " SELECT project_id, project_name FROM projects"
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

}