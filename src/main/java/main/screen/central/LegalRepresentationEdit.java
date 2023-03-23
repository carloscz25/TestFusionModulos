package main.screen.central;

import io.jmix.ui.screen.*;
import main.entity.central.LegalRepresentation;

@UiController("LegalRepresentation.edit")
@UiDescriptor("legal-representation-edit.xml")
@EditedEntityContainer("legalRepresentationDc")
public class LegalRepresentationEdit extends StandardEditor<LegalRepresentation> {
}