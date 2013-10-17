/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ziez.bdl.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ziez
 */
@FacesConverter("StringCaseConverter")
public class StringCaseConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        StringBuilder sb = new StringBuilder(value);
        int i = 0;
        do {
            sb.replace(i, i + 1, sb.substring(i, i + 1).toUpperCase());
            i = sb.indexOf(" ", i) + 1;
        } while (i > 0 && i < sb.length());
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
}
