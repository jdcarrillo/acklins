/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RestFul;

import EntityBeans.Promocion;
import SessionBeans.PromocionFacadeRemote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * REST Web Service
 *
 * @author MAFER
 */

@Path("Promocion")
public class PromocionResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of PromocionResource */
    public PromocionResource() {
    }

    /**
     * Retrieves representation of an instance of RestFul.PromocionResource
     * @return an instance of java.lang.String
     */
  @GET
    @Path("/promocion/{IDPROMOCION}/json")
    @Produces("application/json")
    public String getPromocionJson(@PathParam("IDPROMOCION") String IDPROMOCION) {
    Integer localIDPROMOCION = new Integer((IDPROMOCION));
    try {
    Promocion object=lookupPromocionFacade().find(localIDPROMOCION);


            String  tipo= (object.getTipo().toString());
            String descuento = (object.getDescuento().toString());


            lookupPromocionFacade().calculoDescuento(localIDPROMOCION);
            Float totaldescuento = new Float ((lookupPromocionFacade().find(localIDPROMOCION).getTotalDescuento()));

            String jsonToReturn = "{\"promocion\":{\"httpStatus\":\"200\",\"IDPROMOCION\":\"" + IDPROMOCION + "\",\"TIPO\":\"" + tipo + "\",\"DESCUENTO\":\"" + descuento  + "\",\"TOTALDESCUENTO\":\"" + totaldescuento + "\"}}";
            return jsonToReturn;

        } catch (Exception ex) {
            return "{\"promocion\":{\"httpStatus\":\"404\",\"Exception\":\"" + ex + "\"}}";
        }
    }

    // lista de productos

    @GET
    @Path("/promociones/")
    public String getPromociones() {
    StringBuilder sb=new StringBuilder();
    sb.append("{\"promociones\":[\n\t{\"RowNumber\":\""+lookupPromocionFacade().findAll().size()+"\"},\n");
    try {
        List<Promocion> ls = lookupPromocionFacade().findAll();
        int a=0;
        for (Promocion p : ls) {
            a++;
            sb.append("{\"IDPROMOCION\":\""+p.getIdpromocion()+"\",");
            //sb.append("\"IDCATALOGO\":\""+p.getIdcatalogo().toString()+"\",");
            sb.append("\"TIPO\":\""+p.getTipo()+"\",");
            sb.append("\"DESCUENTO\":\""+p.getDescuento()+"\",");
            sb.append("\"TOTALDESCUENTO\":\""+p.getTotalDescuento()+"\"}");
            if(a<ls.size()){
            sb.append(",\n");
            }
            }
        sb.append("\n  ]\n");
        sb.append("}");
        return sb.toString();
    } catch (Exception ex) {
        return "{\"productoss\":[{\"httpStatus\":\"404\",\"Exception\":\"" + ex + "\"}]}";
    }
}

    private PromocionFacadeRemote lookupPromocionFacade() {
        try {
            javax.naming.Context c = new InitialContext();
            return (PromocionFacadeRemote) c.lookup("java:comp/env/PromocionFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }





}
