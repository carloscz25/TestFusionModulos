package main.screen.locations;

import io.jmix.ui.component.EntityPicker;
import io.jmix.ui.screen.*;
import main.entity.locations.EnergyCertificate;
import main.entity.locations.Unit;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("EnergyCertificate.edit")
@UiDescriptor("energy-certificate-edit.xml")
@EditedEntityContainer("energyCertificateDc")
public class EnergyCertificateEdit extends StandardEditor<EnergyCertificate> {

    @Autowired
    private EntityPicker<Unit> unitField;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if (this.isEntityModifiedInParentContext()){
            unitField.setEnabled(false);
        }

    }

}