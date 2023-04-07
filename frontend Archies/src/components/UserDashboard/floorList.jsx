// Importing necessary modules and files
import React, { useEffect } from "react";
import "../../css/userDashboard/floorList.css";
import axios from "axios";
import { useState } from "react";
import { Link } from "react-router-dom";

// Defining the FloorList component
function FloorList() {
  // Using state hooks to store the user's token and the data retrieved from the server
  const [token, setToken] = useState(window.localStorage.getItem("token"));
  const [data, setData] = useState([{}]);

  // useEffect hook to fetch data from server when the component mounts
  useEffect(() => {
    // Fetching data from server using axios library
    fetch("http://10.191.80.98:9090/api/employee/floors", {
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
  }, []);

  // Rendering the floor list
  return (
    <div className="fList1">
      <h3>Select Floor</h3>

      {/* Mapping over the data array to render a button for each floor */}
      {data.map((val, idx) => {
        // Creating a link to the floor page with the corresponding index
        let a1 = "/floor" + idx;

        // Returning a div with a button for each floor
        return (
          <div className="floor0">
            <Link to={a1}>
              {/* Setting the floor id in local storage when the button is clicked */}
              <button
                onClick={() =>
                  localStorage.setItem("floorid", val.floorName)
                }
                className="floorbtn"
              >
                {val.floorName}
              </button>
            </Link>
          </div>
        );
      })}
    </div>
  );
}

// Exporting the FloorList component
export default FloorList;
