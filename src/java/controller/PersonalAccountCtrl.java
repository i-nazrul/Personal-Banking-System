/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Personalaccount;
import java.util.List;
import java.util.Map;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import service.PersonalAccountServ;

/**
 *
 * @author Nazrul
 */
@Path("/account")
public class PersonalAccountCtrl {
    
    PersonalAccountServ pas=new PersonalAccountServ();
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Map<String, String>> getEntries() {

        List<Map<String, String>> listOfEntries = pas.getAllEntries();
        return listOfEntries;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Personalaccount getEntryById(@PathParam("id") int id) {
        return pas.getEntry(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Personalaccount addEntry(Personalaccount pa) {
        return pas.addEntry(pa);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Personalaccount updateEntry(Personalaccount pa) {
        return pas.updateEntry(pa);

    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteEntry(@PathParam("id") int id) {
        pas.deleteEntry(id);

    }

    
    
}
