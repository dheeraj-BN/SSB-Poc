import React, { useState } from "react";
import "../../css/DeveloperDashboard/developer.css";
import FloorList from "../UserDashboard/floorList";

function Developer() {
  const [showComponent, setShowComponent] = useState(false);

  const handleClick = () => {
    setShowComponent(true);
  };

  return (
    <div>
      <nav>
        <ul>
          <li>
            <a href="/Profile">
              <button >View Profile</button>
            </a>
          </li>
          <li>
            <button onClick={handleClick}>Add Floors</button>
          </li>
        </ul>
        <ul>
          <li>
            <i class="bi bi-box-arrow-right">Sign Out</i>
          </li>
        </ul>
      </nav>
      <div>
            {showComponent && <FloorList />}
           

      </div>
    </div>
  );
}

export default Developer;
