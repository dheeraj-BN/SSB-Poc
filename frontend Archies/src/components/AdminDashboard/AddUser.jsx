import React, { useContext, useEffect, useState } from "react";
import axios from "axios";
import "../../css/adduser_popup.css";
import Sidebar1 from "./Sidebar";

function AddUser() {
  const [empId, setEmpId] = useState(0);

  const [token, setToken] = useState(window.localStorage.getItem("token"));

  // useEffect(()=>{
  //   axios.post("http://10.191.80.98:9090/login").then((db)=>{
  //       // setToken(db.data.)
  //       console.log(db.data)

  //   });

  // },[])

  function addData() {
    // axios
    //   .post("http://10.191.80.98:9090/api/admin/addUser/" + empId)
    //   .then((res) => {
    //     if (res.data === "USER ALREDY EXIST") {
    //       window.location = "/";
    //     } else {
    //       window.location = "/adduser";
    //     }
    //   });
    axios
      .put("http://10.191.80.98:9090/api/admin/updateShiftTime/4?shift_timings=", {
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        },
      })
      .then(response => {
        console.log(response.data)
      }
      
      
      )
      .then(data => console.log(data))
      .catch(error => console.error(error))
  }
  // useEffect(()=>{
  //     axios.post("https://reqres.in/api/users").then((res)=>{
  //         console.log(res.data);
  //     })
  // },[])
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
            {console.log(token)}
            <input
              type="text"
              className="form-control"
              id="exampleInputEmail1"
              onBlur={(e) => setEmpId(Number(e.target.value))}
              aria-describedby="emailHelp"
            />
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
