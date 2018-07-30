package grails.utils;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.DataProviderListener;
import com.vaadin.data.provider.Query;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Registration;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import grails.NewHelado;
import proyectofinalprogweb.Helado;
import proyectofinalprogweb.HeladoService;
import proyectofinalprogweb.User;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class HeladoUI extends VerticalLayout implements View {

    public static final String NAME = "Helado";
    private VerticalLayout mainLayout = new VerticalLayout();
    private Button logout;
    private Button sabor;
    private Label currentUser;

    public HeladoUI() {
        logout = new Button("Logout");
        Grid<Helado> grid = new Grid<>();

        DataProvider<Helado, Object> provider = new DataProvider<Helado, Object>() {
            @Override
            public boolean isInMemory() {
                return false;
            }

            @Override
            public int size(Query<Helado, Object> query) {
                return Grails.get(HeladoService.class).countHelados();
            }

            @Override
            public Stream<Helado> fetch(Query<Helado, Object> query) {
                return Grails.get(HeladoService.class).allHelados().stream();
            }

            @Override
            public void refreshItem(Helado item) {

            }

            @Override
            public void refreshAll() {

            }

            @Override
            public Registration addDataProviderListener(DataProviderListener<Helado> listener) {
                return null;
            }
        };
        // Para ajustar Los themes de la grid solo visitar https://vaadin.com/docs/v8/framework/components/components-grid.html
        // Al final le dice los estilos
        grid.setDataProvider(provider);
        grid.addStyleName(ValoTheme.TABLE_COMPACT); // Poniendo theme compacto a la tabla o grid en vaadin.
        grid.addStyleName("myGrid"); // Personalizado de la Grid

        grid.addColumn(Helado::getDescripcion).setCaption("Descripcion");
        grid.addColumn(Helado::getPrecio).setCaption("Precio");

        Button btnAdd = new Button("Add");
        btnAdd.addStyleName(ValoTheme.BUTTON_PRIMARY); // OTRO estilo de vaadin.
        btnAdd.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                NewHelado helado = new NewHelado();
                helado.addCloseListener(new Window.CloseListener() {
                    @Override
                    public void windowClose(Window.CloseEvent e) {
                        grid.setDataProvider(provider);
                    }
                });
                getUI().addWindow(helado);
            }
        });

        Button btnDelete = new Button("Delete");
        btnDelete.addStyleName(ValoTheme.BUTTON_DANGER); // Adiciono un estilo propio de vaadin.
        btnDelete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Set<Helado> selectedItems = grid.getSelectedItems();
                selectedItems.forEach(new Consumer<Helado>() {
                    @Override
                    public void accept(Helado helado) {
                        Grails.get(HeladoService.class).remove(helado);
                    }
                });
                grid.setDataProvider(provider);
            }
        });


        Button btnEdit = new Button("Edit");
        btnEdit.addStyleName("buttonEdit"); // Estilo propio creando en el theme
        btnEdit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                List<Helado> selectedItems = new ArrayList<>(grid.getSelectedItems());
                if (selectedItems.size() == 1) {
                    NewHelado helado = new NewHelado(selectedItems.get(0));
                    helado.addCloseListener(new Window.CloseListener() {
                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            grid.setDataProvider(provider);
                        }
                    });
                    getUI().addWindow(helado);
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

        sabor = new Button("Crear Sabor");
        sabor.addClickListener(new Button.ClickListener() {
            private static final long serialVersionUID = 1L;
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Page.getCurrent().setUriFragment("!"+ SaborUI.NAME);
            }
        });



        User user = (User) getSession().getAttribute("user");
        if(user !=null){
            currentUser = new Label("");
            addComponent(currentUser);
            setComponentAlignment(currentUser,Alignment.TOP_LEFT);
            addComponent(logout);
            setComponentAlignment(logout, Alignment.TOP_RIGHT);
            user.getRoles().forEach(role -> {
                if(role.getRole().equals("ADMIN")){
                    addComponent(sabor);
                    setComponentAlignment(sabor,Alignment.TOP_RIGHT);
                }

            });
        }


        addComponent(btnEdit);
        addComponent(btnDelete);
        addComponent(btnAdd);
        addComponent(grid);
    }
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Helado view", Notification.Type.HUMANIZED_MESSAGE);
        User user = (User)VaadinSession.getCurrent().getAttribute("user");
        if (user!=null)
            currentUser.setCaption("Welcome, " +user.getName());
    }
}

