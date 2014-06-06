/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.DELETE;

/**
 * REST Web Service
 *
 * @author root
 */
public class EstadoConexionResource {

    private String idTienda;

    /**
     * Creates a new instance of EstadoConexionResource
     */
    private EstadoConexionResource(String idTienda) {
        this.idTienda = idTienda;
    }

    /**
     * Get instance of the EstadoConexionResource
     */
    public static EstadoConexionResource getInstance(String idTienda) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of EstadoConexionResource class.
        return new EstadoConexionResource(idTienda);
    }

    /**
     * Retrieves representation of an instance of Restfull.EstadoConexionResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of EstadoConexionResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }

    /**
     * DELETE method for resource EstadoConexionResource
     */
    @DELETE
    public void delete() {
    }
}
