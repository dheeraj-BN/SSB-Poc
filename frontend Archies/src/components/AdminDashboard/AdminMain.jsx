import React from "react";
import Sidebar from "./Sidebar";
// import AdminData from "./AdminData";
import CardDes from "./Card";
function AdminMain(props) {
  return (
    <div>
      <div style={{ display: "flex", gap: "3rem", flexWrap: "initial" }}>
        <div>
          <Sidebar />
        </div>
        <div>
          {/* <AdminData/> */}
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
