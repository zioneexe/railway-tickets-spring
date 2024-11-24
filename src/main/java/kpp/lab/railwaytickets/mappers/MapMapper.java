package kpp.lab.railwaytickets.mappers;

import kpp.lab.railwaytickets.dto.MapDto;
import kpp.lab.railwaytickets.model.interfaces.BaseMap;

public class MapMapper {

    private MapMapper() {}

    public static MapDto baseMapToMapDto(BaseMap map) {
        return new MapDto(
                map.getSizeX(),
                map.getSizeY()
        );
    }
}
