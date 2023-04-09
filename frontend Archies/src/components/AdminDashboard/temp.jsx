import React, { useEffect, useState } from "react";
import MUIDataTable from "mui-datatables";
import "../css/adminDashboard/employee.css";
import axios from 'axios'
import _ from "lodash"

function EmployeeData(props) {

  const [data, setData] = useState()
  const [token, setToken] = useState(window.localStorage.getItem("token"))
  const [date,setDate] = useState('')
  
 
  const columns = [
    {
      name: "bookingId",
      label: "ID",
      options: {
        filter: true,
        sort: false,
      },
    },
    {
      name: "seatNo",
      label: "SEAT",
      options: {
        filter: true,
        sort: false,
      },
    },
    {
      name: "foodStatus",
      label: " FOOD STATUS",
      options: {
        filter: true,
        sort: false,
      },
    },
    {
      name: "bookedTimings",
      label: "TIMING",
      options: {
        filter: true,
        sort: false,
      },
    },
    {
      name: "bookingStatus",
      label: "STATUS",
      options: {
        filter: true,
        sort: false,
      },
    },
    {
      name: "userDeatils.employee.employeeName",
      label: "NAME",
      options: {
        filter: true,
        sort: false,
      },
    },
    {
      name: 'employeeEmail',
      label: "Email",
      options: {
        filter: true,
        sort: false,
      },
    },
    {
      name: "employee phonenumber",
      label: "PHONE",
      options: {
        filter: true,
        sort: false,
      },
    },
    {
      name: "shiftDetails.shiftTimings",
      label: "SHIFT TIMING",
      options: {
        filter: true,
        sort: false,
      },
    },
    {
      name: "date",
      label: "Date",
      options: {
        filter: true,
        sort: false,
      },
      
    },
  ];

  const options = {
    filterType: "checkbox",
  };
  // http://10.191.80.98:9090/api/admin/bookings/count/2023-03-28
  // function flattenObject(obj, prefix = '') {
  //   return Object.keys(obj).reduce((acc, k) => {
  //     const pre = prefix.length ? prefix + '.' : '';
  //     if (typeof obj[k] === 'object' && obj[k] !== null) {
  //       Object.assign(acc, flattenObject(obj[k], pre + k));
  //     } else {
  //       acc[pre + k] = obj[k];
  //     }
  //     return acc;
  //   }, {});
  // }
  


  // important
  useEffect(()=>{
    fetch(`http://40.88.23.186:9090/api/admin/date/${new Date().toISOString().substr(0,10)}`, {
      method: "GET",
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
        // setShifts(newShifts);
        // console.log(data)
        setData(data)
        // setData(Object.values(data).flat())
        console.log(data)
        
      })
      .catch((error) => {
        console.error("There was an error deleting the shift:", error);
      });
  },[])


  return(
    <div>
      <MUIDataTable
        title={"Employee List"}
        data={data}
        columns={columns}
        options={options}
      />

      {console.log("These is me",data)}
    </div>
  )
}

export default EmployeeData;
