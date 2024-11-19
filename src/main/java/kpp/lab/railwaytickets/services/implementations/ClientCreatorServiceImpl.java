package kpp.lab.railwaytickets.services.implementations;

import kpp.lab.railwaytickets.model.interfaces.BaseClient;
import kpp.lab.railwaytickets.model.interfaces.BaseStartupProperties;
import kpp.lab.railwaytickets.model.interfaces.BaseTrainStation;
import kpp.lab.railwaytickets.services.interfaces.ClientCreatorService;
import org.springframework.stereotype.Service;

@Service
public class ClientCreatorServiceImpl implements ClientCreatorService {

    private BaseStartupProperties startupProperties;
    private BaseTrainStation trainStation;
    public ClientCreatorServiceImpl(BaseStartupProperties startupProperties, BaseTrainStation trainStation) {
        this.startupProperties = startupProperties;
        this.trainStation = trainStation;
    }

    @Override
    public BaseClient createClient() throws InterruptedException {
        if (trainStation.getMaxPeopleCount() < trainStation.getCurrentClientNumber()) {
            while(true) {
                if (trainStation.getCurrentClientNumber() < trainStation.getMaxPeopleCount() * 0.7) {
                    break;
                }
                Thread.sleep(100);
            }
        }

        return startupProperties.getClientGenerator().generateClient();
    }
}
