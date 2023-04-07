import React, { useState, useEffect } from "react";
import "../../css/userDashboard/pastBookingform.css";
import NavBar  from "./NavBar.jsx";

import axios from "axios";
import { DatagridCell } from "react-admin";

function PastBookingForm() {
   // Set initial state values
  const [branchName, setBranchName] = useState("");
  const [buildingName, setBuildingName] = useState("");
  const [differenceDay, setDifferenceDay] = useState(0);
  const [toDate, setToDate] = useState("");
  const [firstDate, setFirstDate] = useState("");
  const [meal, setMeal] = useState("");
  const [seat, setSeat] =useState("");

  const [shiftTiming, setShiftTiming] = useState("");
  const [request, setRequest] = useState("");
  const [data, setData] = useState([{}]);
  const [token, setToken] = useState(window.localStorage.getItem("token"));
  
  

 // Handle form submission
  const handleSubmit = (e) => {
    e.preventDefault();
    window.location = "/floorlist";
  };
  
 // Get today's date
  function todayDate() {
    const now = new window.Date();
    const year = now.getFullYear();
    const month = now.getMonth() + 1;
    const day = now.getDate();
    return `${year}-${month.toString().padStart(2, "0")}-${day
      .toString()
      .padStart(2, "0")}`;
  }
  // Calculate number of days between two dates
  function weeklydate(e) {
    let startDate = new window.Date(firstDate);
    let lastDate = new window.Date(e.target.value);
    const diffTime = Math.abs(startDate - lastDate);
    const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    setDifferenceDay(diffDays);
    if (diffDays > 7) {
      alert("Not allowed to book for more than 7 days");
    }
    setToDate(e.target.value)
  }
  useEffect(() => {
    fetch("http://10.191.80.100:9090/api/employee/lastbookingdetails/22", {
      method: "GET",

      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error: ${response.status}`);
        }

        return response.text();
      })
      .then((text) => {
        setData(JSON.parse(text));
         console.log(text)
        
      })
  },[]);
// Save form data to local storage
  const savedata=()=>{
    localStorage.setItem("foodStatus",meal)
    localStorage.setItem("shiftTimings",shiftTiming)
    if(request=='Daily'){
      localStorage.setItem('from',firstDate);
      localStorage.setItem("to",firstDate);
      window.location="/floorlist"
    }
    else if(request==="Weekly"){
          localStorage.setItem("from",firstDate);
          localStorage.setItem("to",toDate);
          window.location="/floorlist"     
    }
  }

  return (
    <div>
       <div>
          <NavBar/>
        </div> 
        <div>
        <form onSubmit={handleSubmit} className="seat-booking-form1">
            {/* Branch name selection */}
      <label htmlFor="branch-name-input ">Branch Name:</label>
      <select
        id="branch-name-input"
        value={branchName}
        onChange={(e) => setBranchName(e.target.value)}
        className="form-input11"

      >
        <option value="Bangalore">Bangalore</option>
      </select>
        {/* Building name selection */}
      <label htmlFor="building-name-input">Building Name:</label>
      <select
        id="building-name-input"
        value={buildingName}
        onChange={(e) => setBuildingName(e.target.value)}
        className="form-input11"
      >
        <option value="JP Nagar">JP Nagar</option>
      </select>
        {/* Daily Dates and custom Dates  selection */}
      <label htmlFor="request-input">Select type Of Requests:</label>
      <select
        id="request-input"
        value={request}
        onChange={(e) => setRequest(e.target.value)}
        className="form-input11"
        required
      >
        <option value="" disabled>
          Select
        </option>
        <option value="Daily">Daily</option>
        <option value="Weekly">Custom Dates</option>
      </select>
        {/* Shift Timing selection */}
      <label htmlFor="shift-timing-input">Shift Timing:</label>
      <select
        id="shift-timing-input"
        value={shiftTiming}
        onChange={(e) => setShiftTiming(e.target.value)}
        className="form-input11"
        required
      >
        <option value="" disabled>
            Select
        </option>
       
    
      </select>
        {/* Meal preference selection */}
      <label htmlFor="meal-name-input">Meal:</label>
      <select
        id="meal-name-input"
        value={meal}
        onChange={(e) => setMeal(e.target.value)}
        className="form-input11"
        required
      >
        <option value="" disabled>
          Select
        </option>
      </select>
      <label htmlFor="seat-name-input">Seat:</label>
      <select
        id="seat-name-input"
        value={seat}
        onChange={(e) => setSeat(e.target.value)}
        className="form-input11"
        required
      >
        <option value="" disabled>
          Select
        </option>
      </select>

      {request === "Daily" && (
        <>
          {" "}
          <label htmlFor="from-date-input"> From Date:</label>
          <input
            id="from-date-input"
            type="date"
            value={firstDate}
            min={todayDate()}
            onChange={(e) => setFirstDate(e.target.value)}
            className="form-input1"
            required
          />
          <label htmlFor="to-date-input">To Date:</label>
          <input
            id="to-date-input"
            type="date"
            value={firstDate}
            min={todayDate()}
            className="form-input11"
          />
        </>
      )}

      {request === "Weekly" && (
        <>
          {" "}
          <label htmlFor="from-date-input"> From Date:</label>
          <input
            id="from-date-input"
            type="date"
            value={firstDate}
            min={todayDate()}
            onChange={(e) => setFirstDate(e.target.value)}
            className="form-input11"
          />
          <label htmlFor="to-date-input">To Date:</label>
          <input
            id="to-date-input"
            type="date"
            min={todayDate()}
            val={toDate}
            onChange={(e) => weeklydate(e)}
            className="form-input11"
          />
        </>
      )}

      <button type="submit" onClick={savedata} className="btn btn-primary booking-btn1">
        Submit
      </button>
    </form>
    
        </div>
    </div>
    
  );
}

export default PastBookingForm;
