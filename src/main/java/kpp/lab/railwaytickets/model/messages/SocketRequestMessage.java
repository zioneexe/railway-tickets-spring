package kpp.lab.railwaytickets.model.messages;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
public class SocketRequestMessage {
    private String message;

    public SocketRequestMessage() {}

    public SocketRequestMessage(String message) {
        this.message = message;
    }
}