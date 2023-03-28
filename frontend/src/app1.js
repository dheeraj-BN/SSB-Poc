

// import React, { useState, useRef } from "react";
// import Webcam from "react-webcam";
// import jsqr from "jsqr";

// function App() {
//   const [qrResult, setQrResult] = useState("");
//   const webcamRef = useRef(null);

//   const handleScan = () => {
//     const imageSrc = webcamRef.current.getScreenshot();
//     const code = jsqr(imageSrc, 1);
//     if (code) {
//       setQrResult(code.data);
//     }
//   };

//   return (
//     <div>
//       <Webcam
//         ref={webcamRef}
//         screenshotFormat="image/jpeg"
//         videoConstraints={{
//           facingMode: "environment",
//         }}
//       />
//       <button onClick={handleScan}>Scan QR Code</button>
//       {qrResult && <p>{qrResult}</p>}
//     </div>
//   );
// }

// export default App;





import React, { useState } from "react";
import QRCode from "react-qr-code";

function App() {
  const [qrData, setQrData] = useState("");

  const generateQrData = () => {
    const randomData = Math.floor(Math.random()*10 +1).toString(2);
    setQrData(randomData);
  };

  return (
    <div>
      <button onClick={generateQrData}>Generate QR Code</button>
      <QRCode value={qrData} />
    </div>
  );
}

export default App;


