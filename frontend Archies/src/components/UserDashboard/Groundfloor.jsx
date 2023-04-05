import React from 'react';
import { useEffect } from 'react';
import { useState } from 'react';
import "../../css/userDashboard/Groundfloor.css";





function SeatMatrix() {
  // const numDivs = 20;
  

  // const divs = [];
  // for (let i = 1; i <= numDivs; i++) {
  //   const divId = `${"F"+i}`;
  //   console.log(divId)

  //   const newDiv = (
  //     <div className='seat' key={divId} id={divId}>
  //       FF-{i}
  //     </div>
  //   );

  //   divs.push(newDiv);
  // }

  const [seats, setSeats] = useState([]);
  const [data, setData] = useState([{}]);
  const [seatBooked,setSeatBooked] = useState([{}])
  const [token, setToken] = useState(window.localStorage.getItem("token"));


   useEffect(() => {
     fetch("http://10.191.80.100:9090/api/employee/seatnumber/2023-04-05", {
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
        setSeatBooked(JSON.parse(text))
       
        
      });
      
  },[]);

  useEffect(()=>{
    const numSeats = 50; // Change this to the desired number of seats
    const newSeats = [];
    for (let i = 1; i <= numSeats; i++) {
      const seatName = `G${i}`;
      
      const isBooked = seatBooked.some((seat) =>seat === seatName);
      newSeats.push({
        id: i,
        name: seatName,
        booked: isBooked,
        selected: false,
      });
    }
    setSeats(newSeats);
  },[seatBooked])
 
 

  const [selected, setSelected] = useState({});

  const handleSeatClick = (name) => {
    console.log(name);
    //logic for deselection
    setSelected({
      seatId: name,
      floorId: "GF",
    });
  };

  const sendData = () => {
    if (selected.seatId != null) {
      localStorage.setItem("seat_name", selected.seatId);
      window.location = "/";
    } else {
      alert("please select a seat");
    }
  };

  return (
    // <div className='seat-booking-app' >
    //     { <h1>First Floor</h1> }
    //     <div className='seat-map'>
    //        {divs}
    //   </div>
    //   <div id='legend'>
    //   <div class="seat"></div> <div class="txt">Available</div>
    //   <div class="seat taken"></div> <div class="txt">Taken</div>
    //   <div class="seat selected"></div> <div class="txt">Your Chosen Seats</div>

    //   </div>
    // </div>
    <div className="seat-booking-app">
      <div>
        <h1>Ground Floor</h1>
      </div>

      <div className="seat-map">
        {seats.map((seat) =>
          seat.booked ? (
            <div>
              <label>
                {seat.name}
                <input id={seat.id} className="seat booked" disabled>
                </input>
              </label>{" "}
            </div>
          ) : (
            <div>
              <label>
                {seat.name}
                <input
                  className="seat"
                  name="test"
                  // checked={}
                  value={seat.name}
                  onClick={() => handleSeatClick(seat.name)}
                  type="radio"
                />
              </label>
            </div>
          )

          // }
        )}
      </div>
      <br />
      <div id="legend">
        <label>
          Available seats
          <input className="seat" name="test" type="radio" disabled />
        </label>
        <label>
          selected seats
          <input
            className="seat select"
            name="test"
            type="radio"
            disabled
          />{" "}
        </label>{" "}
        <label>
          Booked seats
          <input className="seat booked" name="test" type="radio" disabled />
        </label>
        <div>
          Your Chosen Seats
          {!seats.booked && <div>{selected.seatId}</div>}
        </div>
       
      </div>
      <button onClick={sendData} className="btn btn-warning Nextbtn">
        submit
      </button>
      
    </div>
  );
}



export default SeatMatrix;
