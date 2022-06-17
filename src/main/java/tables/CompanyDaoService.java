package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoService {
    private List<Company> companies = new ArrayList<>();

    private PreparedStatement getAllInfoSt;
    private PreparedStatement getQuantityEmployeeSt;


    public CompanyDaoService(Connection connection) throws SQLException {
        PreparedStatement getAllInfoSt = connection.prepareStatement(
                "SELECT * FROM companies"
        );
        try (ResultSet rs = getAllInfoSt.executeQuery()) {
            while (rs.next()) {
                Company company = new Company();
                company.setCompany_id(rs.getLong("company_id"));
                if (rs.getString("company_name") != null) {
                    company.setCompany_name(rs.getString("company_name"));
                }
                company.setRating(Company.Rating.valueOf(rs.getString("rating")));
                companies.add(company);
            }
        }

        getQuantityEmployeeSt = connection.prepareStatement(
                " SELECT COUNT(developer_id) FROM companies " +
                        "JOIN developers ON companies.company_id = developers.company_id " +
                        " WHERE company_name  LIKE  ?"
        );

    }

    public void getAllNames() throws SQLException {
        System.out.println("Список всех IT компаний :");
        for (Company company : companies) {
            getQuantityEmployeeSt.setString(1, "%" + company.getCompany_name() + "%");
            int result = 0;
            try (ResultSet rs = getQuantityEmployeeSt.executeQuery()) {
                while (rs.next()) {
                    result = rs.getInt("COUNT(developer_id)");
                }
                System.out.print("\t" + company.getCompany_id() + ". " + company.getCompany_name() + ", рейтинг - " + company.getRating());
                System.out.println(",  количество сотрудников - " + result);
            }
        }
    }
}