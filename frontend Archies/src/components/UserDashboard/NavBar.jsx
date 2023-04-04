import React from "react";
import "../../css/userDashboard/UserDashboard.css";


function NavBar() {
  
  return (
   
      <div>
      <nav>
        <ul>
          <li>
          <a href="/booking">
            <button>Seat Booking
              </button></a></li>
              
          <li>
            <a href="/Profile"><button>View Profile</button>
            </a>
            </li>
           <li>
            <a href="/modify"><button>Modify Your booking</button>
            </a>
            </li>
        </ul>
        <ul>
         <li><i class="bi bi-box-arrow-right">Sign Out</i></li>
          
        </ul>
      </nav>
    </div>
   
  );
}

export default NavBar;

     

