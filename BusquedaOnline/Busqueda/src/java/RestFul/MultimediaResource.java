/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RestFul;

import EntityBeans.Multimedia;
import SessionBeans.MultimediaFacadeRemote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import utils.QueryStringParser;

/**
 * REST Web Service
 *
 * @author MAFER
 */

@Path("Multimedia")
public class MultimediaResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of MultimediaResource */
    public MultimediaResource() {
    }

    /**
     * Retrieves representation of an instance of RestFul.MultimediaResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/multimedia/{IDMULTIMEDIA}/json")
    @Produces("application/json")
    public String getProveedorJson(@PathParam("IDMULTIMEDIA") String IDMULTIMEDIA) {
    Integer localIDMULTIMEDIA = new Integer((IDMULTIMEDIA));
    try {
    Multimedia object = lookupMultimediaFacade().find(localIDMULTIMEDIA);
            //String NOMBRE = object.getNombrecliente().toString();
            String tipocontenido = object.getTipocontenido().toString();
            String descripcion = object.getDescripcion().toString();
            String api = object.getApi().toString();


            String jsonToReturn = "{\"multimedia\":{\"httpStatus\":\"200\",\"IDMULTIMEDIA\":\"" + IDMULTIMEDIA + "\",\"tipo\":\"" + tipocontenido +  "\",\"descripcion\":\"" + descripcion + "\",\"api\":\"" + api +  "\"}}";
            return jsonToReturn;
        } catch (Exception ex) {
            return "{\"multimedia\":{\"httpStatus\":\"404\",\"Exception\":\"" + ex + "\"}}";
        }
    }
// metodo que muestra una lista de clientes

@GET
@Path("/multimedias/")
public String getMultimedias() {
    StringBuilder sb=new StringBuilder();
    sb.append("{\"multimedias\":[\n\t{\"RowNumber\":\""+lookupMultimediaFacade().findAll().size()+"\"},\n");
    try {
        List<Multimedia> ls = lookupMultimediaFacade().findAll();
        int a=0;
        for (Multimedia s : ls) {
            a++;
            sb.append("{\"IDMULTIMEDIA\":\""+s.getIdmultimedia().toString()+"\",");
            sb.append("\"TIPOCONTENIDO\":\""+s.getTipocontenido().toString()+"\",");
            sb.append("\"DESCRIPCION\":\""+s.getDescripcion().toString()+"\",");
            sb.append("\"API\":\""+s.getApi().toString()+"\"}");

            if(a<ls.size()){
                sb.append(",\n");
            }
            }
        sb.append("\n  ]\n");
        sb.append("}");
        return sb.toString();
    } catch (Exception ex) {
        return "{\"multimedias\":[{\"httpStatus\":\"404\",\"Exception\":\"" + ex + "\"}]}";
    }
}

// metodo para crear un nuevo cliente
    @POST
    @Path("/multimedia/")
    @Produces("text/html")
    public String postMultimedia(String content) {
        String localMultimedia="";
        try {
            Multimedia objMultimedia = new Multimedia();
            localMultimedia=QueryStringParser.getParamValue(content, "IDMULTIMEDIA");
            String url=context.getAbsolutePath().toString() + "/" + QueryStringParser.getParamValue(content, "IDMULTIMEDIA")+"/xml";
            objMultimedia.setIdmultimedia(new Integer(QueryStringParser.getParamValue(content, "IDMULTIMEDIA")));
            objMultimedia.setTipocontenido(new String(QueryStringParser.getParamValue(content, "tipo")));
            objMultimedia.setDescripcion(new String(QueryStringParser.getParamValue(content, "descripcion")));
            objMultimedia.setApi(new String(QueryStringParser.getParamValue(content, "api")));
            lookupMultimediaFacade().create(objMultimedia);
            return "201 <br/> success Creating multimedia: "+localMultimedia+"<br/> <a href=\""+url+"\">"+url+"</a><p/><A HREF=\"javascript:history.go(-1)\">Go back</A>";
        } catch (Exception ex) {
            localMultimedia=QueryStringParser.getParamValue(content, "IDMULTIMEDIA");
            return "Error Creando multimedia: "+localMultimedia+"\n<br/>"+ex.getMessage()+"<p/>\n<A HREF=\"javascript:history.go(-1)\">Try again</A> ";
        }
    }
// metodo para actualizar un cliente

    @PUT
    @Path("/multimedia")
    @Produces("text/html")
    public String putTeam(String content) {
    String localMultimedia="";
    try {
        Multimedia objMultimedia = new Multimedia();
        localMultimedia = QueryStringParser.getParamValue(content, "IDMULTIMEDIA");
        objMultimedia.setIdmultimedia(new Integer(QueryStringParser.getParamValue(content, "IDMULTIMEDIA")));
        objMultimedia.setTipocontenido(new String(QueryStringParser.getParamValue(content, "TIPOCONTENIDO")));
        objMultimedia.setDescripcion(new String(QueryStringParser.getParamValue(content, "DESCRIPCION")));
        objMultimedia.setApi(new String(QueryStringParser.getParamValue(content, "API")));

      lookupMultimediaFacade().edit(objMultimedia);
        return "{\"multimedia\":{\"httpStatus\":\"200\",\"baseUri\":\"" + context.getAbsolutePath().toString() + "\",\"content\":\"" + content + "\"}}";
    } catch (Exception ex) {
        return "{\"multimedia\":{\"httpStatus\":\"400\",\"Exception\":\"" + ex + "\"}}";
    }
}

    // metodo para eliminar un cliente

@DELETE
@Path("/multimedia/{IDMULTIMEDIA}")
public String deleteTeam(@PathParam("IDMULTIMEDIA") String IDMULTIMEDIA) {
    Multimedia objMultimedia = new Multimedia();
    try {
    Integer localIDMULTIMEDIA = new Integer((IDMULTIMEDIA));
        objMultimedia = lookupMultimediaFacade().find(localIDMULTIMEDIA);
        lookupMultimediaFacade().remove(objMultimedia);
        return "{\"multimedia\":{\"httpStatus\":\"200\"}}";
    } catch (Exception ex) {
        return "{\"multimedia\":{\"httpStatus\":\"404\",\"Exception\":\"" + ex + "\"}}";
    }
}

    private MultimediaFacadeRemote lookupMultimediaFacade() {
        try {
            javax.naming.Context c = new InitialContext();
            return (MultimediaFacadeRemote) c.lookup("java:comp/env/MultimediaFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }



}
