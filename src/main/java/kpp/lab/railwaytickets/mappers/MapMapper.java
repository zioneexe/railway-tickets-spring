package kpp.lab.railwaytickets.mappers;

import kpp.lab.railwaytickets.dto.MapDto;
import kpp.lab.railwaytickets.dto.PositionDto;
import kpp.lab.railwaytickets.model.BaseMap;
import kpp.lab.railwaytickets.model.BasePosition;

public class MapMapper {
    public static MapDto baseMapToMapDto(BaseMap map) {
        return new MapDto(
                map.getSizeX(),
                map.getSizeY()
        );
    }
}
