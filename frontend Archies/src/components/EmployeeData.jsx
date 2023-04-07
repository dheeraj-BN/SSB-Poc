import React, { useEffect, useState } from "react";
import MUIDataTable from "mui-datatables";
import "../css/adminDashboard/employee.css";
import axios from 'axios'

function EmployeeData(props) {

  const [data, setData] = useState([])
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
      name: 'userDeatils["employee"]["employeeEmail"]',
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
      name: "shift timings",
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

  // important
  useEffect(()=>{
    fetch(`http://10.191.80.98:9090/api/admin/date/${new Date().toISOString().substr(0,10)}`, {
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
        console.log(data)
        
      })
      .catch((error) => {
        console.error("There was an error deleting the shift:", error);
      });
  },[])

  // useEffect(()=>{
  //   // axios.get("https://jsonplaceholder.typicode.com/users").then(
  //   //     response=>{
  //   //         setData(response.data)
  //   //     })
  //   //     .catch(error=>{
  //   //         console.log(error)
  //   //     })
      
  //     fetch(`http://10.191.80.98:9090/api/admin/bookings/count/${date}`, {
  //     method: "GET",
  //     headers: {
  //       Authorization: `Bearer ${token}`,
  //     },
  //   })
  //     .then((response) => {
  //       if (!response.ok) {
  //         throw new Error("Network response was not ok");
  //       } 
  //       return response.json();
  //     })
  //     .then((data) => {
  //       // setShifts(newShifts);
  //       // console.log(data)
  //       setData(data)
        
  //     })
  //     .catch((error) => {
  //       console.error("There was an error deleting the shift:", error);
  //     });
      
  // },[date])


  // fetch(`http://10.191.80.98:9090/api/admin/validateToken/?token=${webcamResult}`, {
  //     method: "PUT",
  //     headers: {
  //       Authorization: `Bearer ${token}`,
  //     },
  //   })
  //     .then((response) => {
  //       if (!response.ok) {
  //         throw new Error("Network response was not ok");
  //       } 
  //       return response.json();
  //     })
  //     .then((data) => {
  //       // setShifts(newShifts);
  //       // console.log(data)
  //       setqrdata(data)
        
  //     })
  //     .catch((error) => {
  //       console.error("There was an error deleting the shift:", error);
  //     });
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
