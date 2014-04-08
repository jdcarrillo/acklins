/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RestFul;

import EntityBeans.Catalogo;
import SessionBeans.CatalogoFacadeRemote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import utils.QueryStringParser;

/**
 * REST Web Service
 *
 * @author MAFER
 */

@Path("catalogo")
public class CatalogoResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of CatalogoResource */
    public CatalogoResource() {
    }

    /**
     * Retrieves representation of an instance of RestFul.CatalogoResource
     * @return an instance of java.lang.String
     */

    // metodo para buscar un cliente por IDCLIENTE
    @GET
    @Path("/catalogo/{IDCATALOGO}/json")
    @Produces("application/json")
    public String getCatalogoJson(@PathParam("IDCATALOGO") String IDCATALOGO) {
    Integer localIDCATALOGO = new Integer((IDCATALOGO));
    try {
    Catalogo object = lookupCatalogoFacade().find(localIDCATALOGO);
            //String NOMBRE = object.getNombrecliente().toString();
            String categoria = object.getCategoria().toString();

            String jsonToReturn = "{\"catalogo\":{\"httpStatus\":\"200\",\"IDCATALOGO\":\"" + IDCATALOGO + "\",\"CATEGORIA\":\"" + categoria +  "\"}}";
            return jsonToReturn;
        } catch (Exception ex) {
            return "{\"catalogo\":{\"httpStatus\":\"404\",\"Exception\":\"" + ex + "\"}}";
        }
    }
// metodo que muestra una lista de clientes

@GET
@Path("/catalogos/")
public String getClientes() {
    StringBuilder sb=new StringBuilder();
    sb.append("{\"catalogos\":[\n\t{\"RowNumber\":\""+lookupCatalogoFacade().findAll().size()+"\"},\n");
    try {
        List<Catalogo> ls = lookupCatalogoFacade().findAll();
        int a=0;
        for (Catalogo c : ls) {
            a++;
            sb.append("{\"IDCATALOGO\":\""+c.getIdcatalogo().toString()+"\",");
            //sb.append("{\"IDCLIENTE\":\""+c.getIdcliente().toString()+"\",");
            sb.append("\"CATEGORIA\":\""+c.getCategoria().toString()+"\"}");
            if(a<ls.size()){
                sb.append(",\n");
            }
            }
        sb.append("\n  ]\n");
        sb.append("}");
        return sb.toString();
    } catch (Exception ex) {
        return "{\"catalogos\":[{\"httpStatus\":\"404\",\"Exception\":\"" + ex + "\"}]}";
    }
}

// metodo para crear un nuevo cliente
    @POST
    @Path("/catalogo/")
    @Produces("text/html")
    public String postCliente(String content) {
        String localCatalogo="";
        try {
            Catalogo objCatalogo = new Catalogo();
            localCatalogo=QueryStringParser.getParamValue(content, "IDCATALOGO");
            String url=context.getAbsolutePath().toString() + "/" + QueryStringParser.getParamValue(content, "IDCATALOGO")+"/xml";
            objCatalogo.setIdcatalogo(new Integer(QueryStringParser.getParamValue(content, "IDCATALOGO")));
            objCatalogo.setCategoria(new String(QueryStringParser.getParamValue(content, "categoria")));
            
            lookupCatalogoFacade().create(objCatalogo);
            return "201 <br/> success Creating cliente: "+localCatalogo+"<br/> <a href=\""+url+"\">"+url+"</a><p/><A HREF=\"javascript:history.go(-1)\">Go back</A>";
        } catch (Exception ex) {
            localCatalogo=QueryStringParser.getParamValue(content, "IDCLIENTE");
            return "Error Creando cliente: "+localCatalogo+"\n<br/>"+ex.getMessage()+"<p/>\n<A HREF=\"javascript:history.go(-1)\">Try again</A> ";
        }
    }
// metodo para actualizar un cliente

    @PUT
@Path("/catalogo")
@Produces("text/html")
public String putTeam(String content) {
    String localCatalogo="";
    try {
        Catalogo objCatalogo = new Catalogo();
        localCatalogo = QueryStringParser.getParamValue(content, "IDCATALOGO");
        objCatalogo.setIdcatalogo(new Integer(QueryStringParser.getParamValue(content, "IDCATALOGO")));
        objCatalogo.setCategoria(new String(QueryStringParser.getParamValue(content, "CATEGORIA")));
        lookupCatalogoFacade().edit(objCatalogo);
        return "{\"cliente\":{\"httpStatus\":\"200\",\"baseUri\":\"" + context.getAbsolutePath().toString() + "\",\"content\":\"" + content + "\"}}";
    } catch (Exception ex) {
        return "{\"cliente\":{\"httpStatus\":\"400\",\"Exception\":\"" + ex + "\"}}";
    }
}

    private CatalogoFacadeRemote lookupCatalogoFacade() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CatalogoFacadeRemote) c.lookup("java:comp/env/CatalogoFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }


}
