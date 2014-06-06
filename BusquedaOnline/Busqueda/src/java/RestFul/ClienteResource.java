/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package RestFul;

import EntityBeans.Cliente;
import SessionBeans.ClienteFacadeRemote;
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

@Path("cliente")
public class ClienteResource {
    @Context
    private UriInfo context;

    /** Creates a new instance of ClienteResource */
    public ClienteResource() {
    }

    /**
     * Retrieves representation of an instance of RestFul.ClienteResource
     * @return an instance of java.lang.String
     */

    // metodo para buscar un cliente por IDCLIENTE
    @GET
    @Path("/cliente/{IDCLIENTE}/json")
    @Produces("application/json")
    public String getClienteJson(@PathParam("IDCLIENTE") String IDCLIENTE) {
    Integer localIDCLIENTE = new Integer((IDCLIENTE));
    try {
    Cliente object = lookupClienteFacade().find(localIDCLIENTE);
            //String NOMBRE = object.getNombrecliente().toString();
            String nombre = object.getNombrecliente().toString();
            String apellido = object.getApellidocliente().toString();
            String ciudad = object.getCiudadcliente().toString();
            String direccion = object.getDireccioncliente().toString();
            String telefono = object.getTelefonocliente().toString();
            String email = object.getEmailcliente().toString();
            String login = object.getLogin().toString();
            String password = object.getPassword().toString();

            String jsonToReturn = "{\"cliente\":{\"httpStatus\":\"200\",\"IDCLIENTE\":\"" + IDCLIENTE + "\",\"nombre\":\"" + nombre + "\",\"apellido\":\"" + apellido + "\",\"ciudad\":\"" + ciudad +  "\",\"direccion\":\"" + direccion + "\",\"telefono\":\"" + telefono + "\",\"email\":\"" + email + "\", \"login\":\"" + login + "\",\"password\":\"" + password + "\"}}";
            return jsonToReturn;
        } catch (Exception ex) {
            return "{\"cliente\":{\"httpStatus\":\"404\",\"Exception\":\"" + ex + "\"}}";
        }
    }
// metodo que muestra una lista de clientes

@GET
@Path("/clientes/")
public String getClientes() {
    StringBuilder sb=new StringBuilder();
    sb.append("{\"clientes\":[\n\t{\"RowNumber\":\""+lookupClienteFacade().findAll().size()+"\"},\n");
    try {
        List<Cliente> ls = lookupClienteFacade().findAll();
        int a=0;
        for (Cliente s : ls) {
            a++;
            sb.append("{\"IDCLIENTE\":\""+s.getIdcliente().toString()+"\",");
            sb.append("\"NOMBRECLIENTE\":\""+s.getNombrecliente().toString()+"\",");
            sb.append("\"APELLIDOCLIENTE\":\""+s.getApellidocliente().toString()+"\",");
            sb.append("\"CIUDADCLIENTE\":\""+s.getCiudadcliente().toString()+"\",");
            sb.append("\"DIRECCIONCLIENTE\":\""+s.getDireccioncliente().toString()+"\",");
            sb.append("\"TELEFONOCLIENTE\":\""+s.getTelefonocliente().toString()+"\",");
            sb.append("\"EMAILCLIENTE\":\""+s.getEmailcliente().toString()+"\",");
            sb.append("\"LOGIN\":\""+s.getLogin().toString()+"\",");
            sb.append("\"PASSWORD\":\""+s.getPassword().toString()+"\"}");
            if(a<ls.size()){
                sb.append(",\n");
            }
            }
        sb.append("\n  ]\n");
        sb.append("}");
        return sb.toString();
    } catch (Exception ex) {
        return "{\"clientes\":[{\"httpStatus\":\"404\",\"Exception\":\"" + ex + "\"}]}";
    }
}

// metodo para crear un nuevo cliente
    @POST
    @Path("/cliente/")
    @Produces("text/html")
    public String postCliente(String content) {
        String localCliente="";
        try {
            Cliente objCliente = new Cliente();
            localCliente=QueryStringParser.getParamValue(content, "IDCLIENTE");
            String url=context.getAbsolutePath().toString() + "/" + QueryStringParser.getParamValue(content, "IDCLIENTE")+"/xml";
            objCliente.setIdcliente(new Integer(QueryStringParser.getParamValue(content, "IDCLIENTE")));
            objCliente.setNombrecliente(new String(QueryStringParser.getParamValue(content, "nombre")));
            objCliente.setApellidocliente(new String(QueryStringParser.getParamValue(content, "apellido")));
            objCliente.setCiudadcliente(new String(QueryStringParser.getParamValue(content, "ciudad")));
            objCliente.setDireccioncliente(new String(QueryStringParser.getParamValue(content, "direccion")));
            objCliente.setTelefonocliente(new String(QueryStringParser.getParamValue(content, "telefono")));
            objCliente.setEmailcliente(new String(QueryStringParser.getParamValue(content, "email")));
            objCliente.setLogin(new String(QueryStringParser.getParamValue(content, "login")));
            objCliente.setPassword(new String(QueryStringParser.getParamValue(content, "password")));
            lookupClienteFacade().create(objCliente);
            return "201 <br/> success Creating cliente: "+localCliente+"<br/> <a href=\""+url+"\">"+url+"</a><p/><A HREF=\"javascript:history.go(-1)\">Go back</A>";
        } catch (Exception ex) {
            localCliente=QueryStringParser.getParamValue(content, "IDCLIENTE");
            return "Error Creando cliente: "+localCliente+"\n<br/>"+ex.getMessage()+"<p/>\n<A HREF=\"javascript:history.go(-1)\">Try again</A> ";
        }
    }
// metodo para actualizar un cliente

