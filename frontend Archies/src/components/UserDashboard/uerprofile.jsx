import React from "react";
import ProfileAd from "../AdminDashboard/ProfileAd";
import '../../css/adminDashboard/profile.css'
import NavBar from "./NavBar";


function ProfileUser() {
  return (
    <div>
      <div>
        <NavBar/>
      </div>
      <div >
        <ProfileAd/>
      </div>
      </div>
   
  );
}

export default ProfileUser;
