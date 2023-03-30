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
import SeatBooking from "./components/seatbooking";
import QRGenerator from "./components/qrgenerator";
import UserDashBoard from "./components/UserDashboard";
import DateSelection from "./components/timing";
import SeatBookingForm from "./components/BookingForm";
import FloorList from "./components/floorList";
import UserDashboard from "./components/UserDashboard";
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import SeatMatrix from "./components/Groundfloor";
import SeatMatrix1 from "./components/FirstFloor";
import SeatMatrix2 from "./components/Secondfloor";

function App() {
 

  return (
   <div>
    {/* <p> Hello World</p> */}
    {/* <SeatBooking/> */}
    {/* <QRGenerator/> */}
   {/* <UserDashBoard/> */}
     {/* <SeatBookingForm/> */}
     {/* <FloorList/> */}
    {/* < UserDashboard/> */}
    <Router>
      <Routes>
        <Route path="/"  element={<UserDashBoard/>} />
        <Route path="/booking" element={<SeatBookingForm/>} />
        <Route path="/floorlist" element={<FloorList/>}/>
        <Route path="/groundfloor" element={<SeatMatrix/>}/>
        <Route path="/seatsample" element={<SeatBooking/>}/>
        <Route path="/firstfloor" element={<SeatMatrix1/>}/>
        <Route path="/secondfloor" element={<SeatMatrix2/>}/>


        <Route path="/qr" element={<QRGenerator/>}/>



      </Routes>
    </Router>

   </div>
  )
}

export default App;