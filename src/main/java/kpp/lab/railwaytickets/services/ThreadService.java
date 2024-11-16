package kpp.lab.railwaytickets.services;

import kpp.lab.railwaytickets.model.abstractions.BaseCashDesk;
import kpp.lab.railwaytickets.model.generator.BaseClientGenerator;

import java.util.List;

public interface ThreadService {

    void startClientGenerator(BaseClientGenerator clientGenerator);

    void stopClientGenerator();

    void startCashDesks(List<BaseCashDesk> cashDesks);

    void stopCashDesks();

    void shutdownAll();

}
