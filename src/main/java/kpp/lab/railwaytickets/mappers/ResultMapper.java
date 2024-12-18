package kpp.lab.railwaytickets.mappers;

import kpp.lab.railwaytickets.dto.ResultDto;
import kpp.lab.railwaytickets.model.Result;

public class ResultMapper {

    private ResultMapper() {}

    public static ResultDto resultToDto(Result result) {
        return new ResultDto(
                result.getTotalClients(),
                result.getTotalTickets()
        );
    }

    public static Result dtoToResult(ResultDto resultDto) {
        return new Result(
                resultDto.getTotalClients(),
                resultDto.getTotalTickets()
        );
    }
}
