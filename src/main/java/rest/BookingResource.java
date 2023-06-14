package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.BookingDTO;
import facades.BookingFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

public class BookingResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final BookingFacade FACADE = BookingFacade.getBookingFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces("application/json")
    @Path("all")
    public String getAllCars() {
        return GSON.toJson(FACADE.getBookings());
    }

    @POST
    @Produces("application/json")
    public String createBooking(String booking) {
        BookingDTO bookingDTO = GSON.fromJson(booking, BookingDTO.class);
        BookingDTO returnedDTO = FACADE.createBooking(bookingDTO);
        return GSON.toJson(returnedDTO);
    }

    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    @Path("edit")
    public Response editBooking(String booking) {
        BookingDTO bookingDTO = GSON.fromJson(booking, BookingDTO.class);
        BookingDTO updatedBooking = FACADE.editBooking(bookingDTO);

        return Response.ok().entity(updatedBooking).build();
    }
}
