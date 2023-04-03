import React from "react";
import "../../css/userDashboard/navbar.css";
import "../../css/userDashboard/UserDashboard.css";
<<<<<<< HEAD
import NavBar from "./NavBar";
import QRGenerator from "./qrgenerator";
=======
>>>>>>> 858ad4605e961299982bdf4d5f7f523bbd598360

function UserDashboard() {
  return (
    <div>
<<<<<<< HEAD
      <div>
        <NavBar />
      </div>
      <h1>Welcome to your dashboard Mihir</h1>
      {/* <a href="/qr">
        <button className="btn btn-primary">View Booked Seat</button>
        </a> */}
      <div className="qr">
        <QRGenerator />
      </div>
=======
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
>>>>>>> 858ad4605e961299982bdf4d5f7f523bbd598360
    </div>
  );
}

export default UserDashboard;
