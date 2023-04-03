import React, { useEffect, useState } from "react";
import axios from "axios";
import "../../css/adduser_popup.css";
import Sidebar1 from "./Sidebar";

function AddUser() {
  const [empId, setEmpId] = useState(0);
  // const [token , setToken] = useState('')

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
    fetch('http://10.191.80.98:9090/api/admin/addUser/'+ empId, {
      method: 'POST',
      headers: {
        'Authorization': `Bearer ${" eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJBRE1JTiJdLCJzd…Q1OH0.sgZ4FxktfOJ4W1UvXvC52htszQOq-M7LAu7sTljjBS0"}`
      }
    })
      .then(response => {
        // Handle response
        console.log(response.data)
      })
      .catch(error => {
        // Handle error
      });
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
