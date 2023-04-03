
import { useEffect, useState } from "react";
import qrcode from "qrcode";


import axios from "axios";
function QRGenerator(){
const [imgQR, setImageQR] = useState();

    
    useEffect(() => {
      axios.get("https://reqres.in/api/users").then(async(response) => {
        const image = await qrcode.toDataURL(response.data.data[1].email)
        setImageQR(image);
      }); 
    }, []);
    return (
        <div className="container mx-auto mt-2">
             <div className="row">
            <h2 className="col-sm-10 badges bg-danger text-center text-white">QrCode generator</h2>
        </div>
      <div class="card col-sm-3 m-2">
        <div class="card-header m-1 rounded text-center">
          <h3 class="badges bg-secondary rounded text-center text-light">
            Qrcode Image
          </h3>
        </div>
        <div class="card-body text-center">
        {imgQR && (<a href ={imgQR} download><img src={imgQR} width="100%" alt = "qr code pic is here"/></a>)}
        </div>
      </div>
        </div>
    );
}
export default QRGenerator;