    @PUT
@Path("/cliente")
@Produces("text/html")
public String putCliente(String content) {
    String localCliente="";
    try {
        Cliente objCliente = new Cliente();
        localCliente = QueryStringParser.getParamValue(content, "IDCLIENTE");
        objCliente.setIdcliente(new Integer(QueryStringParser.getParamValue(content, "IDCLIENTE")));
        objCliente.setNombrecliente(new String(QueryStringParser.getParamValue(content, "NOMBRECLIENTE")));
        objCliente.setApellidocliente(new String(QueryStringParser.getParamValue(content, "APELLIDOCLIENTE")));
        objCliente.setCiudadcliente(new String(QueryStringParser.getParamValue(content, "CIUDADCLIENTE")));
        objCliente.setDireccioncliente(new String(QueryStringParser.getParamValue(content, "DIRECCIONCLIENTE")));
        objCliente.setTelefonocliente(new String(QueryStringParser.getParamValue(content, "TELEFONOCLIENTE")));
        objCliente.setEmailcliente(new String(QueryStringParser.getParamValue(content, "EMAILCLIENTE")));
        objCliente.setLogin(new String(QueryStringParser.getParamValue(content, "LOGIN")));
        objCliente.setPassword(new String(QueryStringParser.getParamValue(content, "PASSWORD")));
        lookupClienteFacade().edit(objCliente);
        return "{\"cliente\":{\"httpStatus\":\"200\",\"baseUri\":\"" + context.getAbsolutePath().toString() + "\",\"content\":\"" + content + "\"}}";
    } catch (Exception ex) {
        return "{\"cliente\":{\"httpStatus\":\"400\",\"Exception\":\"" + ex + "\"}}";
    }
}

    // metodo para eliminar un cliente

@DELETE
@Path("/cliente/{IDCLIENTE}")
public String deleteTeam(@PathParam("IDCLIENTE") String IDCLIENTE) {
    Cliente objCliente = new Cliente();
    try {
    Integer localIDCLIENTE = new Integer((IDCLIENTE));
        objCliente = lookupClienteFacade().find(localIDCLIENTE);
        lookupClienteFacade().remove(objCliente);
        return "{\"cliente\":{\"httpStatus\":\"200\"}}";
    } catch (Exception ex) {
        return "{\"cliente\":{\"httpStatus\":\"404\",\"Exception\":\"" + ex + "\"}}";
    }
}

    private ClienteFacadeRemote lookupClienteFacade() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ClienteFacadeRemote) c.lookup("java:comp/env/ClienteFacade");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }




}
