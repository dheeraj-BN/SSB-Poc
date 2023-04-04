import React from "react";
import ProfileAd from "./ProfileAd";
import Sidebar1 from "./Sidebar";
import '../../css/adminDashboard/profile.css'


function ProfileAdmin(props) {
  return (
    <div className="profilepage">
      <div className="item3">
        <Sidebar1/>
      </div>
      <div className="item4">
        <ProfileAd/>
      </div>
    </div>
  );
}

export default ProfileAdmin;
