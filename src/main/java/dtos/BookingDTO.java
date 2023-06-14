package dtos;

import entities.Booking;
import entities.Car;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString

public class BookingDTO {
    private Long id;
    private String dateAndTime;
    private String duration;

    public BookingDTO(Booking booking) {
        this.id = booking.getId();
        this.dateAndTime = getDateAndTime();
        this.duration = getDuration();
    }

    public BookingDTO(Long id, String dateAndTime, String duration) {
        this.id = id;
        this.dateAndTime = dateAndTime;
        this.duration = duration;
    }

    public static List<BookingDTO> getDTOs(List<Booking> bookingList) {
        List<BookingDTO> bookingDTOs = new ArrayList<>();
        bookingList.forEach(booking -> bookingDTOs.add(new BookingDTO(booking)));
        return bookingDTOs;
    }

}
