package grails;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import elemental.html.IDBFactory;
import grails.utils.HeladoUI;
import grails.utils.RegisterUI;
import grails.utils.SaborUI;
import proyectofinalprogweb.Helado;
import proyectofinalprogweb.Sabor;
import proyectofinalprogweb.User;

/**
 * Created by aluis on 5/27/18.
 */
@Push
@Theme("DemoGrails")
@Widgetset("AppWidgetSet")
@SpringUI(path = "/")
public class AppUI extends UI {

    // Dato importante con los themes, se generan unos .css y unos css.gz que deben ser borrados para ver cambios refrescados.
    private VerticalLayout mainLayout = new VerticalLayout();

    @Override
    protected void init(VaadinRequest request) {
        new Navigator(this, this);
        setContent(mainLayout);
        getNavigator().addView(LoginPage.NAME, LoginPage.class);

        getNavigator().setErrorView(LoginPage.class);
//        Page.getCurrent().addPopStateListener(new Page.PopStateListener() {
//            @Override
//            public void uriChanged(Page.PopStateEvent event) {
//                router(event.getUri());
//            }
//        });
        Page.getCurrent().addUriFragmentChangedListener(new Page.UriFragmentChangedListener() {
            @Override
            public void uriFragmentChanged(Page.UriFragmentChangedEvent event) {
                router(event.getUriFragment());
            }
        });
        router("");
    }

    private void router(String route) {
        Notification.show(route);
        if (getSession().getAttribute("user") != null) {
            System.out.println("There");
            getNavigator().addView(HeladoUI.NAME, HeladoUI.class);
            getNavigator().addView(SaborUI.NAME, SaborUI.class);
            if (route.equals("!Sabor")) {
                getNavigator().navigateTo(SaborUI.NAME);
            } else {
                getNavigator().navigateTo(HeladoUI.NAME);
            }
        } else {
            getNavigator().addView(RegisterUI.NAME, RegisterUI.class);
            //getNavigator().navigateTo(LoginPage.NAME);
            if(route.equals("!Register")){
                getNavigator().navigateTo(RegisterUI.NAME);
            }
        }
    }
}
