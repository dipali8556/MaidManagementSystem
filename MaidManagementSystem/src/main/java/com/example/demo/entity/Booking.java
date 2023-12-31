package com.example.demo.entity;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;
    
    // Maid associated with booking
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Maid maid;
    
   
   
    @NotNull(message = "Enter Your User Id.")
    private Integer client_id;
    
    @NotNull(message = "Enter the MaidID of the Maid you want to Book.")
    private Integer maidbook_id;
    
    // User who book the maid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    

    
    @Column(name="Bookingdate",length = 50)
    private String bookingDate;			//date and time will be Automatically Generated
    
    @Column(name="BookingStatus",length = 30)
    private String bookingStatus;		    // Booking status of the maid (initially pending, can be confirmed and verified by the Admin)

}
