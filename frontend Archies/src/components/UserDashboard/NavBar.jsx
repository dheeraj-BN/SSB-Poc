// Importing necessary modules and files
import React from "react";
import "../../css/userDashboard/navbar.css";

// Defining the Navbar component
function NavBar() {
  
  return (
    <div>
      {/* Navbar HTML structure */}
      <nav className="nav2">
        <ul>
          <li>
            {/* Button to navigate to seat booking page */}
            <a href="/booking">
              <button>Seat Booking</button>
            </a>
          </li>
          <li>
            {/* Button to navigate to user profile page */}
            <a href="/profileuser">
              <button>View Profile</button>
            </a>
          </li>
          <li>
            {/* Button to navigate to modify booking page */}
            <a href="/modify">
              <button>Modify Your Booking</button>
            </a>
          </li>
          <li>
            {/* Button to check pass booking */}
            <a href="/booking">
              <button>Past booking</button>
            </a>
          </li>
        </ul>
        <ul>
          <li>
            {/* Button to sign out */}
            <i class="bi bi-box-arrow-right">
              {/* <button><Logout/></button> */}
            </i>
          </li>
        </ul>
      </nav>
    </div>
  );
}

// Exporting the Navbar component
export default NavBar;
