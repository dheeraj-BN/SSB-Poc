package com.SecureSeat.Booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.SecureSeat.Booking.controller.ShiftDetailsController;
import com.SecureSeat.Booking.entity.ShiftDetails;
import com.SecureSeat.Booking.service.ShiftDetailsService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ShiftDetailsTest {

	@Mock
    private ShiftDetailsService shiftDetailsService;

    @InjectMocks
    private ShiftDetailsController shiftDetailsController;
    
    @Test
    void testGetAllShiftDetails() {
    	 List<ShiftDetails> shiftDetailsList = new ArrayList<>();
         shiftDetailsList.add(new ShiftDetails("09:00  - 05:00 "));
         shiftDetailsList.add(new ShiftDetails("05:00  - 01:00 "));

         when(shiftDetailsService.getAllShiftDetails()).thenReturn(shiftDetailsList);
         List<ShiftDetails> result = shiftDetailsController.getAllShiftDetails();
         assertEquals(2, result.size());
         assertEquals("09:00  - 05:00 ", result.get(0).getShiftTimings());
         assertEquals("05:00  - 01:00 ", result.get(1).getShiftTimings());
    }
    
    @Test
    void testDeleteShiftDetails() {
    	List<ShiftDetails> shiftDetailsList = new ArrayList<>();
        shiftDetailsList.add(new ShiftDetails(1,"09:00  - 05:00 "));
        shiftDetailsList.add(new ShiftDetails(2,"05:00  - 01:00 "));
        int id =2;
        when(shiftDetailsService.deleteShift(id)).thenReturn("Shift details deleted successfully");
    
        String result=shiftDetailsController.deleteShiftDetails(2);
        assertEquals("Shift details deleted successfully", result);
    }
}
