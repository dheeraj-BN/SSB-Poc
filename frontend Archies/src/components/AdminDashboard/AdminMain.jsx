import React from "react";
import Sidebar from "./Sidebar";
// import AdminData from "./AdminData";
import CardDes from "./Card";
import "../../css/adminDashboard/AdminMain.css";
import FCards from "./FncCards";

function AdminMain(props) {

  const card1 = {
    color : "#D6EDFF"
  }
  const card2 = {
    color : "#19323C"
  }
  const card3 = {
    color : "#D5D887"
  }
  return (
    <div>
      <div className="adminPage">
        <div>
          <Sidebar />
        </div>
        <div className="parentempdata">
          <div className="empData">
            <CardDes
              color="Primary"
              title="Available."
              counts="99"
              onclick={() => {
                alert("yes i am here");
              }}
            />
            <CardDes color="Danger" title="Not Available" counts="40" />
            <CardDes color="Success" title="Food Counts." counts="90" />
          </div>
          <div className="fundata">
            <FCards img = "https://pocproject.000webhostapp.com/images/QRScan.png" title="QR Scan" onclick={(e)=>{alert("I am QR Scan")}} color={card1.color}/>
            <FCards img = "https://pocproject.000webhostapp.com/images/AddUser.png" title="ADD User" onclick={(e)=>{alert("I am Add User")}} color={card2.color}/>
            <FCards img = "https://pocproject.000webhostapp.com/images/Employee_Data.png" title="Employee data" onclick={(e)=>{alert("I am Employee Data")}} color={card3.color}/>
          </div>
        </div>
      </div>
    </div>
  );
}

export default AdminMain;
