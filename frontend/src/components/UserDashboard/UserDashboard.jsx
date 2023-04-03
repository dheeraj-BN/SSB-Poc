import React from "react";
import { useNavigate } from "react-router-dom";
import "../components/UserDashboard.css"

function UserDashboard() {
  // const navigate=useNavigate()
  // function SeatBookingForm(){
  //   navigate("/booking")
  // }
  return (
   
      <div>
      <nav>
        <ul>
          <li><button><a href="/booking">Seat Booking</a></button></li>
          <li><button>View Profile</button></li>
          <li><button>View Seat Booking</button></li>
        </ul>
        <ul>
         <li><i class="bi bi-box-arrow-right">Sign Out</i></li>
          {/* <li><a href="#" class="btn btn-danger"><i class="bi bi-box-arrow-right"></i> Sign Out</a></li> */}
        </ul>
      </nav>
      <h1>Welcome to your dashboard</h1>
      {/* Other content of the dashboard */}
    </div>
   
  );
}

export default UserDashboard;

     

