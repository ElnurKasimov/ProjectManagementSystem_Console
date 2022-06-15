package consoleMenuService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MenuService {
    private Map<String, Menu> elementsMenu = new HashMap<String, Menu>();

    public void create() {
        FillerContent fillerMenuContent = new FillerContent();

        Menu mainMenu = new Menu("Main");
        mainMenu.setContentMenu(fillerMenuContent.fill("Main"));
        elementsMenu.put("Main", mainMenu);

        Menu developersMenu = new Menu("Developers");
        developersMenu.setContentMenu(fillerMenuContent.fill("Developers"));
        elementsMenu.put("Developers", developersMenu);

        Menu projectsMenu = new Menu("Projects");
        projectsMenu.setContentMenu(fillerMenuContent.fill("Projects"));
        elementsMenu.put("Projects", projectsMenu);

        Menu companiesMenu = new Menu("Companies");
        companiesMenu.setContentMenu(fillerMenuContent.fill("Companies"));
        elementsMenu.put("Companies", companiesMenu);

        Menu customersMenu = new Menu("Customers");
        customersMenu.setContentMenu(fillerMenuContent.fill("Customers"));
        elementsMenu.put("Customers", customersMenu);

    }

    public Menu get (String name) {
        Menu result = null;
        Optional<Menu> optionalResult = Optional.empty();
        for (Map.Entry<String, Menu> entry : elementsMenu.entrySet()) {
                if ( entry.getKey().equals(name)) optionalResult = Optional.ofNullable(entry.getValue());
        }
            if (optionalResult.isPresent() ) {
                result = optionalResult.get();
            } else {
            System.out.println("Меню с таким названием не найдено");
            }
      return result;
    }

    public void add (String name) {

    }
}
