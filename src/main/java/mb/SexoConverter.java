package mb;

import dao.DaoFactory;
import dominio.Sexo;
import dominio.Participante;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "sexoConverter")
public class SexoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            Sexo sexo;
            DaoFactory daofactory = new DaoFactory();
            sexo = daofactory.getSexoDao().findById(Integer.valueOf(value));
            return sexo;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Sexo) {

            Sexo sexo = (Sexo) value;

            return String.valueOf(sexo.getId());
        }
        return "";
    }
}
