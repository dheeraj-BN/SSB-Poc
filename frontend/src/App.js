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
function App() {
 

  return (
   <div>
    {/* <p> Hello World</p> */}
    {/* <SeatBooking/> */}
    {/* <QRGenerator/> */}
   {/* <UserDashBoard/> */}
     <SeatBookingForm/>

   </div>
  )
}

export default App;