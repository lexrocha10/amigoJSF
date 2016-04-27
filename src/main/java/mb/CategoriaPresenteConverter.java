package mb;

import dao.DaoFactory;
import dominio.CategoriaPresente;
import dominio.Participante;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * @author alexrochatsi
 */
@FacesConverter(value = "categoriaPresenteConverter")
public class CategoriaPresenteConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value != null && !value.equals("")) {
            CategoriaPresente categoriaPresente;
            DaoFactory daofactory = new DaoFactory();
            categoriaPresente = daofactory.getCategoriaPresenteDao().findById(Integer.valueOf(value));
            return categoriaPresente;
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value) {
        if (value instanceof CategoriaPresente) {

            CategoriaPresente categoriaPresente = (CategoriaPresente) value;

            return String.valueOf(categoriaPresente.getId());
        }
        return "";
    }
}
