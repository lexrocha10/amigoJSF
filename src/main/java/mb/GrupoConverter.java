package mb;

import dao.DaoFactory;
import dominio.Grupo;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "grupoConverter")
public class GrupoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            Grupo grupo;
            DaoFactory daofactory = new DaoFactory();
            grupo = daofactory.getGrupoDao().findById(Integer.valueOf(value));
            return grupo;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Grupo) {

            Grupo grupo = (Grupo) value;

            return String.valueOf(grupo.getId());
        }
        return "";
    }
}
