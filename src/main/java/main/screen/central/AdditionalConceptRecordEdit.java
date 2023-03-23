package main.screen.central;

import io.jmix.ui.screen.*;
import main.entity.central.AdditionalConceptRecord;

@UiController("AdditionalConceptRecord.edit")
@UiDescriptor("additional-concept-record-edit.xml")
@EditedEntityContainer("additionalConceptRecordDc")
public class AdditionalConceptRecordEdit extends StandardEditor<AdditionalConceptRecord> {
}