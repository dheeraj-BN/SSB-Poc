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
          <li><button><i class="fas fa-bell"></i></button></li>
        </ul>
      </nav>
      <h1>Welcome to your dashboard</h1>
      {/* Other content of the dashboard */}
    </div>
   
  );
}

export default UserDashboard;

     

