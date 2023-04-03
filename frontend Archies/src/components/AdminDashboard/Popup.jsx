import React from "react";
import "../../css/adminDashboard/Popup.css";

function Popup(props) {
  return (
    <div className="popup">
      <h2>Popup Title</h2>
      <form>
        <label>
          Name:
          <input type="text" name="name" />
        </label>
        <label>
          Email:
          <input type="email" name="email" />
        </label>
        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default Popup;
