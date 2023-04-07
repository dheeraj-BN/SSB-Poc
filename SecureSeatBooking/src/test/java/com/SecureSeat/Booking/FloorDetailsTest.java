package com.SecureSeat.Booking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import com.SecureSeat.Booking.controller.FloorController;
import com.SecureSeat.Booking.entity.FloorDetails;
import com.SecureSeat.Booking.repo.FloorDetailsRepo;
import com.SecureSeat.Booking.service.FloorService;
import com.SecureSeat.Booking.service.MailService;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FloorDetailsTest {
	

	  @Mock
	    private FloorService floorService;
	    
	    @Mock
	    private FloorDetailsRepo floorDetailsRepo;
	    
	    @Mock
	    private MailService mailService;
	    
	    @InjectMocks
	    private FloorController floorController;
	
	
	    private FloorDetails floorDetails;
	    
	    @BeforeEach
	    public void setUp() {
	        floorDetails = new FloorDetails("Floor1", 10);
	    }
	    
	    
	    @Test
	    public void testGetFloorDetailsByFloorName() {
	        doReturn(floorDetails).when(floorService).getFloorDetailsByFloorName("Floor1");
	        FloorDetails actualFloorDetails = floorController.getFloorDetailsByFloorName("Floor1");
	        assertThat(actualFloorDetails).isEqualTo(floorDetails);
	    }
	    
//	    
//	    @Test
//	    public void testAddFloorWithdetails() {
//	        doReturn(floorDetails).when(floorService).addFloor("Floor1", 10);
//	        doNothing().when(mailService).addedFloor(floorDetails);
//	        ResponseEntity<FloorDetails> responseEntity = floorController.addFloorWithdetails(floorDetails);
//	        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//	        assertThat(responseEntity.getBody()).isEqualTo(floorDetails);
//	        verify(mailService).addedFloor(floorDetails);
//	    }
//	
	    
	    @Test
	    public void testAddFloorWithdetailsWhenFloorAlreadyExists() {
	        doThrow(new RuntimeException()).when(floorService).addFloor("Floor1", 10);
	        ResponseEntity<FloorDetails> responseEntity = floorController.addFloorWithdetails(floorDetails);
	        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	        assertThat(responseEntity.getBody()).isNull();
	    }
	    
	    @Test
	    public void testDeleteFloorByFloorName() {
	        doReturn(floorDetails).when(floorDetailsRepo).findByFloorName("Floor1");
	        doNothing().when(floorDetailsRepo).deleteById(anyInt());
	        ResponseEntity<String> responseEntity = floorController.deleteFloorByFloorName("Floor1");
	        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
	        assertThat(responseEntity.getBody()).isEqualTo("Floor Floor1 deleted successfully.");
	    }
	    
	    
	    @Test
	    public void testDeleteFloorByFloorNameWhenFloorDoesNotExist() {
	        doReturn(null).when(floorDetailsRepo).findByFloorName(anyString());
	        ResponseEntity<String> responseEntity = floorController.deleteFloorByFloorName("Floor1");
	        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
	        assertThat(responseEntity.getBody()).isEqualTo("Floor Floor1 not found.");
	    }
	    
	    
	    
	
}
