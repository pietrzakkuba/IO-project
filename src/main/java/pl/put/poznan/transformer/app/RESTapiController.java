package pl.put.poznan.transformer.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.Building;
import pl.put.poznan.transformer.logic.Floor;
import pl.put.poznan.transformer.logic.Room;

import java.util.List;


@RestController
@RequestMapping("/buildings")
public class RESTapiController {
    private static final Logger logger = LoggerFactory.getLogger(RESTapiController.class);

    @Autowired
    BuildingService buildingService;

    @RequestMapping("/all")
    public List<Building> getBuildings() {
        return buildingService.getBuildings();
    }

    @RequestMapping(value = "/param/{build}/{arg}", method = RequestMethod.GET, produces = "application/json")
    public List<Room> getBuildingsWithParams(@PathVariable String build, @PathVariable int arg) {
        return buildingService.getRoomsOverArgument(build, arg);
    }

    @RequestMapping("/find/{build}")
    public Building getBuild(@PathVariable String build) {
        List<Building> buildings = buildingService.getBuildings();
        for (int i = 0; i <= buildings.size(); i++) {

            if (buildings.get(i).getName().equals(build))
                return buildings.get(i);
        }
        return null;
    }

    @RequestMapping("/find/{build}/{floor}")
    public Floor getFloor(@PathVariable String build, @PathVariable String floor) {
        List<Building> buildings = buildingService.getBuildings();
        for (int i = 0; i <= buildings.size(); i++) {
            System.out.println(floor);
            if (buildings.get(i).getName().equals(build))
                for (int j = 0; j <= buildings.get(i).getFloors().size(); j++)
                    if (buildings.get(i).getFloors().get(j).getName().equals(floor))
                        return buildings.get(i).getFloors().get(j);
        }
        return null;
    }

    @RequestMapping("/find/{build}/{floor}/{room}")
    public Room getRoom (@PathVariable String build, @PathVariable String floor, @PathVariable String room) {
        List<Building> buildings = buildingService.getBuildings();
        for (int i = 0; i <= buildings.size(); i++) {
            if (buildings.get(i).getName().equals(build))
                for (int j = 0; j <= buildings.get(i).getFloors().size(); j++)
                    if (buildings.get(i).getFloors().get(j).getName().equals(floor))
                        for (int k = 0; k <= buildings.get(i).getFloors().get(j).getRooms().size(); k++)
                            if (buildings.get(i).getFloors().get(j).getRooms().get(k).getName().equals(room))
                                return buildings.get(i).getFloors().get(j).getRooms().get(k);

        }
        return null;
    }


}


