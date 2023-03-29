package main.screen.locations;

import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.model.DataContext;
import io.jmix.ui.screen.*;
import main.entity.locations.OccupancyCertificate;
import main.entity.locations.Unit;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("OccupancyCertificate.edit")
@UiDescriptor("occupancy-certificate-edit.xml")
@EditedEntityContainer("occupancyCertificateDc")
public class OccupancyCertificateEdit extends StandardEditor<OccupancyCertificate> {
    @Autowired
    private DataContext dataContext;
    @Autowired
    private EntityPicker<Unit> unitField;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if (this.isEntityModifiedInParentContext()){
            unitField.setEnabled(false);
        }

    }



}