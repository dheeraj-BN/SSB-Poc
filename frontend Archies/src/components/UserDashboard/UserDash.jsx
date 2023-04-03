import React from "react";
import "../../css/userDashboard/navbar.css";
import "../../css/userDashboard/UserDashboard.css";
import NavBar from "./NavBar";
import QRGenerator from "./qrgenerator";

function UserDashboard() {
  return (
    <div>
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
    </div>
  );
}

export default UserDashboard;
