import React, { useState } from 'react';
import "../../css/DeveloperDashboard/addflor.css";


function AddFloor() {
  const [floorName, setFloorName] = useState('');
  const [numSeats, setNumSeats] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    // Do something with the floorName and numSeats values, such as submitting them to a server or updating state in a parent component
    console.log(`Floor name: ${floorName}, number of seats: ${numSeats}`);
  };

  return (
    <div>
    <form onSubmit={handleSubmit} className="seat-booking-form">
      <label htmlFor="floorName">Name of Floor:</label>
      <input
        type="text"
        id="floorName"
        value={floorName}
        onChange={(e) => setFloorName(e.target.value)}
        className="form-input"
      />
      <br />
      <label htmlFor="numSeats">Number of Seats:</label>
      <input
        type="number"
        id="numSeats"
        value={numSeats}
        onChange={(e) => setNumSeats(e.target.value)}
        className="form-input"
      />
      <br />
      <button className="btn" type="submit">Submit</button>
    </form>
    </div>
  );
}

export default AddFloor;
