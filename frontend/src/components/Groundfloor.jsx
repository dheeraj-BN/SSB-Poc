import React, { useState } from 'react';
import "../components/Groundfloor.css"

function SeatMatrix() {
  const numDivs = 20;

  const [selectedDiv, setSelectedDiv] = useState(null); // State variable to keep track of the selected div

  const divs = [];
  for (let i = 1; i <= numDivs; i++) {
    const divId = `${"G"+i}`;
    console.log(divId)

    const isSelected = selectedDiv === divId; // Check if this div is selected

    const newDiv = (
      <div className={`seat ${isSelected ? 'selected' : ''}`} // Add the 'selected' class if this div is selected
           key={divId}
           id={divId}
           onClick={() => setSelectedDiv(isSelected ? null : divId)} // Toggle the selectedDiv state variable on click
           style={{ pointerEvents: isSelected ? 'auto' : 'none' }} // Disable pointer events for other divs if a div is selected
      >
        GF-{i}
      </div>
    );

    divs.push(newDiv);
  }

  return (
    <div className='seat-booking-app' >
        { <h1>Ground Floor</h1> }
        <div className='seat-map'>
           {divs}
      </div>
      <div id='legend'>
      <div className="seat"></div> <div className="txt">Available</div>
      <div className="seat taken"></div> <div className="txt">Taken</div>
      <div className="seat selected"></div> <div className="txt">Your Chosen Seats</div>

      </div>
    </div>
  );
}

export default SeatMatrix;