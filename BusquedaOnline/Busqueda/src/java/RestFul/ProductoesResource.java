/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RestFul;

import EntityBeans.Producto;
import SessionBeans.ProductoFacadeRemote;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import utils.QueryStringParser;

/**
 * REST Web Service
 *
 * @author MAFER
 */

@Path("/productoes")
public class ProductoesResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of ProductoesResource */
    public ProductoesResource() {
    }

    /**
     * Retrieves representation of an instance of RestFul.ProductoesResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/producto/{IDPRODUCTO}/json")
    @Produces("application/json")
    public String getProductoJson(@PathParam("IDPRODUCTO") String IDPRODUCTO) {
    Integer localIDPRODUCTO = new Integer((IDPRODUCTO));
    try {
    Producto object=lookupProductoFacade().find(localIDPRODUCTO);
            //String catnum = (new Integer ( object. .getCatnum()).toString());

            String  marca= (object.getMarca().toString());
            String garantia= (object.getGarantia().toString());
            String nombreproducto = (object.getNombreproducto().toString());
            String preciosiniva = (object.getPreciosiniva().toString());

            //lookupProductoFacade().calculoIva(object);//.edit(object);//
            lookupProductoFacade().calculoIva(localIDPRODUCTO);//.edit(objectProducto);//
            Float precioconiva = new Float ((lookupProductoFacade().find(localIDPRODUCTO).getPrecioconiva()));

            String jsonToReturn = "{\"producto\":{\"httpStatus\":\"200\",\"IDPRODUCTO\":\"" + IDPRODUCTO + "\",\"MARCA\":\"" + marca + "\",\"GARANTIA\":\"" + garantia  + "\",\"NOMBREPRODUCTO\":\"" + nombreproducto + "\",\"PRECIOSINIVA\":\"" + preciosiniva + "\",\"PRECIOCONIVA\":\"" + precioconiva + "\"}}";
            return jsonToReturn;

        } catch (Exception ex) {
            return "{\"producto\":{\"httpStatus\":\"404\",\"Exception\":\"" + ex + "\"}}";
        }
    }

    // lista de productos

    @GET
    @Path("/productos/")
    public String getProductos() {
    StringBuilder sb=new StringBuilder();
    sb.append("{\"productos\":[\n\t{\"RowNumber\":\""+lookupProductoFacade().findAll().size()+"\"},\n");
    try {
        List<Producto> ls = lookupProductoFacade().findAll();
        int a=0;
        for (Producto p : ls) {
            a++;
            sb.append("{\"IDPRODUCTO\":\""+p.getIdproducto()+"\",");
            //sb.append("\"IDCATALOGO\":\""+p.getIdcatalogo().toString()+"\",");
            sb.append("\"MARCA\":\""+p.getMarca()+"\",");
            sb.append("\"GARANTIA\":\""+p.getGarantia()+"\",");
            sb.append("\"NOMBREPRODUCTO\":\""+p.getNombreproducto()+"\",");
            sb.append("\"PRECIOSINIVA\":\""+p.getPreciosiniva()+"\",");
            sb.append("\"PRECIOCONIVA\":\""+p.getPrecioconiva()+"\"}");
            if(a<ls.size()){
            sb.append(",\n");
            }
            }
        sb.append("\n  ]\n");
        sb.append("}");
        return sb.toString();
    } catch (Exception ex) {
        return "{\"productos\":[{\"httpStatus\":\"404\",\"Exception\":\"" + ex + "\"}]}";
    }
}


 

    /**
     * POST method for creating an instance of ProductoesResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
     @POST
     @Path("/producto/")
     @Produces("text/html")
     public String postProducto(String content) {
        String localProducto="";
        try {
            Producto objProducto = new Producto();
            localProducto=QueryStringParser.getParamValue(content, "IDPRODUCTO");
            String url=context.getAbsolutePath().toString() + "/" + QueryStringParser.getParamValue(content, "IDPRODUCTO")+"/xml";
            objProducto.setIdproducto(new Integer(QueryStringParser.getParamValue(content, "IDPRODUCTO")));
            objProducto.setMarca(new String(QueryStringParser.getParamValue(content, "marca")));
            objProducto.setGarantia(new String(QueryStringParser.getParamValue(content, "garantia")));
            objProducto.setNombreproducto(new String(QueryStringParser.getParamValue(content, "nombre")));
            objProducto.setPreciosiniva(new Float (QueryStringParser.getParamValue(content, "preciosiniva")));
            objProducto.setPrecioconiva(new Float(QueryStringParser.getParamValue(content, "precioconiva")));

            lookupProductoFacade().create(objProducto);
            return "201 <br/> success Creating producto: "+localProducto+"<br/> <a href=\""+url+"\">"+url+"</a><p/><A HREF=\"javascript:history.go(-1)\">Go back</A>";
        } catch (Exception ex) {
            localProducto=QueryStringParser.getParamValue(content, "IDPRODUCTO");
            return "Error Creando producto: "+localProducto+"\n<br/>"+ex.getMessage()+"<p/>\n<A HREF=\"javascript:history.go(-1)\">Try again</A> ";
        }
    }
// metodo para actualizar un cliente

    @PUT
    @Path("/producto")
    @Produces("text/html")
    public String putProducto(String content) {
        String localProducto="";
    try {
        Producto objProducto = new Producto();
        localProducto = QueryStringParser.getParamValue(content, "IDPRODUCTO");
        objProducto.setIdproducto(new Integer(QueryStringParser.getParamValue(content, "IDPRODUCTO")));
        objProducto.setMarca(new String(QueryStringParser.getParamValue(content, "MARCA")));
        objProducto.setGarantia(new String(QueryStringParser.getParamValue(content, "GARANTIA")));
        objProducto.setNombreproducto(new String(QueryStringParser.getParamValue(content, "NOMBREPRODUCTO")));
        objProducto.setPreciosiniva(new Float(QueryStringParser.getParamValue(content, "PRECIOSINIVA")));
        objProducto.setPrecioconiva(new Float(QueryStringParser.getParamValue(content, "PRECIOCONIVA")));

        lookupProductoFacade().edit(objProducto);
        return "{\"producto\":{\"httpStatus\":\"200\",\"baseUri\":\"" + context.getAbsolutePath().toString() + "\",\"content\":\"" + content + "\"}}";
    } catch (Exception ex) {
        return "{\"producto\":{\"httpStatus\":\"400\",\"Exception\":\"" + ex + "\"}}";
    }
}

    // metodo para eliminar un cliente

@DELETE
@Path("/producto/{IDPRODUCTO}")
public String deleteTeam(@PathParam("IDPRODUCTO") String IDPRODUCTO) {
    Producto objProducto = new Producto();
    try {
    Integer localIDPRODUCTO = new Integer((IDPRODUCTO));
        objProducto = lookupProductoFacade().find(localIDPRODUCTO);
        lookupProductoFacade().remove(objProducto);
        return "{\"producto\":{\"httpStatus\":\"200\"}}";
    } catch (Exception ex) {
        return "{\"producto\":{\"httpStatus\":\"404\",\"Exception\":\"" + ex + "\"}}";
    }
}

    /**
     * Sub-resource locator method for  {id}
     */
    @Path("{id}")
    public ProductoResource getProductoResource() {
        return new ProductoResource();
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
