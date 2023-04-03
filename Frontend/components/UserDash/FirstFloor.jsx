import React from 'react';
import "../CSS/Groundfloor.css";


function SeatMatrix1() {
  const numDivs = 20;
  

  const divs = [];
  for (let i = 1; i <= numDivs; i++) {
    const divId = `${"F"+i}`;
    console.log(divId)

    const newDiv = (
      <div className='seat' key={divId} id={divId}>
        FF-{i}
      </div>
    );

    divs.push(newDiv);
  }

  return (
    <div className='seat-booking-app' >
        { <h1>First Floor</h1> }
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



export default SeatMatrix1;
