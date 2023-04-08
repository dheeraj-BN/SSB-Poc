// Importing React and the useState hook
import React, { useState, useEffect } from "react";
// Importing CSS files for styling
import "../../css/userDashboard/navbar.css";
import "../../css/userDashboard/navbar.css";

// Importing the NavBar and QRGenerator components
import NavBar from "./NavBar";
import QRGenerator from "./qrgenerator";

function UserDashboard() {
  const [seat_detail, setSeatdetail] = useState();
  const [data, setData] = useState([{}]);
  const [token, setToken] = useState(window.localStorage.getItem("token"));

  const storedData = localStorage.getItem("seat_name");
  const storedData1 = localStorage.getItem("userId");

  useEffect(() => {
    fetch(
      "http://40.88.23.186:9090/api/employee/booked/details/" + storedData1,
      {
        method: "GET",

        headers: {
          Authorization: "Bearer " + token,
          "Content-Type": "application/json",
        },
      }
    )
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error: ${response.status}`);
        }

        return response.text();
      })
      .then((text) => {
        setData(JSON.parse(text));
        console.log(JSON.parse(text));

      });
  }, []);

  return (
    <div>
      <div>
        {/* Rendering the NavBar component */}
        <NavBar />
      </div>
      <h1>Your QR Code for Date {data.date}</h1>
      {/* <a href="/qr">
        <button className="btn btn-primary">View Booked Seat</button>
        </a> */}
      <div className="data1">
        {/* Rendering the QRGenerator component */}

        <div className="qr">
          <QRGenerator />
        </div>
        <div>
          <h2>Your Booking ID is {data.bookingId} </h2>
          <h2>Your Seat Number is {data.seatNo} </h2>
        </div>
      </div>

      {/* <div>
       <p2>{storedData}</p2>
      </div> */}
    </div>
  );
}

export default UserDashboard;
