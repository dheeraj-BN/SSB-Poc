import React, { useState } from 'react';
import "../../css/DeveloperDashboard/addflor.css";
 

const AddFloor = () => {
  const [seats, setSeats] = useState(0);
  const [floor, setFloor] = useState('');

  const handleSeatChange = (event) => {
    setSeats(parseInt(event.target.value));
  };

  const handleFloorChange = (event) => {
    setFloor(event.target.value);
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    console.log(`Number of seats: ${seats}`);
    console.log(`Floor name: ${floor}`);
  };

  return (
    <form className="seat-form" onSubmit={handleSubmit}>
      <label htmlFor="seats">Number of Seats:</label>
      <select id="seats" name="seats" value={seats} onChange={handleSeatChange} type="number">
      </select>

      <label htmlFor="floor">Floor Name:</label>
      <input type="text" id="floor" name="floor" value={floor} onChange={handleFloorChange} />

      <button type="submit">Submit</button>
    </form>
  );
};

export default AddFloor;
