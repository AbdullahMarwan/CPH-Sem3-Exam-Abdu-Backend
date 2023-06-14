package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.CarDTO;
import facades.CarFacade;
import facades.CarFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("car")
public class CarResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final CarFacade FACADE = CarFacade.getCarFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces("application/json")
    @Path("all")
    public String getAllCars() {
        return GSON.toJson(FACADE.getCars());
    }

    @POST
    @Produces("application/json")
    public String createCar(String car) {
        CarDTO carDTO = GSON.fromJson(car, CarDTO.class);
        CarDTO returnedDTO = FACADE.createCar(carDTO);
        return GSON.toJson(returnedDTO);
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("edit")
    public Response editCar(String car) {
        CarDTO carDTO = GSON.fromJson(car, CarDTO.class);
        CarDTO updatedCar = FACADE.editCar(carDTO);

        return Response.ok().entity(updatedCar).build();
    }
}
