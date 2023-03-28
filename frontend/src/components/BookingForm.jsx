import React, { useState } from 'react';
import '../components/seatbookingform.css';

function SeatBookingForm() {
  const [branchName, setBranchName] = useState('');
  const [buildingName, setBuildingName] = useState('');
  const [Date, setDate] = useState('');
  const [toDate, setToDate] = useState('');
  const [shiftTiming, setShiftTiming] = useState('');
  const [request,setRequest]=useState('')

  const handleSubmit = (e) => {
    e.preventDefault();
    // Send seat booking information to the appropriate system for processing and confirmation
  };

  return (
    <form onSubmit={handleSubmit} className="seat-booking-form">
      <label htmlFor="branch-name-input">Branch Name:</label>
      <select
        id="branch-name-input"

        value={branchName}
        onChange={(e) => setBranchName(e.target.value)}
        className="form-input">
        <option value="Bangalore">Bangalore</option>
      </select>

      <label htmlFor="building-name-input">Building Name:</label>
      <select
        id="building-name-input"

        value={buildingName}
        onChange={(e) => setBuildingName(e.target.value)}
        className="form-input">
        <option value="JP Nagar">JP Nagar</option>
      </select>
      <label htmlFor="request-input">Select type Of Requests:</label>
      <select
        id="request-input"

        value={request}
        onChange={(e) => setRequest(e.target.value)}
        className="form-input">
        <option value="select">Select</option>
        <option value="Daily">Daily</option>
        <option value="Weekly">Weekly</option>

      </select>
      <label htmlFor="shift-timing-input">Shift Timing:</label>
      <select
        id="shift-timing-input"
        value={shiftTiming}
        onChange={(e) => setShiftTiming(e.target.value)}
        className="form-input"
      >
        <option value="">--Select--</option>
        <option value="Morning">Morning</option>
        <option value="Afternoon">Afternoon</option>
        <option value="Evening">Evening</option>
        <option value="Night">Night</option>
      </select>

      {/* <label htmlFor="from-date-input">Date:</label>
      <input
        id="from-date-input"
        type="date"
        value={Date}
        onChange={(e) => setDate(e.target.value)}
        className="form-input"
      /> */}

      <label htmlFor="to-date-input"> Date:</label>
      <input
        id="to-date-input"
        type="date"
        value={toDate}
        onChange={(e) => setToDate(e.target.value)}
        className="form-input"
      />



      <button  className="btn btn-primary"><a href="/seatSelection">Next</a></button>
    </form>
  );
}

export default SeatBookingForm;