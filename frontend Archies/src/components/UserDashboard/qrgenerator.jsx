import { useEffect, useState } from "react";
import qrcode from "qrcode";


import axios from "axios";
function QRGenerator() {
  const [imgQR, setImageQR] = useState();
  const [token, setToken] = useState(window.localStorage.getItem("token"));
  const [data, setData] = useState([{}]);
  const [image, setImage]=useState()


  const storedData = localStorage.getItem('userId');

  useEffect(() => {
    fetch("http://40.88.23.186:9090/api/employee/booked/details/"+storedData, {
      method: "GET",

      headers: {
        Authorization: "Bearer " + token,
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error: ${response.status}`);
        }

        return response.text();
      })
      .then((text) => {
        setData(JSON.parse(text));
        
        
     
        
      });
  },[]);
  useEffect(()=>{
    qrcode.toDataURL(data.token).then((img)=>{

      setImageQR(img);
    })
      
  },[data])
  return (
    <div className="">
        <div class="card-body text-center">
          {imgQR && (
            <a href={imgQR} download>
              <img src={imgQR} width="20%" alt="qr code pic is here" />
            </a>
          )}
         
        </div>
      </div>
   
  );
}
export default QRGenerator;
