// Importing React and the useState hook
import React, { useState } from 'react';

// Importing CSS file for styling
import "../../css/DeveloperDashboard/addflor.css";

// Defining the AddFloor component
function AddFloor() {
  // Defining two state variables, floorName and numSeats, and their respective setter functions
  const [floorName, setFloorName] = useState('');
  const [numSeats, setNumSeats] = useState('');

  // Defining the handleSubmit function, which will prevent the default form submission behavior
  const handleSubmit = (e) => {
    e.preventDefault();
  };

  // Rendering the form elements on the page
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

// Exporting the AddFloor component for use in other files
export default AddFloor;
