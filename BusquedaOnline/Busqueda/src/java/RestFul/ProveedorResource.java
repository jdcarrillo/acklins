/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RestFul;


import EntityBeans.Proveedor;
import SessionBeans.ProveedorFacadeRemote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
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

@Path("Proveedor")
public class ProveedorResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of ProveedorResource */
    public ProveedorResource() {
    }

    /**
     * Retrieves representation of an instance of RestFul.ProveedorResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/proveedor/{IDPROVEEDOR}/json")
    @Produces("application/json")
    public String getProveedorJson(@PathParam("IDPROVEEDOR") String IDPROVEEDOR) {
    Integer localIDPROVEEDOR = new Integer((IDPROVEEDOR));
    try {
    Proveedor object = lookupProveedorFacade().find(localIDPROVEEDOR);
            //String NOMBRE = object.getNombrecliente().toString();
            String nombre = object.getNombreproveedor().toString();
            String direccion = object.getDireccionproveedor().toString();
            String ciudad = object.getCiudadproveedor().toString();
            String telefono = object.getTelefonoproveedor().toString();
            String email = object.getEmailproveedor().toString();

            String jsonToReturn = "{\"proveedor\":{\"httpStatus\":\"200\",\"IDPROVEEDOR\":\"" + IDPROVEEDOR + "\",\"nombre\":\"" + nombre + "\",\"ciudad\":\"" + ciudad +  "\",\"direccion\":\"" + direccion + "\",\"telefono\":\"" + telefono + "\",\"email\":\"" + email + "\"}}";
            return jsonToReturn;
        } catch (Exception ex) {
            return "{\"proveedor\":{\"httpStatus\":\"404\",\"Exception\":\"" + ex + "\"}}";
        }
    }
// metodo que muestra una lista de clientes

@GET
@Path("/proveedores/")
public String getProveedores() {
    StringBuilder sb=new StringBuilder();
    sb.append("{\"proveedores\":[\n\t{\"RowNumber\":\""+lookupProveedorFacade().findAll().size()+"\"},\n");
    try {
        List<Proveedor> ls = lookupProveedorFacade().findAll();
        int a=0;
        for (Proveedor s : ls) {
            a++;
            sb.append("{\"IDPROVEEDOR\":\""+s.getIdproveedor().toString()+"\",");
            sb.append("\"NOMBREPROVEEDOR\":\""+s.getNombreproveedor().toString()+"\",");
            sb.append("\"DIRECCIONPROVEEDOR\":\""+s.getDireccionproveedor().toString()+"\",");
            sb.append("\"CIUDADPROVEEDOR\":\""+s.getCiudadproveedor().toString()+"\",");
            sb.append("\"TELEFONOPROVEEDOR\":\""+s.getTelefonoproveedor().toString()+"\",");
            sb.append("\"EMAILPROVEEDOR\":\""+s.getEmailproveedor().toString()+"\"}");

            if(a<ls.size()){
                sb.append(",\n");
            }
            }
        sb.append("\n  ]\n");
        sb.append("}");
        return sb.toString();
    } catch (Exception ex) {
        return "{\"proveedores\":[{\"httpStatus\":\"404\",\"Exception\":\"" + ex + "\"}]}";
    }
}

// metodo para crear un nuevo cliente
    @POST
    @Path("/proveedor/")
    @Produces("text/html")
    public String postProveedor(String content) {
        String localProveedor="";
        try {
            Proveedor objProveedor = new Proveedor();
            localProveedor=QueryStringParser.getParamValue(content, "IDPROVEEDOR");
            String url=context.getAbsolutePath().toString() + "/" + QueryStringParser.getParamValue(content, "IDPROVEEDOR")+"/xml";
            objProveedor.setIdproveedor(new Integer(QueryStringParser.getParamValue(content, "IDPROVEEDOR")));
            objProveedor.setNombreproveedor(new String(QueryStringParser.getParamValue(content, "nombre")));
            objProveedor.setDireccionproveedor(new String(QueryStringParser.getParamValue(content, "direccion")));
            objProveedor.setCiudadproveedor(new String(QueryStringParser.getParamValue(content, "ciudad")));
            objProveedor.setTelefonoproveedor(new String(QueryStringParser.getParamValue(content, "telefono")));
            objProveedor.setEmailproveedor(new String(QueryStringParser.getParamValue(content, "email")));
            lookupProveedorFacade().create(objProveedor);
            return "201 <br/> success Creating proveedor: "+localProveedor+"<br/> <a href=\""+url+"\">"+url+"</a><p/><A HREF=\"javascript:history.go(-1)\">Go back</A>";
        } catch (Exception ex) {
            localProveedor=QueryStringParser.getParamValue(content, "IDPROVEEDOR");
            return "Error Creando proveedor: "+localProveedor+"\n<br/>"+ex.getMessage()+"<p/>\n<A HREF=\"javascript:history.go(-1)\">Try again</A> ";
        }
    }
// metodo para actualizar un cliente

    @PUT
    @Path("/proveedor")
    @Produces("text/html")
    public String putProveedor(String content) {
    String localProveedor="";
    try {
        Proveedor objProveedor = new Proveedor();
        localProveedor = QueryStringParser.getParamValue(content, "IDPROVEEDOR");
        objProveedor.setIdproveedor(new Integer(QueryStringParser.getParamValue(content, "IDPROVEEDOR")));
        objProveedor.setNombreproveedor(new String(QueryStringParser.getParamValue(content, "nombre")));
        objProveedor.setDireccionproveedor(new String(QueryStringParser.getParamValue(content, "direccion")));
        objProveedor.setCiudadproveedor(new String(QueryStringParser.getParamValue(content, "ciudad")));
        objProveedor.setTelefonoproveedor(new String(QueryStringParser.getParamValue(content, "telefono")));
        objProveedor.setEmailproveedor(new String(QueryStringParser.getParamValue(content, "email")));
        lookupProveedorFacade().edit(objProveedor);
        return "{\"proveedor\":{\"httpStatus\":\"200\",\"baseUri\":\"" + context.getAbsolutePath().toString() + "\",\"content\":\"" + content + "\"}}";
    } catch (Exception ex) {
        return "{\"proveedor\":{\"httpStatus\":\"400\",\"Exception\":\"" + ex + "\"}}";
    }
}

    // metodo para eliminar un cliente

@DELETE
@Path("/proveedor/{IDPROVEEDOR}")
public String deleteTeam(@PathParam("IDPROVEEDOR") String IDPROVEEDOR) {
    Proveedor objProveedor = new Proveedor();
    try {
    Integer localIDPROVEEDOR = new Integer((IDPROVEEDOR));
        objProveedor = lookupProveedorFacade().find(localIDPROVEEDOR);
        lookupProveedorFacade().remove(objProveedor);
        return "{\"proveedor\":{\"httpStatus\":\"200\"}}";
    } catch (Exception ex) {
        return "{\"proveedor\":{\"httpStatus\":\"404\",\"Exception\":\"" + ex + "\"}}";
    }
}

    private ProveedorFacadeRemote lookupProveedorFacade() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ProveedorFacadeRemote) c.lookup("java:comp/env/ProveedorFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }


}
