import React, { useState } from "react";
import "../../css/QRSCAN.css";
import QrCodeScan from "../QrCodeScan";
import Sidebar1 from "./Sidebar";

function QRScan(props) {
  let [qr,setQr] = useState('')
  let [empdata,setempData] = useState('')
  
  return (
    <div>
      <div className='qrscan'>
        <div>
          <Sidebar1 />
        </div>
        <div className="qrreader">
          <QrCodeScan data={setQr} emp={setempData}/>
          <div className="qrdata">
            <h2 style={{color:"blue"}}>Employee Details</h2>
            <p>Id : <span>{empdata.employeeId}</span> </p>
            <p>Name : <span>{empdata.employeeName}</span> </p>
            <p>Email : <span>{empdata.employeeEmail}</span> </p>
            <p>Designation : <span>{empdata.employeeDesignation}</span> </p>
            <p>Phone Number : <span>{empdata.employeePhoneNo}</span> </p>
          </div>
        </div>
      </div>    
    </div>
  );
}

export default QRScan;
