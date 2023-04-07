import React, { useContext, useEffect, useState } from "react";
import "../../css/adduser_popup.css";
import Sidebar1 from "./Sidebar";

function AddUser() {
  const [empId, setEmpId] = useState(0);
  const [empdata, setempdata] = useState([{}]);
  const [token, setToken] = useState(window.localStorage.getItem("token"));
  const [data, setData] = useState();

  useEffect(() => {
    fetch(`http://10.191.80.98:9090/api/admin/notRegistered`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error: ${response.status}`);
        }
        return response.text();
        // console.log(response.data)
      })
      .then((data) => {
        setempdata(JSON.parse(data));
        console.log(JSON.parse(data));
      })
      .then((text) => {
        console.log(text);
      })
      .catch((error) => console.log(error));
  }, []);

  function addData() {
    fetch(`http://10.191.80.98:9090/api/admin/addUser/${empId}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },

      // body: JSON.stringify(data)
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error: ${response.status}`);
        }
        return response.text();
        // console.log(response.data)
      })
      .then((text) => {
        setData(text);
      })
      .catch((error) => console.log(error));
  }

  return (
    <div className="adduserform">
      <div>
        <Sidebar1 />
      </div>
      <div className="main">
        <div className="formuser">
          <form onSubmit={(e)=> e.preventDefault()}>
            <div className="form-group">
              <label htmlFor="exampleInputEmail1">Enter Employee ID</label>
              <input
                type="text"
                className="form-control"
                id="exampleInputEmail1"
                onBlur={(e)=> setEmpId(Number(e.target.value))}
                aria-describedby="emailHelp"
                placeholder="User ID"
              />
            </div>
            <p>{data}</p>
            <button type="submit" onClick={addData} className="btn btn-primary">
              Submit
            </button>
          </form>
        </div>
        <div className="scrollabele">
          <table class="table">
            <thead class="thead-dark">
              <tr>
                <th scope="col">ID</th>
                <th scope="col">NAME</th>
                <th scope="col">EMAIL</th>
              </tr>
            </thead>

            <tbody>
              {empdata.map((emp, index) => {
                return (
                  <tr key={index}>
                    <th scope="row">{emp.employeeId}</th>
                    <td>{emp.employeeName}</td>
                    <td>{emp.email}</td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}
export default AddUser;

// Create header and session
