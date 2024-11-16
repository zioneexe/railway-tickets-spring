package kpp.lab.railwaytickets.mappers;

import kpp.lab.railwaytickets.dto.CashDeskDto;
import kpp.lab.railwaytickets.dto.EntranceDto;
import kpp.lab.railwaytickets.dto.TrainStationDto;
import kpp.lab.railwaytickets.model.*;
import kpp.lab.railwaytickets.model.abstractions.BaseCashDesk;
import kpp.lab.railwaytickets.model.abstractions.BaseEntrance;

import java.util.ArrayList;
import java.util.List;

public class TrainStationMapper {
    public static TrainStationDto baseTrainStationToTrainStationDto(BaseTrainStation trainStation) {
        List<BaseCashDesk> cashDeskList = trainStation.getCashDesks();
        List<CashDeskDto> cashDeskDtoList = new ArrayList<>();
        for (BaseCashDesk cashDesk : cashDeskList) {
            cashDeskDtoList.add(CashDeskMapper.baseCashDeskToCashDeskDto(cashDesk));
        }

        List<BaseEntrance> entranceList = trainStation.getEntrances();
        List<EntranceDto> entranceDtoList = new ArrayList<>();
        for (var entrance : entranceList) {
            entranceDtoList.add(EntranceMapper.baseEntranceToEntranceDto(entrance));
        }

        return new TrainStationDto(
                cashDeskDtoList,
                entranceDtoList,
                MapMapper.baseMapToMapDto(trainStation.getMap()),
                trainStation.getMaxPeopleCount()
        );
    }
}
