import React from 'react';
import "../components/seatMatrix.css"

function SeatMatrix() {
  const numDivs = 20;

  const divs = [];
  for (let i = 1; i <= numDivs; i++) {
    const divId = `div${i}`;

    const newDiv = (
      <div className='seat' key={divId} id={divId}>
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
    </div>
  );
}

export default SeatMatrix;
