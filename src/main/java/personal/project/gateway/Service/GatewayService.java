package personal.project.gateway.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.project.gateway.Model.Route;
import personal.project.gateway.Repository.GatewayRepository;

import java.util.List;

@Service
public class GatewayService {

    @Autowired
    GatewayRepository repository;

    public List<Route> checkURL(String mid, String sourcePath) {
        return repository.getAllData(mid, sourcePath);
    }

}
