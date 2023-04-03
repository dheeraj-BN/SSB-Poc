import React from "react";
import "../../css/userDashboard/UserDashboard.css";

function UserDashboard() {
  return (
    <div>
      <nav className="nav">
        <ul>
          <li>
            <a href="/booking">
              <button>Seat Booking</button>
            </a>
          </li>
          <li>  
            <a href="/profileuser">
              <button>View Profile</button>
            </a>
          </li>
          <li>
            <a href="/qr">
              <button>View Seat Booking</button>
            </a>
          </li>
        </ul>
        <ul>
          <li>
            <i class="bi bi-box-arrow-right">Sign Out</i>
          </li>
        </ul>
      </nav>
      <h1>Welcome to your dashboard</h1>
    </div>
  );
}

export default UserDashboard;