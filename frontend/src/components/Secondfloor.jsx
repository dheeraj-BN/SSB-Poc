import React from 'react';
import "../components/Groundfloor.css"

function SeatMatrix2() {
  const numDivs = 20;

  const divs = [];
  for (let i = 1; i <= numDivs; i++) {
    const divId = `${"S"+i}`;

    const newDiv = (
      <div className='seat' key={divId} id={divId}>
        SF-{i}
      </div>
    );

    divs.push(newDiv);
  }

  return (
    <div className='seat-booking-app' >
        { <h1>Second Floor</h1> }
        <div className='seat-map'>
           {divs}
      </div>
      <div id='legend'>
      <div class="seat"></div> <div class="txt">Available</div>
      <div class="seat taken"></div> <div class="txt">Taken</div>
      <div class="seat selected"></div> <div class="txt">Your Chosen Seats</div>

      </div>
    </div>
  );
}

export default SeatMatrix2;
