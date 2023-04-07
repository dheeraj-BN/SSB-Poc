import React, { useContext, useEffect, useState } from "react";
import axios from "axios";
import "../../css/adduser_popup.css";
import Sidebar1 from "./Sidebar";

function AddUser() {
  const [empId, setEmpId] = useState(0);

  const [token, setToken] = useState(window.localStorage.getItem("token"));
  const [data, setData] = useState(''); 

  useEffect(()=>{
    
  },[])

  function addData() {

  //   axios
  //     .put("http://10.191.80.98:9090/api/admin/updateShiftTime/4?shift_timings=", {
  //       headers: {
  //         'Authorization': `Bearer ${token}`,
  //         'Content-Type': 'application/json'
  //       },
  //     })
  //     .then(response => {
  //       console.log(response.data)
  //     }
      
      
  //     )
  //     .then(data => console.log(data))
  //     .catch(error => console.error(error))
  }

  // fetch("http://10.191.80.98:9090/api/admin/updateShiftTime/4?shift_timings=VHBCG", {
  //   method: 'PUT',
  //   headers: {
  //     'Authorization': 'Bearer ' + token,
  //     'Content-Type': 'application/json'
  //   }
  // })
  //   .then(response => {
  //     // Handle the response
  //   })
  //   .catch(error => {
  //     // Handle the error
  //   });

  fetch("http://10.191.80.98:9090/api/admin/addUser/"+empId, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + token
    },
    
    // body: JSON.stringify(data) 
  })
  .then(response => {
    if(!response.ok){
      throw new Error(`HTTP error: ${response.status}`)
    }
    return response.text()
    // console.log(response.data)
  })
  .then((text)=>{
    console.log(text)
  })
  .catch(error => console.log(error));
  
  return (
    <div className="adduserform">
      <div>
        <Sidebar1 />
      </div>
      <div className="main">
        <div className="popup container">
          <form className="alignments" onSubmit={(e) => e.preventDefault()}>
            <label htmlFor="exampleInputEmail1" className="form-label">
              Enter your Employee Id
            </label>
            <input
              type="text"
              className="form-control"
              id="exampleInputEmail1"
              onBlur={(e) => setEmpId(Number(e.target.value))}
              aria-describedby="emailHelp"
            />
            <p>{}</p>
            <button type="submit" onClick={addData} className="btn btn-primary">
              Submit
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}
export default AddUser;

// Create header and session
