package kpp.lab.railwaytickets.socket;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.protocol.JacksonJsonSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.PreDestroy;

@CrossOrigin
@Component
public class SocketIOConfig {

    @Value("${socket.host}")
    private String socketHost;
    @Value("${socket.port}")
    private int socketPort;
    private SocketIOServer server;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname(socketHost);
        config.setPort(socketPort);
        config.setOrigin("http://localhost:5173");

        config.setJsonSupport(new JacksonJsonSupport());

        server = new SocketIOServer(config);
        server.start();

        return server;
    }

    @PreDestroy
    public void stopSocketIOServer() {
        this.server.stop();
    }

}