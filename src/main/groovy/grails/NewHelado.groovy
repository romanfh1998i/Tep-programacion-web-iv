package grails

import com.vaadin.data.HasValue;
import com.vaadin.ui.*;
import grails.utils.Grails;
import proyectofinalprogweb.Helado;
import proyectofinalprogweb.HeladoService;
import proyectofinalprogweb.Sabor;
import proyectofinalprogweb.SaborService;
import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;
import java.util.Locale;


import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * Created by aluis on 6/1/18.
 */
public class NewHelado extends Window {


    private TextArea descripcion = new TextArea("Descripcion");
    private TextField precio = new TextField("Precio");
    private CheckBoxGroup<Sabor> sabores= new CheckBoxGroup<Sabor>("Sabores");

    private Button btnOK = new Button("OK");
    private Button btnCancel = new Button("Cancel");

    private Helado helado;

    public NewHelado(Helado helado) {
        this.helado = helado;
        prepare();
    }

    public NewHelado() {
        prepare();
    }

    private void prepare() {
        center();
        VerticalLayout main = new VerticalLayout();

        sabores.setItems(Grails.get(SaborService.class).allSabors());
        sabores.setItemCaptionGenerator(new ItemCaptionGenerator<Sabor>() {
            @Override
            String apply(Sabor item) {
                return item.getName()
            }
        })
        main.addComponent(descripcion);
        main.addComponent(precio);
        main.addComponent(sabores);

        btnOK.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (helado == null) {
                    helado = new Helado();
                }

                helado.setDescripcion(descripcion.getValue())
                BigDecimal bdPrecio = stringToBigDecimal(precio.getValue(), Locale.ENGLISH)
                helado.setPrecio(bdPrecio);
                Set<Sabor> sabors = new HashSet();
                sabores.addValueChangeListener(new HasValue.ValueChangeListener<Set<Sabor>>() {
                    @Override
                    void valueChange(HasValue.ValueChangeEvent<Set<Sabor>> event1) {


                        if(event1.getValue().isEmpty()) {
                            Notification.show("Debes elegir uno o mas sabores para el helado");
                        } else {
                            Notification.show("Seleccionando");
                            sabors= event1.getValue();

                        }
                    }
                })

                Grails.get(HeladoService.class).createHelado(helado, sabors);

                close();
            }
        });

        btnCancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                close();
            }
        });

        HorizontalLayout hlControls = new HorizontalLayout();
        hlControls.addComponent(btnOK);
        hlControls.addComponent(btnCancel);
        main.addComponent(hlControls);
        setContent(main);

        if (helado != null) {
            description.setValue(helado.getDescripcion() != null ? helado.getDescripcion() : "");
            precio.setValue(helado.getPrecio().toString());
            sabores.setValue(helado.sabores);
        }
    }
    private static BigDecimal stringToBigDecimal(final String formattedString,
                                                 final Locale locale)
    {
        final DecimalFormatSymbols symbols;
        final char                 groupSeparatorChar;
        final String               groupSeparator;
        final char                 decimalSeparatorChar;
        final String               decimalSeparator;
        String                     fixedString;
        final BigDecimal           number;

        symbols              = new DecimalFormatSymbols(locale);
        groupSeparatorChar   = symbols.getGroupingSeparator();
        decimalSeparatorChar = symbols.getDecimalSeparator();

        if(groupSeparatorChar == '.')
        {
            groupSeparator = "\\" + groupSeparatorChar;
        }
        else
        {
            groupSeparator = Character.toString(groupSeparatorChar);
        }

        if(decimalSeparatorChar == '.')
        {
            decimalSeparator = "\\" + decimalSeparatorChar;
        }
        else
        {
            decimalSeparator = Character.toString(decimalSeparatorChar);
        }

        fixedString = formattedString.replaceAll(groupSeparator , "");
        fixedString = fixedString.replaceAll(decimalSeparator , ".");
        number      = new BigDecimal(fixedString);

        return (number);
    }
}
