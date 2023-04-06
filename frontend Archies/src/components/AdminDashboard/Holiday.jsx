import React, { useState } from "react";
import "../../css/adminDashboard/Holiday.css";
import Sidebar1 from "./Sidebar";

function Holiday() {
  const [holidays, setHolidays] = useState([]);
  const [editingIndex, setEditingIndex] = useState(-1);

  function handleSubmit(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const holiday = {
      //   name: formData.get("name"),
      date: formData.get("date"),
      description: formData.get("description"),
    };

    if (editingIndex === -1) {
      setHolidays([...holidays, holiday]);
    } else {
      const newHolidays = [...holidays];
      newHolidays[editingIndex] = holiday;
      setHolidays(newHolidays);
      setEditingIndex(-1);
    }
    event.target.reset();
  }

  function handleEdit(index) {
    setEditingIndex(index);
    const holiday = holidays[index];
    const form = document.querySelector("form");
    // form.elements.name.value = holiday.name;
    form.elements.date.value = holiday.date;
    form.elements.description.value = holiday.description;
  }

  function handleDelete(index) {
    const newHolidays = [...holidays];
    newHolidays.splice(index, 1);
    setHolidays(newHolidays);
  }

  function HolidayItem({ holiday, index }) {
    return (
      <div className="scrollable-table">
        {/* <h2>{holiday.name}</h2> */}
        <table className="tableholiday" key={index}>
          <thead>
          <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Edit</th>
            <th>Delete</th>
          </tr>
          </thead>
          <tbody>
            {holidays.map((holiday, index)=>{
              return(
                <tr key={index}>
                <td>{holiday.date}</td>
                <td className="scrollable-cell">{holiday.description}</td>
                <td><button onClick={() => handleEdit(index)}>Edit</button></td>
                <td><button onClick={() => handleDelete(index)}>Delete</button></td>
              </tr>
              )
            })}
          </tbody>
        </table>
      </div>
    );
  }

  return (
    <div className="holiday">
      <div>
        <Sidebar1 />
      </div>
      <div>
        <div className="dataform">
          <div className="item5">
            <HolidayItem />
          </div>
          <div className="form-holiday item6">
            <h2>Holiday Form</h2>
            <form onSubmit={handleSubmit} className="holiday-label">
              {/* <label>
          Name:
          <input type="text" name="name" />
        </label> */}
              {/* <br /> */}
              <div className="user-box">
                <input type="date" name="date" />
                <label>Date</label>
              </div>
              <div className="user-box">
                <textarea name="description" />
                <label> Description </label>
              </div>
              <button type="submit">
                <span></span>
                <span></span>
                <span></span>
                <span></span> 
                {editingIndex === -1 ? "Add Holiday" : "Save Holiday"}
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Holiday;
