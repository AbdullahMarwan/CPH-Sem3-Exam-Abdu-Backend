package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dtos.WashingAssistantDTO;
import entities.WashingAssistant;
import facades.WashingAssistantFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.List;

@Path("washing_assistant")
public class WashingAssistantResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final WashingAssistantFacade FACADE = WashingAssistantFacade.getWashingAssistantFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    //TODO Return ALL WashingAssistants from DTO list (this might return list)
    @GET
    @Produces("application/json")
    @Path("all")
    public List<WashingAssistant> getAllWashingAssistants() {
        Type listOfMyClassObject = new TypeToken<List<WashingAssistant>>() {}.getType();

        List<WashingAssistant> outputList = GSON.fromJson((Reader) FACADE.getWashingAssistants(), listOfMyClassObject);
        return outputList;
    }

    //TODO Return ALL WashingAssistants from DTO list (this might return only string)
    /*
    @GET
    @Produces("application/json")
    @Path("all")
    public String getAllWashingAssistants() {
        return GSON.toJson(FACADE.getWashingAssistants());
    }
     */

    @POST
    @Produces("application/json")
    public String createWashingAssistant(String washingAssistant) {
        WashingAssistantDTO washingAssistantDTO = GSON.fromJson(washingAssistant, WashingAssistantDTO.class);
        WashingAssistantDTO returnedDTO = FACADE.createWashingAssistant(washingAssistantDTO);
        return GSON.toJson(returnedDTO);
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("edit")
    public Response editWashingAssistant(String washingAssistant) {
        WashingAssistantDTO washingAssistantDTO = GSON.fromJson(washingAssistant, WashingAssistantDTO.class);
        WashingAssistantDTO updatedWashingAssistant = FACADE.editWashingAssistant(washingAssistantDTO);

        return Response.ok().entity(updatedWashingAssistant).build();
    }
}
