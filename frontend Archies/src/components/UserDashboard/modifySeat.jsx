// Importing necessary modules
import React, { useState ,useEffect} from 'react';
import "../../css/userDashboard/modifySeat.css";

// Defining the functional component
function SeatModify() {
  // Declaring state variables using useState hook
  const [token, setToken] = useState(window.localStorage.getItem("token"));
  const [bookingToken, setBookingToken]=useState()
  const [data, setData] = useState([{}]);
  const storedData = localStorage.getItem('userId');

  // Fetching the booked seat details for the current user using useEffect hook
  useEffect(() => {
    fetch("http://10.191.80.100:9090/api/employee/next/booked/details/"+storedData, {
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
      });
  },[]);

  // Function to cancel a booked seat
  const cancelBooking = (seattoken) => {
    fetch("http://10.191.80.100:9090/api/employee/cancel/"+seattoken, {
      method: "PUT",

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
        window.location="/modify";        
      });
  };
  
  // Rendering the component
  return (
    <div>
      {/* Navbar */}
      <nav class="navbar navbar-dark bg-mynav">
        <div class="container-fluid">
          <a class="navbar-brand" >Modify Your Seat Booking</a>
        </div>
      </nav>
      
      {/* Table displaying booked seats */}
      <div class="table-responsive">
        <table class="table">
          <thead>
            <tr>
              <th scope="col" >Date</th>
              <th scope="col">Seat No</th>
              <th scope="col">Action</th>
            </tr>
          </thead>
          <tbody>
            {/* Mapping over the seat data to display each booked seat */}
            {
              data && (
                data.map((item,index)=>{
                  const seattoken = item.token;
                  console.log("seat              "+seattoken)
                  return(
                    <tr key={index}>
                      <td scope="col" >{item.date}</td>
                      <td scope="col">{item.seatNo}</td>
                      <button>Edit</button>
                      {/* Button to cancel a booked seat */}
                      <button onClick={() =>cancelBooking(seattoken)}>Cancel</button>
                    </tr>
                  )
                })
              )
            }
          </tbody>
        </table>
      </div>
    </div>    
  );
}

// Exporting the component
export default SeatModify;
