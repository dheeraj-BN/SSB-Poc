import React, { useEffect } from "react";
import "../../css/userDashboard/floorList.css";

import axios from "axios";
import { useState } from "react";
import { Link } from "react-router-dom";

function FloorList() {
  const [token, setToken] = useState(window.localStorage.getItem("token"));
  const [data, setData] = useState([{}]);
  

  useEffect(() => {
    fetch("http://10.191.80.98:9090/api/developer/floordetails", {
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
  return (
    <div className="fList1">
      <h3>Select Floor</h3>

   
      {
        data.map((val,idx)=>{
            let a1 = "/floor"+idx
      return <div className="floor0">
        <Link to={a1}>
          <button className="floorbtn">{val.floorName}</button>
        </Link>
      </div>
           
        })
      }
      {/* <div className="floor0">
        <a href="/firstfloor">
          <button className="floorbtn">First Floor</button>
        </a>
      </div>
      <div className="floor0">
        <a href="/secondfloor">
          <button className="floorbtn">Second Floor</button>
        </a>
      </div> */}
    </div>
  );
}
export default FloorList;
