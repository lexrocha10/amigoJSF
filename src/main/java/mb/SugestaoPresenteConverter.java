package mb;

import dao.DaoFactory;
import dominio.SugestaoPresente;
import dominio.Participante;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "sugestaoPresenteConverter")
public class SugestaoPresenteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            SugestaoPresente sugestaoPresente;
            DaoFactory daofactory = new DaoFactory();
            sugestaoPresente = daofactory.getSugestaoPresenteDao().findById(Integer.valueOf(value));
            return sugestaoPresente;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof SugestaoPresente) {

            SugestaoPresente sugestaoPresente = (SugestaoPresente) value;

            return String.valueOf(sugestaoPresente.getId());
        }
        return "";
    }
}
