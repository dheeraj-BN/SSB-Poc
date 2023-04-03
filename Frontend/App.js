// import axios from "axios";
// import React from "react";

// function App() {
//   axios.get('http://java_be2:9002/hello')
// .then(Response => {
// // console.log(response=>{
//   alert(Response.data)

// });
//   return (
//    <div>
//     <p> Hello World</p>

//    </div>
//   )
// }

// export default App
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import SeatModify from "./components/UserDash/modifySeat";
import SeatMatrix from "./components/UserDash/Groundfloor";
import SeatMatrix1 from "./components/UserDash/FirstFloor";
import SeatMatrix2 from "./components/UserDash/Secondfloor";
import FloorList from "./components/UserDash/floorList";
import QRGenerator from "./components/UserDash/qrgenerator";
import SeatBookingForm from "./components/UserDash/BookingForm" ;
import UserDashBoardBoard from "./components/UserDash/UserDashboard"



function App() {
 

  return (
   <div>
  
    <Router>
      <Routes>
        <Route path="/"  element={<UserDashBoardBoard/>} />
        <Route path="/booking" element={<SeatBookingForm/>} />
        <Route path="/floorlist" element={<FloorList/>}/>
        <Route path="/groundfloor" element={<SeatMatrix/>}/>
        {/* <Route path="/seatsample" element={<SeatBooking/>}/> */}
        <Route path="/firstfloor" element={<SeatMatrix1/>}/>
        <Route path="/secondfloor" element={<SeatMatrix2/>}/>
        <Route path="/modify" element={<SeatModify/>}/>


        <Route path="/qr" element={<QRGenerator/>}/>



      </Routes>
    </Router>

   </div>
  )
}

export default App;