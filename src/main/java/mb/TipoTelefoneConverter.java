package mb;

import dao.DaoFactory;
import dominio.TipoTelefone;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "tipoTelefoneConverter")
public class TipoTelefoneConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            TipoTelefone tipoTelefone;
            DaoFactory daofactory = new DaoFactory();
            tipoTelefone = daofactory.getTipoTelefoneDao().findById(Integer.valueOf(value));
            return tipoTelefone;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof TipoTelefone) {

            TipoTelefone tipoTelefone = (TipoTelefone) value;

            return String.valueOf(tipoTelefone.getId());
        }
        return "";
    }
}
