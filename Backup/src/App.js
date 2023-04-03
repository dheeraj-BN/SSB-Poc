import "bootstrap/dist/css/bootstrap.min.css";
import "./css/loginform.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import AdminMain from "./components/AdminDashboard/AdminMain";
import AddUser from "./components/AdminDashboard/AddUser";
import Profile from "./components/AdminDashboard/Profile";
import QRScan from "./components/AdminDashboard/QRScan";
// import SignIn from "./routes/SignIn";
import EmployeeInfo from "./components/AdminDashboard/EmployeeInfo";
import EmailVer from "./routes/EmailVer"
import ResetPassword from "./routes/ResetPassword"
import Login from "./routes/LoginForm";

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
          <Route path="/profile" element={<Profile />} />
          <Route path="/empdata" element={<EmployeeInfo />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
