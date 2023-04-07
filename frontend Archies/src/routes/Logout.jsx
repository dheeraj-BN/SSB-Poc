import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';

function Logout(props) {
    // const history = useHistory()
  const [token, setToken] = useState(window.localStorage.getItem("token"))

    useEffect(()=>{
         fetch(`http://10.191.80.98:9090/logout`, {
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
        // history.replace('/login')

    }


    return (
        <div>
            <button className='btn btn-danger' onClick={handleSignout}><a href='/login' style={{color:"white"}}>Logout</a></button>
        </div>
    );
}

export default Logout;