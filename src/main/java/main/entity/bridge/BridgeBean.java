package main.entity.bridge;

import io.jmix.core.DataManager;
import main.entity.projects.Cycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BridgeBean {

    @Autowired
    private DataManager dataManager;


    public CycleUnitBridge get(Cycle c){
        CycleUnitBridge cub = dataManager.load(CycleUnitBridge.class)
                .query("select cub from CycleUnitBridge cub where cub.cycle.id = :cid")
                .parameter("cid", c.getId()).one();
        return cub;
    }
}