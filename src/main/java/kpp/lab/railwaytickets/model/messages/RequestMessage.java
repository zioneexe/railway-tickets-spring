package kpp.lab.railwaytickets.model.messages;

import lombok.*;

@Data
@Builder
public class RequestMessage {
    private String message;

    public RequestMessage() {}

    public RequestMessage(String message) {
        this.message = message;
    }
}