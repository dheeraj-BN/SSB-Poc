import { useEffect, useState } from "react";
import qrcode from "qrcode";

import axios from "axios";
function QRGenerator() {
  const [imgQR, setImageQR] = useState();

  useEffect(() => {
    axios.get("https://reqres.in/api/users").then(async (response) => {
      const image = await qrcode.toDataURL(response.data.data[1].email);
      setImageQR(image);
    });
  }, []);
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
