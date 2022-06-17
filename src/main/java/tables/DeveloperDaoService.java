package tables;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeveloperDaoService {
    private  List<Developer> developers = new ArrayList<>();

    //private  List<Developer> customers = new ArrayList<>();
    //private  List<Developer> projects = new ArrayList<>();

    private PreparedStatement getAllLastNamesSt;
    private PreparedStatement getInfoByLastNameSt;
    private PreparedStatement getSkillsByLastNameSt;
    private PreparedStatement getProjectsByLastNameSt;
    private PreparedStatement getQuantityJavaDevelopersSt;
    private PreparedStatement getListMiddleDevelopersSt;
    private PreparedStatement selectMaxIdSt;
    private PreparedStatement addDeveloperSt;

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
                "SELECT lastName, firstName, age, salary, company_name " +
                 "FROM developers JOIN companies " +
                 "ON developers.company_id = companies.company_id" +
                 " WHERE lastName  LIKE ?"
        );

        getSkillsByLastNameSt = connection.prepareStatement(
                "SELECT language, level " +
                "FROM developers JOIN developers_skills " +
                "ON developers.developer_id = developers_skills.developer_id " +
                "JOIN skills ON developers_skills.skill_id = skills.skill_id " +
                "WHERE lastName  LIKE ?"
                 );

        getProjectsByLastNameSt = connection.prepareStatement(
                "SELECT  project_name " +
                        "FROM developers JOIN projects_developers " +
                        "ON developers.developer_id = projects_developers.developer_id " +
                        "JOIN projects ON projects_developers.project_id = projects.project_id " +
                        "WHERE lastName  LIKE ?"
        );

        getQuantityJavaDevelopersSt = connection.prepareStatement(
                "SELECT COUNT(language) " +
                "FROM developers JOIN developers_skills " +
                "ON developers.developer_id = developers_skills.developer_id " +
                "JOIN skills ON developers_skills.skill_id = skills.skill_id " +
                "WHERE language = 'Java'"
        );

        getListMiddleDevelopersSt = connection.prepareStatement(
                "SELECT lastName, firstName, language " +
                        "FROM developers JOIN developers_skills " +
                        "ON developers.developer_id = developers_skills.developer_id " +
                        "JOIN skills ON developers_skills.skill_id = skills.skill_id " +
                        "WHERE level = 'middle'"
        );

        selectMaxIdSt = connection.prepareStatement(
                "SELECT max(developer_id) AS maxId FROM developers"
        );

        addDeveloperSt = connection.prepareStatement(
                "INSERT INTO developers  VALUES ( ?, ?, ?, ?, ?)");
    }





    public void getAllLastNames() throws SQLException {
        System.out.println("Фамилии всех разработчиков");
        for (Developer developer : developers) {
            System.out.println("\t" + developer.getDeveloper_id() + ". " + developer.getLastName());
        }
    }

    public void getInfoByLastName(String lastName ) throws SQLException {
        getInfoByLastNameSt.setString(1, "%" + lastName + "%");
        try (ResultSet rs = getInfoByLastNameSt.executeQuery()) {
            while (rs.next()) {
                System.out.println("\tф.и.о. -  " + rs.getString("lastName") + "  " + rs.getString("firstName") + ";  ");
                System.out.println("\tВозраст -  " + rs.getInt("age")+ ";  ");
                System.out.println("\tРаботает в компании -  " + rs.getString("company_name") + ";  ");
                System.out.println("\tЗарплата -  " + rs.getInt("salary"));
            }
        }
    }

    public void getSkillsByLastName(String lastName) throws SQLException {
        getSkillsByLastNameSt.setString(1, "%" + lastName + "%");
        System.out.println("\tВладеет языками: ");
        try (ResultSet rs = getSkillsByLastNameSt.executeQuery()) {
            while (rs.next()) {
                System.out.println("\t\t" + rs.getString("language") + " -  " + rs.getString("level") + ";  ");
            }
        }
    }

    public void getProjectsByLastName(String lastName) throws SQLException {
        getProjectsByLastNameSt.setString(1, "%" + lastName + "%");
        System.out.println("\tУчаствует в проектах: ");
        try (ResultSet rs = getProjectsByLastNameSt.executeQuery()) {
            while (rs.next()) {
                System.out.println("\t\t" + rs.getString("project_name") + ";  ");
            }
        }
    }

    public void getQuantityJavaDevelopers() throws SQLException {
        int count = 0;
        try (ResultSet rs = getQuantityJavaDevelopersSt.executeQuery()) {
            while (rs.next()) {
                count = rs.getInt("COUNT(language)");
            }
        }
        System.out.println("\tВо всех компаниях работат  " + count  + " Java-разработчиков");
    }

    public void getListMiddleDevelopers() throws SQLException {
        System.out.println("\tCписок всех разработчиков с уровнем знания языка middle: ");
        try (ResultSet rs = getListMiddleDevelopersSt.executeQuery()) {
            while (rs.next()) {
                System.out.print(rs.getString("lastName" ) + " " +  rs.getString("firstName"));
                System.out.println(",  язык - " + rs.getString("language"));
            }
        }
    }

    // todo все компании и заказчики добавлены, можно прорабатывать логику добавления разработчика

/*
    public void addDeveloper () throws SQLException {
        long developer_id;
        try(ResultSet rs = selectMaxIdSt.executeQuery()) {
            rs.next();
            developer_id = rs.getLong("maxId");
        }
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите фамилию разработчика : ");
        String lastName = sc.nextLine();
        System.out.print("Введите имя разработчика : ");
        String firstName = sc.nextLine();
        System.out.print("Введите возраст разработчика : ");
        int age = sc.nextInt();
        System.out.print("Введите название компании где он работает : ");
        boolean isCompanyTrue = true;
        do {
            String companyName = sc.nextLine();

        } while (!isCompanyTrue);
        System.out.print("Введите зарплату разработчика : ");
        int salary = sc.nextInt();

        addDeveloperSt.setLong(1, developer_id+1);
        addDeveloperSt.setString(1, developer_id+1);
    }

 */




    /*
    "\t\t- его полное ф.и.о.;\n" +
                     " \t\t- возраст;\n" +
                     " \t\t- языки программирования, на которых он работает;\n" +
                     " \t\t- уровень знания этих языков;\n" +
                     " \t\t- компания, в которой он работает;\n" +
                     " \t\t- его заработная плата;\n" +
                     " \t\t- в каких проектах участвует." );



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


