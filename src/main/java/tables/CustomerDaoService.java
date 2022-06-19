package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoService {
    private List<Customer> customers = new ArrayList<>();

    private PreparedStatement getAllInfoSt;
    private PreparedStatement getAllNamesSt;
    private PreparedStatement getProjectsNamesSt;


    public CustomerDaoService(Connection connection) throws SQLException {
            PreparedStatement getAllInfoSt = connection.prepareStatement(
                    "SELECT * FROM customers"
            );
            try (ResultSet rs = getAllInfoSt.executeQuery()) {
                while (rs.next()) {
                    Customer customer = new Customer();
                    customer.setCustomer_id(rs.getLong("customer_id"));
                    if (rs.getString("customer_name") != null) {
                        customer.setCustomer_name(rs.getString("customer_name"));
                    }
                    customer.setReputation(Customer.Reputation.valueOf(rs.getString("reputation")));
                    customers.add(customer);
                }
            }

        getAllNamesSt = connection.prepareStatement(
                " SELECT customer_id, customer_name, reputation FROM customers"
        );
        getProjectsNamesSt = connection.prepareStatement(
                " SELECT project_name FROM customers JOIN projects " +
                            "ON customers.customer_id = projects.customer_id " +
                            " WHERE  customer_name  LIKE  ?"
            );



        }

        public void getAllNames() throws SQLException {
            System.out.println("Список всех  заказчиков :");
            try (ResultSet rs = getAllNamesSt.executeQuery()) {
                while (rs.next()) {
                    long customerID = rs.getLong("customer_id");
                    String customerName = rs.getString("customer_name");
                    String customerReputation = rs.getString("reputation");
                    System.out.print("\t" + customerID + ". " + customerName + ", репутация - " + customerReputation);
                    System.out.println(", является заказчиком следующих проектов: ");
                    getProjectsNamesSt.setString(1, "%" + customerName + "%");
                    try (ResultSet rs1 = getProjectsNamesSt.executeQuery()) {
                        while (rs1.next()) {
                            System.out.println("\t\t" + rs1.getString("project_name"));
                        }
                    }
                }
            }

        }

}
