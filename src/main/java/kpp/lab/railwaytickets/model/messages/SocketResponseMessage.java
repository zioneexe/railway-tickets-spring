package kpp.lab.railwaytickets.model.messages;

import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SocketResponseMessage {
    private BaseClient client;
}
