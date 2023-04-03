import React, { useEffect } from 'react';
import "../../css/userDashboard/floorList.css";

import axios from 'axios';

function FloorList(){
    useEffect(()=>{
        axios.get('http://10.191.80.109:9090/api/developer/getAllFloorDetails')
    .then(resp=>{
        console.log(resp.data)
    },[])
    })
    
    return(
       
            
        <div className='fList'>
            <h3>Select Floor</h3>
            <div className="floor0">
                
               {/* <button className='floorbtn'><a href="/groundfloor">Ground Floor</a></button> */}
               <a href="/groundfloor">
               <button className='floorbtn'>Ground Floor</button>
               </a>
            </div>
            <div className="floor0">
            <a href="/firstfloor">   
            <button className='floorbtn'>First Floor</button> 
            </a>
            </div>
            <div className="floor0">
            <a href="/secondfloor">   
            <button className='floorbtn'>Second Floor</button>
            </a>
            </div>
           
            </div>
        
    )
}
export default FloorList;