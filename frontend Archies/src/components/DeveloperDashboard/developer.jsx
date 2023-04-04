import React, { useState } from "react";
import "../../css/DeveloperDashboard/developer.css";
import AddFloor from "./addfloor";

function Developer() {
  const [showComponent, setShowComponent] = useState(false);

  const handleClick = () => {
    setShowComponent(true);
  };

  return (
    <div>
      <nav className="nav1">
        <ul>
          <li>
            <a href="/profileuser">
              <button className="btn-profile">View Profile</button>
            </a>
          </li>
          <li>
            <button className="btn-floor" onClick={handleClick}>Add Floors</button>
          </li>
        </ul>
        <ul>
          <li>
            <i class="bi bi-box-arrow-right">Sign Out</i>
          </li>
        </ul>
      </nav>
      <div>
            {showComponent && <AddFloor />}
           

      </div>
    </div>
  );
}

export default Developer;
