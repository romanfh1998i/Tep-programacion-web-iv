package grails.utils;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;

import grails.LoginPage;
import proyectofinalprogweb.Sabor;
import proyectofinalprogweb.SaborService;
import proyectofinalprogweb.User;
import proyectofinalprogweb.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Theme("Valo")
public class SaborUI extends VerticalLayout implements View {
    public static final String NAME = "Sabor";
    private Button logout;
    private Label currentUser;
    public SaborUI(){
        Panel panel = new Panel("Sabor");
        panel.setSizeUndefined();
        addComponent(panel);
        FormLayout content = new FormLayout();
        TextField name = new TextField("Name");
        logout = new Button("Logout");
        List<String> colores = new ArrayList<>();
        colores.add("Rosa");
        colores.add("Amarillo");
        colores.add("Azul");
        colores.add("Blanco");
        colores.add("Marron");
        colores.add("Verde");

        ComboBox <String>color = new ComboBox<>("Color");
        color.setItems(colores);

        content.addComponent(name);
        content.addComponent(color);
        Button send = new Button("Enter");

        send.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (!((color.getValue() != null) &&
                      (name.getValue().length()<2))) {
                    Sabor sabor = new Sabor();
                    sabor.setColor(color.getValue());
                    sabor.setName(name.getValue());
                    Grails.get(SaborService.class).createSabor(sabor);
                    getUI().getNavigator().addView(HeladoUI.NAME, HeladoUI.class);
                    Page.getCurrent().setUriFragment("!" + LoginPage.NAME);
                } else {
                    Notification.show("Debes especificar un nombre y un color para el sabor!!", Notification.Type.ERROR_MESSAGE);
                }
            }
        });
        logout.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;
            @Override
            public void buttonClick(Button.ClickEvent event)  {
                VaadinSession.getCurrent().getSession().invalidate();
                Page.getCurrent().setLocation("/");
            }
        });

        String username = VaadinSession.getCurrent().getAttribute("user").toString();
        User user = Grails.get(UserService.class).findUser(username);
        if(user !=null){
            currentUser = new Label("");
            addComponent(currentUser);
            setComponentAlignment(currentUser,Alignment.TOP_RIGHT);
            addComponent(logout);
            setComponentAlignment(logout, Alignment.TOP_RIGHT);
        }
        content.addComponent(send);
        content.setSizeUndefined();
        content.setMargin(true);
        panel.setContent(content);
        setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
    }

}
