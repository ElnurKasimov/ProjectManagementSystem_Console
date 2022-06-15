package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperDaoService {
    private  List<Developer> developers = new ArrayList<>();
    private PreparedStatement getAllLastNamesSt;
    private PreparedStatement getInfoByLastNameSt;



    public DeveloperDaoService(Connection connection) throws SQLException {
        PreparedStatement getAllInfoSt = connection.prepareStatement(
                "SELECT * FROM developers"
        );
        try (ResultSet rs = getAllInfoSt.executeQuery()) {

            while (rs.next()) {
                Developer developer = new Developer();
                developer.setDeveloper_id(rs.getLong("developer_id"));
                developer.setLastName(rs.getString("lastName"));
                if (rs.getString("firstName") != null) {
                    developer.setFirstName(rs.getString("firstName"));
                }
                developer.setAge(rs.getInt("age"));
                developer.setCompany_id(rs.getInt("company_id"));
                developer.setSalary(rs.getInt("salary"));
                developers.add(developer);
            }
        }

        getAllLastNamesSt = connection.prepareStatement(
                "SELECT lastName FROM developers"
        );

        getInfoByLastNameSt = connection.prepareStatement(
                "SELECT lastName, firstName, age, salary, name " +
                 "FROM developers JOIN companies " +
                 "ON developers.company_id = companies.company_id" +
                 " WHERE lastName  LIKE ?"
        );


    }



    /*
    private PreparedStatement createSt;
    private PreparedStatement getByIdSt;
    private PreparedStatement getByLastNameSt;
    private PreparedStatement updateSt;
    private PreparedStatement deleteByIdSt;
    private PreparedStatement existsByIdSt;
    private PreparedStatement clearSt;
*/


    public void getAllLastNames() throws SQLException {
        System.out.println("Фамилии всех разработчиков");
        for (Developer developer : developers) {
            System.out.println("\t" + developer.getDeveloper_id() + ". " + developer.getLastName());
        }
    }

    public void getInfoByLastName() throws SQLException {
        System.out.print("Введите фамилию : ");
        Scanner sc = new Scanner(System.in);
        String result = sc.nextLine();
        getInfoByLastNameSt.setString(1, "%" + result + "%");
        try (ResultSet rs = getInfoByLastNameSt.executeQuery()) {
            while (rs.next()) {
                System.out.println("\tФамилия -  " + rs.getString("lastName") + ";  ");
                System.out.println("\tимя -  " + rs.getString("firstName") + ";  ");
                System.out.println("\tВозраст -  " + rs.getInt("age")+ ";  ");
                System.out.println("\tРаботает в компании -  " + rs.getString("name") + ";  ");
                System.out.println("\tЗарплата -  " + rs.getInt("salary"));
            }
        }
    }

    /*
    public void getAllInfo() throws SQLException {
        try (ResultSet rs = getAllInfoSt.executeQuery()) {
            while (rs.next()) {
                System.out.print("developer_id = " + rs.getLong("developer_id") + ";  ");
                System.out.print("lastName = " + rs.getString("lastName") + ";  ");
                System.out.print("firstName = " + rs.getString("firstName") + ";  ");
                System.out.print("age = " + rs.getInt("age") + ";  ");
                System.out.print("company_id = " + rs.getInt("company_id") + ";  ");
                System.out.println("salary = " + rs.getInt("salary"));
            }
        }
    }

     */

}


/*
  public List<Developer> getAll() throws SQLException, InterruptedException {
        return getDevelopers(getAllByLastNameSt);
    }

    public long create(Developer developer) throws SQLException {
        createSt.setString(1, developer.getName());
        createSt.setString(2,
                developer.getBirthday() == null ? null : human.getBirthday().toString());
        createSt.setString(3,
                human.getGender() == null ? null : human.getGender().name());
        createSt.executeUpdate();

        long id;

        try(ResultSet rs = selectMaxIdSt.executeQuery()) {
            rs.next();
            id = rs.getLong("maxId");
        }

        return id;
    }

    public Human getById(long id) throws SQLException {
        getByIdSt.setLong(1, id);

        try(ResultSet rs = getByIdSt.executeQuery()) {
            if (!rs.next()) {
                return null;
            }

            Human result = new Human();
            result.setId(id);
            result.setName(rs.getString("name"));

            String birthday = rs.getString("birthday");
            if (birthday != null) {
                result.setBirthday(LocalDate.parse(birthday));
            }

            String gender = rs.getString("gender");
            if (gender != null) {
                result.setGender(Human.Gender.valueOf(gender));
            }

            return result;
        }
    }

    }

    public void update(Human human) throws SQLException {
        updateSt.setString(1, human.getName());
        updateSt.setString(2, human.getBirthday().toString());
        updateSt.setString(3, human.getGender().name());
        updateSt.setLong(4, human.getId());

        updateSt.executeUpdate();
    }

    public void deleteById(long id) throws SQLException {
        deleteByIdSt.setLong(1, id);
        deleteByIdSt.executeUpdate();
    }

    public boolean exists(long id) throws SQLException {
        existsByIdSt.setLong(1, id);
        try(ResultSet rs = existsByIdSt.executeQuery()) {
            rs.next();

            return rs.getBoolean("humanExists");
        }
    }

    public long save(Human human) throws SQLException {
        if (exists(human.getId())) {
            update(human);
            return human.getId();
        }

        return create(human);
    }

    public void clear() throws SQLException {
        clearSt.executeUpdate();
    }





      */



/*



        createSt = connection.prepareStatement(
                "INSERT INTO human (lastName, firstName, age, company_id, salary) VALUES(?, ?, ?, ?, ?)"
        );

        getByIdSt = connection.prepareStatement(
                "SELECT lastName, firstName, age, company_id, salary FROM developers WHERE id = ?"
        );

        getByIdSt = connection.prepareStatement(
                "SELECT lastName, firstName, age, company_id, salary FROM developers WHERE id = ?"
        );

        //TODO  can be several records with the same lastName
        getByLastNameSt = connection.prepareStatement(
                "SELECT lastName, firstName, age, company_id, salary FROM developers WHERE lastName = ?"
        );


        updateSt = connection.prepareStatement(
                "UPDATE human SET name = ?, birthday = ?, gender = ? WHERE id = ?"
        );

        deleteByIdSt = connection.prepareStatement(
                "DELETE FROM human WHERE id = ?"
        );

        existsByIdSt = connection.prepareStatement(
                "SELECT count(*) > 0 AS humanExists FROM human WHERE id = ?"
        );

        clearSt = connection.prepareStatement(
                "DELETE FROM human"
        );


    }

 */


