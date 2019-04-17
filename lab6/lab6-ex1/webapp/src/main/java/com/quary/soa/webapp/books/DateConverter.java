package com.quary.soa.webapp.books;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@FacesConverter("dateConverter")
public class DateConverter implements Converter<Date> {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Date getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        try {
            return DATE_FORMAT.parse(s);
        } catch (ParseException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Date date) {
        return DATE_FORMAT.format(date);
    }
}
