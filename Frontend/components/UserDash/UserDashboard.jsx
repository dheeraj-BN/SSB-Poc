import React from "react";
import { useNavigate } from "react-router-dom";
import "../CSS/UserDashboard.css"

function UserDashboard() {
  
  return (
   
      <div>
      <nav>
        <ul>
          <li>
          <a href="/booking">
            <button>Seat Booking
              </button></a></li>
          <li><button>View Profile</button></li>
          <li>
            <a href="/qr">
           <button>View Seat Booking</button></a></li>
        </ul>
        <ul>
         <li><i class="bi bi-box-arrow-right">Sign Out</i></li>
          
        </ul>
      </nav>
      <h1>Welcome to your dashboard</h1>
    </div>
   
  );
}

export default UserDashboard;

     

