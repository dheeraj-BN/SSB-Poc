import React, { useState } from "react";
import "../../css/adminDashboard/profile.css";
import { useEffect } from "react";

function ProfileAd(props) {
  const [token, setToken] = useState(window.localStorage.getItem("token"));
  const [data, setdata] = useState([]);

  useEffect(() => {
    fetch("http://10.191.80.98:9090/userInfo/19", {
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
      })
      .then((data) => {
        setdata(JSON.parse(data));
        // console.log(JSON.parse(data))
      })
      .catch((error) => console.log(error));
  });
  return (
    <div>
      <div className="container-xl px-4 mt-4">
        {/* Account page navigation*/}

        <hr className="mt-0 mb-4" />
        <div className="row">
          <div className="col-xl-8">
            {/* Account details card*/}
            <div className="card mb-4">
              <div className="card-header">Account Details</div>
              <div className="card-body">
                <form>
                  {/* Form Row*/}
                  <div className="row gx-3 mb-3">
                    {/* Form Group (first name)*/}
                    <div className="col-md-6">
                      <label className="small mb-1" htmlFor="inputFirstName">
                        Name
                      </label>
                      <input
                        className="form-control"
                        id="inputFirstName"
                        type="text"
                        placeholder="Enter your first name"
                        defaultValue={data.employeeName}
                        disabled
                      />
                    </div>
                    {/* Form Group (last name)*/}
                    <div className="col-md-6">
                      <label className="small mb-1" htmlFor="inputLastName">
                        Designation
                      </label>
                      <input
                        className="form-control"
                        id="inputLastName"
                        type="text"
                        placeholder="Enter your last name"
                        defaultValue={data.employeeDesignation}
                        disabled
                      />
                    </div>
                  </div>
                  {/* Form Row        */}
                  <div className="row gx-3 mb-3">
                    {/* Form Group (organization name)*/}
                    <div className="col-md-6">
                      <label className="small mb-1" htmlFor="inputOrgName">
                        Gender
                      </label>
                      <input
                        className="form-control"
                        id="inputOrgName"
                        type="text"
                        placeholder="Enter your organization name"
                        defaultValue={data.employeeGender}
                        disabled
                      />
                    </div>
                    {/* Form Group (location)*/}
                    <div className="col-md-6">
                      <label className="small mb-1" htmlFor="inputLocation">
                        Employee ID
                      </label>
                      <input
                        className="form-control"
                        id="inputLocation"
                        type="text"
                        placeholder="Enter your location"
                        defaultValue={data.employeeId}
                        disabled
                      />
                    </div>
                  </div>
                  {/* Form Group (email address)*/}
                  <div className="mb-3">
                    <label className="small mb-1" htmlFor="inputEmailAddress">
                      Phone number
                    </label>
                    <input
                      className="form-control"
                      id="inputEmailAddress"
                      type="email"
                      placeholder="Enter your email address"
                      defaultValue={data.employeePhoneNo}
                      disabled
                    />
                  </div>
                  {/* Form Row*/}
                  <div className="row gx-3 mb-3">
                    {/* Form Group (phone number)*/}
                    <div className="col-md-6">
                      <label className="small mb-1" htmlFor="inputPhone">
                        Email address
                      </label>
                      <input
                        className="form-control"
                        id="inputPhone"
                        type="tel"
                        placeholder="Enter your phone number"
                        defaultValue={data.employeeEmail}
                        disabled
                      />
                    </div>
                    {/* Form Group (birthday)*/}
                    <div className="col-md-6">
                      <label className="small mb-1" htmlFor="inputBirthday">
                        Password
                      </label>
                      <input
                        className="form-control"
                        id="inputBirthday"
                        type="password"
                        name="birthday"
                        placeholder="Enter your birthday"
                        defaultValue={data.employeePersonalEmail}
                        disabled
                      />
                    </div>
                  </div>
                  {/* Save changes button*/}
                  <div style={{display:"flex", gap:"1rem" , alignItems:"center", justifyContent:"center"}}>
                    <button className="btn btn-primary" type="button">
                      Save changes
                    </button>
                    <button className="btn btn-primary" type="button">
                      <span>Change password</span>
                    </button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ProfileAd;
