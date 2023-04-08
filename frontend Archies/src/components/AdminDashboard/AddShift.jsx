import React, { useEffect, useState } from "react";
import "../../css/adminDashboard/addshift.css";
import Sidebar1 from "./Sidebar";
import axios from "axios";

function AddShift(props) {
  const [shifts, setShifts] = useState([]);
  const [editingIndex, setEditingIndex] = useState(-1);
  const [time, setTime] = useState(
    new Date().toLocaleTimeString([], { hour: "2-digit", minute: "2-digit" })
  );
  const [token, setToken] = useState(window.localStorage.getItem("token"));

  useEffect(() => {
    fetch("http://40.88.23.186:9090/api/admin/getAllShiftDetails", {
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
        setShifts(JSON.parse(data));
      })
      .catch((error) => console.log(error));
  }, [token]);

  // function handleSubmit(event) {
  //   event.preventDefault();
  //   const formData = new FormData(event.target);
  //   const shifttime = {
  //     //   name: formData.get("name"),
  //     from: formData.get("time_from"),
  //     to: formData.get("time_to"),
  //   };

  //   if (editingIndex === -1) {
  //     setShifts([...shifts, shifttime]);
  //   } else {
  //     const newShift = [...shifts];
  //     newShift[editingIndex] = shifts;
  //     setShifts(newShift);
  //     setEditingIndex(-1);
  //   }
  //   event.target.reset();
  // }

  // http://10.191.80.98:9090/api/admin/addShiftTime?shiftTime=dvgeabgwebg
  function handleSubmit(event) {
    event.preventDefault();
    const formData = new FormData(event.target);
    const shifttime = {
      from: formData.get("time_from"),
      to: formData.get("time_to"),
    };
    // console.log(shifttime.from+"-"+shifttime.to)
    fetch(
      `http://40.88.23.186:9090/api/admin/addShiftTime?shiftTime=${
        shifttime.from + "-"
      }${shifttime.to}`,
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: "Bearer " + token,
        },
        body: JSON.stringify(shifttime),
      }
    )
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error: ${response.status}`);
        }
        return response.text();
      })
      .then((data) => {
        setShifts([...shifts, JSON.parse(data)]);
        event.target.reset();
      })
      .catch((error) => console.log(error));
  }

  const handleTimeChange = (event) => {
    setTime(event.target.value);
  };

  function handleEdit(index) {
    setEditingIndex(index);
    const shiftime = shifts[index];
    const form = document.querySelector("form");
    // form.elements.name.value = holiday.name;
    form.elements.time_from.value = shiftime.from;
    form.elements.time_to.value = shiftime.to;
  }
  // function handleEdit(index) {
  //   setEditingIndex(index);
  //   const shiftime = shifts[index];
  //   const form = document.querySelector("form");
  //   // form.elements.name.value = holiday.name;
  //   form.elements.time_from.value = shiftime.from;
  //   form.elements.time_to.value = shiftime.to;

  //   fetch(
  //     `http://10.191.80.98:9090/api/admin/updateShiftTime/1?shift_timings=${
  //       shiftime.from + "-"}${shiftime.to}`,
  //     {
  //       method: "PUT",
  //       headers: {
  //         "Content-Type": "application/json",
  //         Authorization: `Bearer ${token}`,
  //       },
  //     }
  //   )
  //     .then((response) => response.json())
  //     .then((data) => {
  //       // Update the form fields with the fetched data
  //       form.elements.time_from.value = data.time_from;
  //       form.elements.time_to.value = data.time_to;
  //     })
  //     .catch((error) => {
  //       console.error("Error fetching data:", error);
  //     });
  // }

  // function handleDelete(index) {
  //   const newShifts = [...shifts];
  //   newShifts.splice(index, 1);
  //   setShifts(newShifts);
  // }
  function handleDelete(index) {
    const shiftId = shifts[index].id;
    const newShifts = [...shifts];
    newShifts.splice(index, 1);

    const token = "your-token";

    fetch(`http://40.88.23.186:9090/shifts/${shiftId}`, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        }
        return response.json();
      })
      .then((data) => {
        setShifts(newShifts);
      })
      .catch((error) => {
        console.error("There was an error deleting the shift:", error);
      });
  }

  function ShiftItem({ shift, index }) {
    return (
      <div className="scrollable-table">
        {/* <h2>{holiday.name}</h2> */}
        <table className="tableholiday" key={index}>
          <thead>
            <tr>
              <th>From</th>
              <th>To</th>
              <th>Edit</th>
            </tr>
          </thead>
          <tbody>
            {shifts.map((shifttime, index) => {
              return (
                <tr key={index}>
                  <td>{shifttime.shiftTimings.split("-")[0]}</td>
                  <td className="scrollable-cell">
                    {shifttime.shiftTimings.split("-")[1]}
                  </td>
                  <td>
                    <button onClick={() => handleEdit(index)}>Edit</button>
                  </td>
                </tr>
              );
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
        <div className="dataform1">
          <div className="item5">
            <ShiftItem />
          </div>
          <div className="form-shift item6">
            <h2>Shifts</h2>
            <form onSubmit={handleSubmit} className="holiday-label">
              {/* <label>
            Name:
            <input type="text" name="name" />
          </label> */}
              {/* <br /> */}
              <div className="user-box">
                <input
                  type="time"
                  id="time-input"
                  name="time_from"
                  step="3600"
                  defaultValue={`${time.split(":")[0]}:00`}
                  onChange={handleTimeChange}
                />
                <label>From</label>
              </div>
              <div className="user-box">
                <input
                  type="time"
                  id="time-input"
                  name="time_to"
                  step="3600"
                  defaultValue={`${time.split(":")[0]}:00`}
                  onChange={handleTimeChange}
                />
                <label>To</label>
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

export default AddShift;
