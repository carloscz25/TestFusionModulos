package main.screen.central;

import io.jmix.ui.screen.*;
import main.entity.central.Document;

@UiController("Document.browse")
@UiDescriptor("document-browse.xml")
@LookupComponent("documentsTable")
public class DocumentBrowse extends StandardLookup<Document> {
}