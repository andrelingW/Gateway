package personal.project.gateway.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "ROUTE")
public class Route {
    @Id
    @Column(name = "ROUTE_ID")
    private Long routeID;
    @Column(name = "ID")
    private String id;
    @Column(name = "SOURCE_PATH")
    private String sourcePath;
    @Column(name = "TARGET_URL")
    private String targetUrl;
}
