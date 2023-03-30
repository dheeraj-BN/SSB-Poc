import React from "react";
import CardDes from "./Card";

function AdminData(props) {
  return (
    <div>
      <div className="d-flex justify-content-evenly align-items-baseline flex-wrap"
        style={{ margin: "50px auto",gap:'3rem',cursor:"pointer",maxWidth:"100%"}}>
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
  );
}

export default AdminData;
