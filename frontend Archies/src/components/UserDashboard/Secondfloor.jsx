import axios from 'axios';
import React from 'react';
import { useEffect } from 'react';
import { useState } from 'react';
import "../../css/userDashboard/Groundfloor.css";

function SeatMatrix2() {
  
 // Define state variables using the useState hook
  const [seats, setSeats] = useState([]);
  const [data, setData] = useState([{}]);
  const [Seatdata, setSeatData] = useState([{}]);

  const [seatBooked,setSeatBooked] = useState([{}])
  const [token, setToken] = useState(window.localStorage.getItem("token"));

   const storedData = localStorage.getItem('from');


// Use the useEffect hook to fetch data from the server 
   useEffect(() => {
     fetch("http://40.88.23.186:9090/api/employee/seatnumber/"+storedData, {
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
        setSeatBooked(JSON.parse(text))
       
        
      });
      
  },[]);

  useEffect(() => {
    // Fetching data from server using axios library
    fetch("http://40.88.23.186:9090/api/employee/floors", {
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
        setSeatData(JSON.parse(text));
      });
  }, []);
// Use another useEffect hook to create an array of seat objects
  useEffect(()=>{
    const numSeats =  30 ; 
    // console.log(numSeats)
    const newSeats = [];
    for (let i = 1; i <= numSeats; i++) {
      const seatName = `SF${i}`;
      
      const isBooked = seatBooked.some((seat) =>seat === seatName);
      newSeats.push({
        id: i,
        name: seatName,
        booked: isBooked,
        selected: false,
      });
    }
    setSeats(newSeats);
  },[seatBooked])
 
 

  const [selected, setSelected] = useState({});
// Define a function to handle the click event on a seat
  const handleSeatClick = (name) => {
    console.log(name);
    //logic for deselection
    setSelected({
      seatId: name,
      floorId: "SF",
    });
  };

  const sendData = () => {
    if (selected.seatId != null) {
      localStorage.setItem("seatNo", selected.seatId);

      let foodStatus = localStorage.getItem("foodStatus")
      let shiftTimings=localStorage.getItem("shiftTimings")
      let userId=localStorage.getItem("userId")
      let seatNo=localStorage.getItem("seatNo")
      let todate=localStorage.getItem("to")
      let fromdate=localStorage.getItem("from")
      axios.post(`http://40.88.23.186:9090/employee/seatbookdetails?from=${fromdate}&to=${todate}`,{foodStatus,shiftDetails:{shiftTimings},
      userDeatils:{userId},seatNo},
      {headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      }}).then((res)=>{
        alert(res.data)
      }).catch((err)=>{
        alert(err)
            })
      window.location = "/";
    } else {
      alert("please select a seat");
    }
  };

  return (
   
    <div className="seat-booking-app">
      <div>
        <h1>Second Floor</h1>
      </div>
       {/* Map over the seats array and render the seats */}
      <div className="seat-map">
        {seats.map((seat) =>
          seat.booked ? (
            <div>
              <label>
                {seat.name}
                <input id={seat.id} className="seat booked" disabled>
                </input>
              </label>{" "}
            </div>
          ) : (
            <div>
              <label>
                {seat.name}
                <input
                  className="seat"
                  name="test"
                  // checked={}
                  value={seat.name}
                  onClick={() => handleSeatClick(seat.name)}
                  type="radio"
                />
              </label>
            </div>
          )         
        )}
      </div>
      <br />
      <div id="legend">
        <label>
          Available seats
          <input className="seat" name="test" type="radio" disabled />
        </label>
        <label>
          selected seats
          <input
            className="seat select"
            name="test"
            type="radio"
            disabled
          />{" "}
        </label>{" "}
        <label>
          Booked seats
          <input className="seat booked" name="test" type="radio" disabled />
        </label>
        <div>
          Your Chosen Seats
          {!seats.booked && <div>{selected.seatId}</div>}
        </div>
       
      </div>
      <button onClick={sendData} className="btn btn-warning Nextbtn">
        submit
      </button>
      
    </div>
  );
}



export default SeatMatrix2;
