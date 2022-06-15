package tables;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Project {
    private long project_id;
    private String name;
    private long company_id;
    private long customer_id;
    public int cost;
    private LocalDate start_date;
}
