package pl.put.poznan.transformer.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.Building;

import java.util.List;


@RestController
@RequestMapping("/buildings")
public class RESTapiController {
    private static final Logger logger = LoggerFactory.getLogger(RESTapiController.class);

    @Autowired
    BuildingService buildingService;

    @GetMapping(produces = "application/json")
    public List<Building> getBuildings() {
        List<Building> buildings = buildingService.getBuildings();
        return buildings;
    }




}

