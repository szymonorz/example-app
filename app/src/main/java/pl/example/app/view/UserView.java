package pl.example.app.view;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.security.core.userdetails.UserDetails;
import pl.example.app.service.SecurityService;

import javax.annotation.security.PermitAll;

@Route("/")
@PermitAll
public class UserView extends VerticalLayout {

    private SecurityService service;
    public UserView(SecurityService service){
        this.service = service;
        Text username = new Text("");
        UserDetails userDetails = service.getAuthenticatedUser();
        if (userDetails != null){
            username.setText(userDetails.getUsername());
        }
        add(username);
    }
}
