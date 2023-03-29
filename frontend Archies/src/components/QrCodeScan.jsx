import React from "react";
import { QrReader } from "react-qr-reader";
import { useState } from "react";

function QrCodeScan(props) {
  const [webcamResult, setwebcamResult] = useState();

  return (
    <div className="card col-sm-3 m-2">
      <div className="card-header m-1 rounded">
        <h3 className="badges bg-secondary rounded text-center">
          Webcam Image
        </h3>
      </div>
      <div className="card-body text-center">
        <QrReader
          onResult={(result, error) => {
            if (!!result) {
              setwebcamResult(result?.text);
            }

            if (!!error) {
              console.info(error);
            }
          }}
          style={{ width: "100%" }}
        />
      </div>
      <div className="card-footer rounded mb-1">
        <h6>Webcam Result : {webcamResult} </h6>
      </div>
    </div>
  );
}

export default QrCodeScan;
