import React from "react";
import ProfileAd from "../AdminDashboard/ProfileAd";
import '../../css/adminDashboard/profile.css';
import Developer from "./developer";


function ProfileDeveloper() {
  return (
    <div>
      <div>
        <Developer/>
      </div>
      <div >
        <ProfileAd/>
      </div>
      </div>
   
  );
}

export default ProfileDeveloper;
