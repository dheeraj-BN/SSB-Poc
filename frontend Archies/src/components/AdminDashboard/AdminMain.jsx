import React from "react";
import Sidebar from "./Sidebar";
// import AdminData from "./AdminData";
import CardDes from "./Card";
import '../../css/adminDashboard/AdminMain.css'

function AdminMain(props) {
  return (
    <div>
      <div className="adminPage">
        <div>
          <Sidebar />
        </div>
        <div className="empData">
            <CardDes
              color="Primary"
              title="Available."
              counts="99"
              onclick={() => {
                alert("yes i am here");
              }}
            />
            <CardDes color="Danger" title="Not Available" counts="40" />
            <CardDes color="Success" title="Food Counts." counts="90" />
        </div>

      </div>
    </div>
  );
}

export default AdminMain;
