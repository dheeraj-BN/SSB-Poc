import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';

function Logout(props) {
    // const history = useHistory()
  const [token, setToken] = useState(window.localStorage.getItem("token"))

    useEffect(()=>{
         fetch(`http://40.88.23.186:9090/logout`, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error("Network response was not ok");
        } 
        return response.text();
      })
      .then((data) => {
        // setShifts(newShifts);
        // console.log(data)
        // setqrdata(data)
        
      })
      .catch((error) => {
        console.error("There was an error deleting the shift:", error);
      });
    },[])


    function handleSignout(){

        localStorage.removeItem("token")
        localStorage.removeItem("id")
        localStorage.removeItem("to date")
        localStorage.removeItem("RaStore.users.listParams")
        localStorage.removeItem("Meal")
        localStorage.removeItem("HTML5_QRCODE_DATA")
        localStorage.removeItem("username")
        localStorage.removeItem("om date")
        localStorage.removeItem("ShiftTiming")
        localStorage.removeItem("userId")
        localStorage.removeItem("floorid")
        localStorage.removeItem("seat_name")
        localStorage.removeItem("from date")
        localStorage.removeItem("userId")

    }


    return (
        <div>
            <button className='btn btn-danger' onClick={handleSignout}><a href='/login' style={{color:"white"}}>Logout</a></button>
        </div>
    );
}

export default Logout;