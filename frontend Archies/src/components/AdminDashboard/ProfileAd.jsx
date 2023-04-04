import React from "react";
import '../../css/adminDashboard/profile.css'

function ProfileAd(props) {
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
                        First name
                      </label>
                      <input
                        className="form-control"
                        id="inputFirstName"
                        type="text"
                        placeholder="Enter your first name"
                      />
                    </div>
                    {/* Form Group (last name)*/}
                    <div className="col-md-6">
                      <label className="small mb-1" htmlFor="inputLastName">
                        Last name
                      </label>
                      <input
                        className="form-control"
                        id="inputLastName"
                        type="text"
                        placeholder="Enter your last name"
                      />
                    </div>
                  </div>
                  {/* Form Row        */}
                  <div className="row gx-3 mb-3">
                    {/* Form Group (organization name)*/}
                    <div className="col-md-6">
                      <label className="small mb-1" htmlFor="inputOrgName">
                        Organization name
                      </label>
                      <input
                        className="form-control"
                        id="inputOrgName"
                        type="text"
                        placeholder="Enter your organization name"
                        defaultValue="Valtech"
                      />
                    </div>
                    {/* Form Group (location)*/}
                    <div className="col-md-6">
                      <label className="small mb-1" htmlFor="inputLocation">
                        Location
                      </label>
                      <input
                        className="form-control"
                        id="inputLocation"
                        type="text"
                        placeholder="Enter your location"
                      />
                    </div>
                  </div>
                  {/* Form Group (email address)*/}
                  <div className="mb-3">
                    <label className="small mb-1" htmlFor="inputEmailAddress">
                      Email address
                    </label>
                    <input
                      className="form-control"
                      id="inputEmailAddress"
                      type="email"
                      placeholder="Enter your email address"
                      defaultValue="name@example.com"
                    />
                  </div>
                  {/* Form Row*/}
                  <div className="row gx-3 mb-3">
                    {/* Form Group (phone number)*/}
                    <div className="col-md-6">
                      <label className="small mb-1" htmlFor="inputPhone">
                        Phone number
                      </label>
                      <input
                        className="form-control"
                        id="inputPhone"
                        type="tel"
                        placeholder="Enter your phone number"
                        defaultValue="XXXXXXXXXX"
                      />
                    </div>
                    {/* Form Group (birthday)*/}
                    <div className="col-md-6">
                      <label className="small mb-1" htmlFor="inputBirthday">
                        Birthday
                      </label>
                      <input
                        className="form-control"
                        id="inputBirthday"
                        type="text"
                        name="birthday"
                        placeholder="Enter your birthday"
                        defaultValue="DD/MM/YYYY"
                      />
                    </div>
                  </div>
                  {/* Save changes button*/}
                  <button className="btn btn-primary" type="button">
                    Save changes
                  </button>
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
