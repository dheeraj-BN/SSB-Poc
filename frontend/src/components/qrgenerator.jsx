import axios from 'axios';
import "../components/qrgenerator.css";
import React, { useEffect, useState } from "react";
import QRCode from "react-qr-code";

function App() {
  const [qrData, setQrData] = useState("");
  const [data, setData] = useState({});

  const generateQrData = () => {
    setQrData(String(data[1].phone))
  };

  useEffect(()=>{

    axios.get("https://jsonplaceholder.typicode.com/users")
      .then(res => {
           
        setData(res.data)
      })    
  },[])

  return (
    <div className='QR'>
    
      <button onClick={generateQrData}>Generate QR Code</button>
      <QRCode value={qrData} />
    </div>
  );
}

export default App;

