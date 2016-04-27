package mb;

import dao.DaoFactory;
import dominio.Participante;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "participanteConverter")
public class ParticipanteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            Participante participante;
            DaoFactory daofactory = new DaoFactory();
            participante = daofactory.getParticipanteDao().findById(Integer.valueOf(value));
            return participante;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof Participante) {

            Participante participante = (Participante) value;

            return String.valueOf(participante.getId());
        }
        return "";
    }
}
