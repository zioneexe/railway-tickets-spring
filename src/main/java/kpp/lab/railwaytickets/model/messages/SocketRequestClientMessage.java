package kpp.lab.railwaytickets.model.messages;

import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SocketRequestClientMessage {
    private BaseClient client;

    public SocketRequestClientMessage() {}

    public SocketRequestClientMessage(BaseClient client) {
        this.client = client;
    }
}