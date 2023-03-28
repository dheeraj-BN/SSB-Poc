import { useState } from 'react';

function SeatBookingDashboard() {
  const [availableSeats, setAvailableSeats] = useState([
    { id: 1, number: 'A1', status: 'available' },
    { id: 2, number: 'A2', status: 'available' },
    { id: 3, number: 'B1', status: 'available' },
    { id: 4, number: 'B2', status: 'available' },
  ]);
  const [selectedSeat, setSelectedSeat] = useState(null);
  const [bookingStatus, setBookingStatus] = useState(null);

  function handleSeatSelect(seat) {
    setSelectedSeat(seat);
  }

  function handleBookingSubmit(e) {
    e.preventDefault();
    if (!selectedSeat) {
      return;
    }
    const updatedSeats = availableSeats.map(seat => {
      if (seat.id === selectedSeat.id) {
        return { ...seat, status: 'booked' };
      }
      return seat;
    });
    setAvailableSeats(updatedSeats);
    setSelectedSeat(null);
    setBookingStatus(selectedSeat.number);
  }

  return (
    <div>
      <h2>Seat Booking Dashboard</h2>
      <div>
        <h3>Select a seat:</h3>
        <ul>
          {availableSeats.map(seat => (
            <li key={seat.id} onClick={() => handleSeatSelect(seat)}>
              {seat.number} ({seat.status})
            </li>
          ))}
        </ul>
      </div>
      <div>
        <h3>Book your seat:</h3>
        <form onSubmit={handleBookingSubmit}>
          <label>
            Name:
            <input type="text" required />
          </label>
          <label>
            Email:
            <input type="email" required />
          </label>
          <button type="submit">Book Seat {selectedSeat?.number}</button>
        </form>
        {bookingStatus && <p>Seat {bookingStatus} booked successfully!</p>}
      </div>
    </div>
  );
}
export default SeatBookingDashboard
