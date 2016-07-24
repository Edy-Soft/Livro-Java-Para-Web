package ao.co.always.financeiro.web.converter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import ao.co.always.financeiro.categoria.Categoria;
import ao.co.always.financeiro.categoria.CategoriaRN;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, 
				UIComponent component, String value){
		if(value != null && value.trim().length() > 0){
			Integer codigo = Integer.valueOf(value);
			try{
				CategoriaRN categoriaRN = new CategoriaRN();
				return categoriaRN.carregar(codigo);
			}catch(Exception e){
				throw new ConverterException("Não foi possvel encontrar a categoria de código"
						+ value + ". " + e.getMessage());
			}
		}
		return null;
	}
	@Override
	public String getAsString(FacesContext context, UIComponent componente, Object value){
		if(value != null){
			Categoria categoria = (Categoria) value;
			return categoria.getIdCategoria().toString();
		}
		return "";
	}
}
