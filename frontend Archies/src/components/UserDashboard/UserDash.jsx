// Importing React and the useState hook
import React, { useState } from "react";
// Importing CSS files for styling
import "../../css/userDashboard/navbar.css";
import "../../css/userDashboard/navbar.css";

// Importing the NavBar and QRGenerator components
import NavBar from "./NavBar";
import QRGenerator from "./qrgenerator";
// import { useParams } from "react-router";
// function withParams(Component) {
//   return props => <Component {...props} params={useParams()} />;
//  }
function UserDashboard() {
  const [seat_detail,setSeatdetail]=useState();
  const storedData = localStorage.getItem('seat_name');

  return (
    <div>
      <div>
        {/* Rendering the NavBar component */}
        <NavBar />
      </div>
      <h1>Welcome to your dashboard Mihir</h1>
      {/* <a href="/qr">
        <button className="btn btn-primary">View Booked Seat</button>
        </a> */}
      <div className="qr">
         {/* Rendering the QRGenerator component */}
        <QRGenerator />
      </div>
      {/* <div>
       <p2>{storedData}</p2>
      </div> */}
    </div>
  );
}

export default UserDashboard;