import React, { useState } from "react";
import "../../css/DeveloperDashboard/developer.css";
import Logout from "../../routes/Logout";
import ProfileAd from "../AdminDashboard/ProfileAd";
import AddFloor from "./addfloor";

function Developer() {
  // state hooks to track whether to show each component
  const [showComponent, setShowComponent] = useState(false);
  const [showComponent1, setShowComponent1] = useState(false);

  // function to update showComponent state when add floors button is clicked
  const handleClick = () => {
    setShowComponent(true);
  };
  
  // function to update showComponent1 state when view profile button is clicked
  const handleClick1 = () => {
    setShowComponent1(true);
  };

  return (
    <div>
      {/* Navigation bar */}
      <nav className="nav1">
        <h1>Secure Seat Booking</h1>
        <ul>
          {/* Button to view developer profile */}
          <li>
            <a href="/profiledeveloper">
              <button className="btn-profile" onClick={handleClick1}>View Profile</button>
            </a>
          </li>
          {/* Button to add floors */}
          <li>
            <button className="btn-floor" onClick={handleClick}>Add Floors</button>
          </li>
        </ul>
        {/* Logout button */}
        <ul>
          <li>
            {/* Logout component */}
            <i class="bi bi-box-arrow-right"><Logout/></i>
          </li>
        </ul>
      </nav>
      <div>
        {/* Conditional rendering of components based on state */}
        {showComponent && <AddFloor />}
        {showComponent1 && <ProfileAd/>}
      </div>
    </div>
  );
}

export default Developer;
