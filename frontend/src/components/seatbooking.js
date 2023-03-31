import React, { useState } from 'react';
import "../components/seatbooking.css";
import axios from 'axios';

const SeatBooking = () => {
  const [seats, setSeats] = useState([
    { id: 1, name: 'A1', booked: false },
    { id: 2, name: 'A2', booked: false },
    { id: 3, name: 'A3', booked: false },
    { id: 4, name: 'A4', booked: false },
    { id: 5, name: 'B1', booked: false },
    { id: 6, name: 'B2', booked: false },
    { id: 7, name: 'B3', booked: false },
    { id: 8, name: 'B4', booked: false },
    { id: 9, name: 'C1', booked: false },
    { id: 10, name: 'C2', booked: false },
    { id: 11, name: 'C3', booked: false },
    { id: 12, name: 'C4', booked: false },
    { id: 13, name: 'D1', booked: false },
    { id: 14, name: 'D2', booked: false },
    { id: 15, name: 'D3', booked: false },
    { id: 16, name: 'D4', booked: false },
  ]);
  const [selectedSeat, setSelectedSeat] = useState(null);

  const handleSeatClick = (id) => {
    const updatedSeats = seats.map((seat) => {
      if (seat.id === id) {
        return { ...seat, booked: !seat.booked };
      } else {
        return seat;
      }
    });
    setSeats(updatedSeats);
    setSelectedSeat(id);
  };

  axios.get("http://10.191.80:9090/floors")
    .then(res => { 
      console.log(res.data)
    })    

  return (
    <div className="seat-booking-app">
      <h1>Seat Matrix</h1>
      <h2>Ground Floor</h2>
      <div className="seat-map">
        {seats.map((seat) => (
          <div
            key={seat.id}
            className={`seat ${seat.booked ? 'booked' : ''} ${seat.id === selectedSeat ? 'selected' : ''}`}
            onClick={() => handleSeatClick(seat.id)}
          >
            {seat.name}
          </div>
        ))}
      </div>
      <br/>
    
      <div id="legend">
        <div class="seat"></div> <div class="txt">Available</div>
        <div class="seat taken"></div> <div class="txt">Taken</div>
        <div class="seat selected"></div> <div class="txt">Your Chosen Seat</div>
      </div>
    </div>
  );
};

export default SeatBooking;
