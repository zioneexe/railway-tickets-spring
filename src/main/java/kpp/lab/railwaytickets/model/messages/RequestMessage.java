package kpp.lab.railwaytickets.model.messages;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestMessage {
    private String message;
}