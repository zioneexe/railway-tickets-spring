package kpp.lab.railwaytickets.controllers;

import kpp.lab.railwaytickets.dto.PositionDto;
import kpp.lab.railwaytickets.dto.StartupPropertiesDto;
import kpp.lab.railwaytickets.mappers.ClientGeneratorMapper;
import kpp.lab.railwaytickets.mappers.PositionMapper;
import kpp.lab.railwaytickets.model.abstractions.BasePosition;
import kpp.lab.railwaytickets.model.abstractions.BaseStartupProperties;
import kpp.lab.railwaytickets.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/settings")
public class SettingsController {

    private final BaseStartupProperties startupProperties;

    @Autowired
    public SettingsController(BaseStartupProperties startupProperties) {
        this.startupProperties = startupProperties;
    }

    @PostMapping
    public ResponseEntity<StartupPropertiesDto> setProperties(@RequestBody StartupPropertiesDto startupPropertiesDto) {

        startupProperties.setMinServiceTime(startupPropertiesDto.getMinServiceTime());
        startupProperties.setMaxServiceTime(startupPropertiesDto.getMaxServiceTime());
        startupProperties.setClientGenerator(ClientGeneratorMapper.clientGeneratorDtoToBaseClientGenerator(startupPropertiesDto.getClientGenerator()));
        startupProperties.setStationWidth(startupPropertiesDto.getStationWidth());
        startupProperties.setStationHeight(startupPropertiesDto.getStationHeight());

        var deskPositionsDto = startupPropertiesDto.getDeskPositions();
        List<BasePosition> deskPositions = new ArrayList<>();
        for (PositionDto positionDto : deskPositionsDto) {
            deskPositions.add(PositionMapper.positionDtoToBasePosition(positionDto));
        }

        startupProperties.setDeskPositions(deskPositions);

        var reserveDeskPositionDto = startupPropertiesDto.getReserveDeskPosition();
        startupProperties.setReserveDeskPosition(new Position(reserveDeskPositionDto.getX(), reserveDeskPositionDto.getY()));

        var entrancePositionsDto = startupPropertiesDto.getEntrancePositions();
        List<BasePosition> entrancePositions = new ArrayList<>();
        for (PositionDto positionDto : entrancePositionsDto) {
            entrancePositions.add(PositionMapper.positionDtoToBasePosition(positionDto));
        }

        startupProperties.setEntrancePositions(entrancePositions);

        return ResponseEntity.status(HttpStatus.CREATED).body(startupPropertiesDto);
    }
}
