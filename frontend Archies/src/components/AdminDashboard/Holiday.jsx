import React, { useState } from "react";
import Sidebar1 from "./Sidebar";


function Holiday() {
  const [holidays, setHolidays] = useState([]);
  const [editingIndex, setEditingIndex] = useState(-1);

  function handleSubmit(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const holiday = {
      name: formData.get('name'),
      date: formData.get('date'),
      description: formData.get('description'),
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
    const form = document.querySelector('form');
    form.elements.name.value = holiday.name;
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
      <div>
        <h2>{holiday.name}</h2>
        <p>{holiday.date}</p>
        <p>{holiday.description}</p>
        <button onClick={() => handleEdit(index)}>Edit</button>
        <button onClick={() => handleDelete(index)}>Delete</button>
      </div>
    );
  }

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <label>
          Name:
          <input type="text" name="name" />
        </label>
        <br />
        <label>
          Date:
          <input type="date" name="date" type="date" name="name" className="form-control" />
        </label>
        <br />
        <label>
          Description:
          <textarea name="description" />
        </label>
        <br />
        <button type="submit">{editingIndex === -1 ? 'Add Holiday' : 'Save Holiday'}</button>
      </form>
      {holidays.map((holiday, index) => (
        <HolidayItem key={index} index={index} holiday={holiday} />
      ))}
    </div>
  );
}



export default Holiday


// <label>
//           Date : 
//           <input type="date" name="name" className="form-control" />
//         </label>
//         <br />
//         <label>
//           Description
//           <textarea type="text" name="date" className="form-control" />
//         </label>