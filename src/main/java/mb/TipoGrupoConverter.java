package mb;

import dao.DaoFactory;
import dominio.TipoGrupo;
import dominio.Participante;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "tipoGrupoConverter")
public class TipoGrupoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            TipoGrupo tipoGrupo;
            DaoFactory daofactory = new DaoFactory();
            tipoGrupo = daofactory.getTipoGrupoDao().findById(Integer.valueOf(value));
            return tipoGrupo;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof TipoGrupo) {

            TipoGrupo tipoGrupo = (TipoGrupo) value;

            return String.valueOf(tipoGrupo.getId());
        }
        return "";
    }
}
