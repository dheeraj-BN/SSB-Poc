import React, { useEffect, useState } from "react";
import Sidebar from "./Sidebar";
// import AdminData from "./AdminData";
import CardDes from "./Card";
import "../../css/adminDashboard/AdminMain.css";
import FCards from "./FncCards";
import AddUser from "./AddUser";

function AdminMain(props) {
  const [count, setCount] = useState('')
  const [notavai,setNotAvai] = useState('')
  const [token, setToken] = useState(window.localStorage.getItem("token"))
  const [food, setfood] = useState('')

  useEffect(()=>{
    fetch(`http://10.191.80.98:9090/api/admin/bookings/count/${new Date().toISOString().substr(0,10)}`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
          } 
        return response.json();
      })
      .then((data) => {
        setCount(data)
        
      })
      .catch((error) => {
        console.error("There was an error deleting the shift:", error);
      });
  },[count])

  useEffect(()=>{
    fetch(`http://10.191.80.98:9090/api/admin/employee-count/${new Date().toISOString().substr(0,10)}/true`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
          } 
        return response.json();
      })
      .then((data) => {
        setfood(data)
        
      })
      .catch((error) => {
        console.error("There was an error deleting the shift:", error);
      });
  },[food])


  useEffect(()=>{
    fetch(`http://10.191.80.98:9090/api/admin/employee-count/${new Date().toISOString().substr(0,10)}/PENDING`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
          } 
        return response.json();
      })
      .then((data) => {
        setNotAvai(data)
        
      })
      .catch((error) => {
        console.error("There was an error deleting the shift:", error);
      });
  },[notavai])


  const card1 = {
    color : "#D6EDFF"
  }
  const card2 = {
    color : "#19323C"
  }
  const card3 = {
    color : "#D5D887"
  }
  const card4 = {
    color : "#2A1A1F"
  }
  return (
      <div className="adminPage">
        <div>
          <Sidebar />
        </div>
        <div className="parentempdata">
          <div className="empData">
          <div>
            <input style={{display : 'none'}} type="date" defaultValue={new Date().toISOString().substr(0,10)} />
          </div>
            <CardDes
              color="Primary"
              title="Available."
              counts={count}
              onclick={() => {
                alert("yes i am here");
              }}
            />
            <CardDes color="Danger" title="Not Available" counts={notavai} />
            <CardDes color="Success" title="Food Counts." counts={food} />
          </div>
          <div className="fundata">
            <FCards img = "https://pocproject.000webhostapp.com/images/QRScan.png" title="QR Scan" address="/qrcodescan"  color={card1.color}/>
            <FCards img = "https://pocproject.000webhostapp.com/images/AddUser.png" title="ADD User" address="/adduser" color={card2.color} onclick={()=><AddUser/>}/>
            <FCards img = "https://pocproject.000webhostapp.com/images/holidays.png" title="Holiday List" address="/holiday" color={card4.color}/>
            <FCards img = "https://pocproject.000webhostapp.com/images/shift.png" title="Add Shifts" address="/addshift" color={card4.color}/>
            {/* Add holiday , add shift */}
          </div>
        </div>
    </div>
  );
}

export default AdminMain;
