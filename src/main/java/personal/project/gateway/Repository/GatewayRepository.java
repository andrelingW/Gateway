package personal.project.gateway.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import personal.project.gateway.Model.Route;

import java.util.List;

@Repository
public interface GatewayRepository extends JpaRepository<Route, Integer> {
    @Query(value = "SELECT * FROM ROUTE R WHERE R.MID = :mid AND R.SOURCE_PATH = :sourcePath", nativeQuery = true)
    List<Route> getAllData(@Param("mid") String mid, @Param("sourcePath") String sourcePath);
}
