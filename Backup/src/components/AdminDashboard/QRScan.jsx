import React, { useState } from "react";
import "../../css/QRSCAN.css";
import QrCodeScan from "../QrCodeScan";
import Sidebar1 from "./Sidebar";

function QRScan(props) {
    let [qr,setQr] = useState('')
  return (
    <div>
      <div className='qrscan'>
        <div>
          <Sidebar1 />
        </div>
        <div className="qrreader">
          <QrCodeScan  data={setQr}/>
          <div className="qrdata">
            <p>Id : <span>5430</span> </p>
            <p>Name : <span>Archies Singh</span> </p>
            <p>Time : <span>1:00pm</span> </p>
          </div>
        </div>
      </div>    
    </div>
  );
}

export default QRScan;
