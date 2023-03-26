package main.entity.bridge;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Bridges {

    List<Modules> activatedModules = new ArrayList<>();

    public void init(){
        activatedModules.add(Modules.CENTRAL);
        activatedModules.add(Modules.LOCATIONS);
        activatedModules.add(Modules.PROJECTS);
    }

    public boolean isModuleActive(Modules m){
        if (activatedModules.indexOf(m)!=-1){
            return true;
        }else{
            return false;
        }
    }

}