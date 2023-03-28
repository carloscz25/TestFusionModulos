package main.screen.central;

import io.jmix.ui.component.TextField;
import io.jmix.ui.screen.*;
import main.screen.locations.LocationEdit;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("AddressFragment")
@UiDescriptor("address-fragment.xml")
public class AddressFragment extends ScreenFragment {


    @Autowired
    private TextField nameField;

    @Subscribe
    public void onAttach(AttachEvent event) {
        FrameOwner fo = event.getSource().getHostController();
        if (fo instanceof LocationEdit){
            LocationEdit le = (LocationEdit) fo;
            nameField.setVisible(false);
        }
    }



}