/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RestFul;

import SessionBeans.ProductoFacadeRemote;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

/**
 * REST Web Service
 *
 * @author MAFER
 */

public class ProductoResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of ProductoResource */
    public ProductoResource() {
    }

    /**
     * Retrieves representation of an instance of RestFul.ProductoResource
     * @param id resource URI parameter
     * @return an instance of EntityBeans.Producto
     */
//    @GET
//    @Path("Producto/json")
//    @Produces("application/json")
//    public EntityBeans.Producto getJson(@PathParam("id")
//    String id) {
//        return lookupProductoFacade().find(new Integer (id));
//    }
    /**
     * PUT method for updating or creating an instance of ProductoResource
     * @param id resource URI parameter
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(@PathParam("id")
    String id, EntityBeans.Producto content) {
    }

    /**
     * DELETE method for resource ProductoResource
     * @param id resource URI parameter
     */
    @DELETE
    public void delete(@PathParam("id")
    String id) {
    }

    private ProductoFacadeRemote lookupProductoFacade() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ProductoFacadeRemote) c.lookup("java:comp/env/ProductoFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }




}
