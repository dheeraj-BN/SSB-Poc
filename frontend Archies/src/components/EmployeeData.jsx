import React, { useEffect, useState } from "react";
import MUIDataTable from "mui-datatables";
import "../css/adminDashboard/employee.css";
import axios from 'axios'

function EmployeeData(props) {

  const [data, setData] = useState([])
  
  const columns = [
    {
      name: "id",
      label: "Id",
      options: {
        filter: true,
        sort: false,
      },
    },
    {
      name: "name",
      label: "Name",
      options: {
        filter: true,
        sort: false,
      },
    },
    {
      name: "username",
      label: "UserName",
      options: {
        filter: true,
        sort: false,
      },
    },
    {
      name: "email",
      label: "Email",
      options: {
        filter: true,
        sort: false,
      },
    },
  ];

  const options = {
    filterType: "checkbox",
  };

  useEffect(()=>{
    axios.get("https://jsonplaceholder.typicode.com/users").then(
        response=>{
            setData(response.data)
        })
        .catch(error=>{
            console.log(error)
        })
  },[])
  return(
      <MUIDataTable
        title={"Employee List"}
        data={data}
        columns={columns}
        options={options}
      />
  )
}

export default EmployeeData;
