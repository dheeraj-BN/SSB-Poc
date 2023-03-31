import "bootstrap/dist/css/bootstrap.min.css";
import "./css/loginform.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import AdminMain from "./components/AdminDashboard/AdminMain"
import QrCodeScan from "./components/QrCodeScan";
// import AdminData from "./components/AdminDashboard/AdminData";

// import AdminDash from "./components/AdminDash"
import AddUser from "./components/AddUser"
// import SignIn from "./routes/SignIn"
import LoginForm from "./routes/LoginForm";
// import EmailVer from "./routes/EmailVer"
// import ResetPassword from "./routes/ResetPassword"

function App() {
  return (
    
    <BrowserRouter>
    <div>
    
      <Routes>
        <Route path="/login" element={<LoginForm/>} />
        {/* <Route path="/login" element={<SignIn/>} /> */}
        {/* <Route path="/email" element={<EmailVer/>} />
  <Route path="/resetpass" element={<ResetPassword/>} /> */}
        {/* <Route path="*" element={<AdminDash/>}/> */}
        <Route path="/adduser" element={<AddUser/>} />
        <Route path="/admin" element={<AdminMain/>}/>
        <Route path="/qrcodescan" element={<QrCodeScan/>} />
      </Routes>
      </div>
    </BrowserRouter>  
  );
}

export default App;
