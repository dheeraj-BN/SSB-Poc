import "bootstrap/dist/css/bootstrap.min.css";
import "./css/loginform.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import AdminMain from "./components/AdminDashboard/AdminMain";
import AddUser from "./components/AdminDashboard/AddUser";
import Profile from "./components/AdminDashboard/Profile";
import QRScan from "./components/AdminDashboard/QRScan";
import SeatBookingForm from "./components/UserDashboard/BookingForm"
import SeatMatrix1 from "./components/UserDashboard/FirstFloor"
import FloorList from "./components/UserDashboard/floorList"
import SeatMatrix from "./components/UserDashboard/Groundfloor"
import SeatModify from "./components/UserDashboard/modifySeat"
import QRGenerator from "./components/UserDashboard/qrgenerator"
import SeatMatrix2 from "./components/UserDashboard/Secondfloor"
import UserDashboard from "./components/UserDashboard/UserDash"
// import SignIn from "./routes/SignIn";
import EmployeeInfo from "./components/AdminDashboard/EmployeeInfo";
import EmailVer from "./routes/EmailVer"
import ResetPassword from "./routes/ResetPassword"
import Login from "./routes/LoginForm";
import Developer from "./components/DeveloperDashboard/developer";
import AddFloor from "./components/DeveloperDashboard/addfloor";

function App() {
  return (
    <BrowserRouter>
      <div>
        <Routes>
          {/* <Route path="/" element={<SignIn />} /> */}
          <Route path="/login" element={<Login />} />
          <Route path="/email" element={<EmailVer />} />
          <Route path="/resetpass" element={<ResetPassword />} />
          <Route path="/adduser" element={<AddUser />} />
          <Route path="/admin" element={<AdminMain />} />
          <Route path="/qrcodescan" element={<QRScan />} />
          <Route path="/profileadmin" element={<Profile />} />
          <Route path="/empdata" element={<EmployeeInfo />} />
          {/* user dash */}
          <Route path="/"  element={<UserDashboard/>} />
          <Route path="/booking" element={<SeatBookingForm/>} />
          <Route path="/floorlist" element={<FloorList/>}/>
          <Route path="/groundfloor" element={<SeatMatrix/>}/>
          <Route path="/firstfloor" element={<SeatMatrix1/>}/>
          <Route path="/secondfloor" element={<SeatMatrix2/>}/>
          <Route path="/modify" element={<SeatModify/>}/>
          <Route path="/profileuser" element={<Profile/>}/>  
          {/*Developer dash */}
          <Route path="/developer" element={<Developer/>}/>
          <Route path="/addfloor" element={<AddFloor/>}/>
          <Route path="/modify" element={<SeatModify/>}/>  



        <Route path="/qr" element={<QRGenerator/>}/>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;



