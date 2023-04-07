import React, { useState, useEffect } from "react";
import "../../css/userDashboard/Bookingform.css";
import NavBar  from "./NavBar.jsx";

import axios from "axios";

function SeatBookingForm() {
   // Set initial state values
  const [branchName, setBranchName] = useState("");
  const [buildingName, setBuildingName] = useState("");
  const [differenceDay, setDifferenceDay] = useState(0);
  const [toDate, setToDate] = useState("");
  const [firstDate, setFirstDate] = useState("");
  const [meal, setMeal] = useState("");

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
  // const nextPage=()=>{
  //   if(request=='Daily'){
  //     localStorage.setItem('from_date',firstDate);
  //     localStorage.setItem("to_date",firstDate);
  //     window.location="/floorlist"
  //   }
  //   else if(request==="Weekly"){
  //         localStorage.setItem("from_date",firstDate);
  //         localStorage.setItem("to_date",toDate);
  //         window.location="/floorlist"     
  //   }
  // }
// Fetch shift timings from API
  useEffect(() => {
    fetch("http://10.191.80.98:9090/api/employee/getshift", {
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
        // console.log(text)
        
      });
  },[]);
// Save form data to local storage
  const savedata=()=>{
    // localStorage.setItem("from",toDate)
    // localStorage.setItem("to",toDate)
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
        <form onSubmit={handleSubmit} className="seat-booking-form">
            {/* Branch name selection */}
      <label htmlFor="branch-name-input ">Branch Name:</label>
      <select
        id="branch-name-input"
        value={branchName}
        onChange={(e) => setBranchName(e.target.value)}
        className="form-input1"

      >
        <option value="Bangalore">Bangalore</option>
      </select>
        {/* Building name selection */}
      <label htmlFor="building-name-input">Building Name:</label>
      <select
        id="building-name-input"
        value={buildingName}
        onChange={(e) => setBuildingName(e.target.value)}
        className="form-input1"
      >
        <option value="JP Nagar">JP Nagar</option>
      </select>
        {/* Daily Dates and custom Dates  selection */}
      <label htmlFor="request-input">Select type Of Requests:</label>
      <select
        id="request-input"
        value={request}
        onChange={(e) => setRequest(e.target.value)}
        className="form-input1"
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
        className="form-input1"
        required
      >
        <option value="" disabled>
          Select
        </option>
        {
          data.map((val,idx)=>{
            return <option value={val.shiftTimings}>{val.shiftTimings}</option>
          })
        }
    
      </select>
        {/* Meal preference selection */}
      <label htmlFor="meal-name-input">Meal:</label>
      <select
        id="meal-name-input"
        value={meal}
        onChange={(e) => setMeal(e.target.value)}
        className="form-input1"
        required
      >
        <option value="" disabled>
          Select
        </option>
        <option value="true">Yes</option>
        <option value="false">No</option>
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
            className="form-input1"
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
            className="form-input1"
          />
          <label htmlFor="to-date-input">To Date:</label>
          <input
            id="to-date-input"
            type="date"
            min={todayDate()}
            val={toDate}
            onChange={(e) => weeklydate(e)}
            className="form-input1"
          />
        </>
      )}

      <button type="submit" onClick={savedata} className="btn btn-primary booking-btn">
        Next
      </button>
    </form>
    
        </div>
    </div>
    
  );
}

export default SeatBookingForm;
