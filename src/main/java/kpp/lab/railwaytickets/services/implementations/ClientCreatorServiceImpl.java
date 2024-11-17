package kpp.lab.railwaytickets.services.implementations;

import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;
import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BaseStartupProperties;
import kpp.lab.railwaytickets.model.interfaces.ClientCreatorSubscriber;
import kpp.lab.railwaytickets.model.decorator.ClientDecorator;
import kpp.lab.railwaytickets.services.interfaces.ClientCreatorService;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ClientCreatorServiceImpl implements ClientCreatorService {

    private BaseStartupProperties startupProperties;
    private BaseClientGenerator clientGenerator;

    public ClientCreatorServiceImpl(BaseStartupProperties startupProperties) {
        this.startupProperties = startupProperties;
    }

    @Override
    public BaseClient createClient() throws InterruptedException {
        return startupProperties.getClientGenerator().generateClient();
    }
}
