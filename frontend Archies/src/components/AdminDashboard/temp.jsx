import React, { useEffect, useState } from "react";
import "../../css/adminDashboard/Holiday.css";
import Sidebar1 from "./Sidebar";

function Holiday() {
  const [holidays, setHolidays] = useState([]);
  const [editingIndex, setEditingIndex] = useState(-1);
  const [token, setToken] = useState(window.localStorage.getItem("token"));
  const [date, setDate] = useState()
  const [desc,setDesc] = useState()

  useEffect(() => {
    fetch("http://40.88.23.186:9090/api/admin/allHolidays", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + token,
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error: ${response.status}`);
        }
        return response.text();
      })
      .then((data) => {
        setHolidays(JSON.parse(data));
      })
      .catch((error) => console.log(error));
  }, [token]);

  function handleSubmit(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const holiday = {
      //   name: formData.get("name"),
      date: formData.get("date"),
      description: formData.get("description"),
    };

    fetch(
      `http://10.191.80.98:9090/api/admin/addholiday`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + token,
        },
        body: JSON.stringify(holiday),
      }
    )
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error: ${response.status}`);
        }

        return response.text();
      })
      .then((data) => {
        setHolidays([...holidays, JSON.parse(data)]);
        event.target.reset();
      })
      .catch((error) => console.log(error));

    // if (editingIndex === -1) {
    //   setHolidays([...holidays, holiday]);
    // } else {
    //   const newHolidays = [...holidays];
    //   newHolidays[editingIndex] = holiday;
    //   setHolidays(newHolidays);
    //   setEditingIndex(-1);
    // }
    // event.target.reset();
  }

  const handledateChange = (event)=>{
    setDate(event.target.value)
  }

  const descriptionChange = (event)=>{
    setDesc(event.target.value)
  }

  //http://10.191.80.98:9090/api/admin/modifiHoliday?date=2023-05-06
  // function handleEdit(index) {
  //   setEditingIndex(index);
  //   const holiday = holidays[index];
  //   const form = document.querySelector("form");
  //   // form.elements.name.value = holiday.name;
  //   form.elements.date.value = holiday.date;
  //   form.elements.description.value = holiday.description;
    
  // }
  function handleEdit(index) {
    setEditingIndex(index);
    try{
      const response = fetch(`http://10.191.80.98:9090/api/admin/modifiHoliday?date=$`)
      const holiday = response.json();
      const form = document.querySelector("form");
      form.elements.date.value = holiday.date;
      form.elements.description.value = holiday.description;
    }catch(error){
      console.error(error)
    }
    // const holiday = holidays[index];
    // form.elements.name.value = holiday.name;
  }

  function handleDelete(index) {
    const newHolidays = [...holidays];
    newHolidays.splice(index, 1);
    setHolidays(newHolidays);
  }
  // function handleDelete(index) {
  //   const holidayToDelete = holidays[index];
  //   fetch(`http://10.191.80.98:9090/api/admin/deleteholiday/${holidayToDelete.id}`, {
  //     method: "DELETE",
  //     headers: {
  //       "Content-Type": "application/json",
  //       Authorization: "Bearer " + token,
  //     },
  //   })
  //     .then((response) => {
  //       if (!response.ok) {
  //         throw new Error(`HTTP error: ${response.status}`);
  //       }
  //       const newHolidays = [...holidays];
  //       newHolidays.splice(index, 1);
  //       setHolidays(newHolidays);
  //     })
  //     .catch((error) => console.log(error));
  // }

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
                <td>{holiday.holidayDate}</td>
                <td className="scrollable-cell">{holiday.holidayDescription}</td>
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
              <div className="user-box">
                <input type="date" name="date" defaultValue={date} onChange={handledateChange}/>
                <label>Date</label>
              </div>
              <div className="user-box">
                <textarea name="description" defaultValue={desc} onChange={descriptionChange} />
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
      {
        console.log(desc,date)
      }
      {/* const token = 'exampleToken';
const expirationTime = new Date().getTime() + 15 * 60 * 1000; // 15 minutes in milliseconds
sessionStorage.setItem('token', token);
sessionStorage.setItem('expirationTime', expirationTime.toString()); */}

{/* function isTokenExpired() {
  const expirationTime = sessionStorage.getItem('expirationTime');
  return expirationTime !== null && parseInt(expirationTime) < new Date().getTime();
} */}
{/* 
useEffect(() => {
  const interval = setInterval(() => {
    if (isTokenExpired()) {
      // remove the token from session storage when it expires
      sessionStorage.removeItem('token');
      sessionStorage.removeItem('expirationTime');
      // do something else, like log the user out or redirect to the login page
    }
  }, 1000); // check every second
  return () => clearInterval(interval); // clean up the interval when the component unmounts
}, []); */}

    </div>
  );
}

export default Holiday;
