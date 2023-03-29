import React, { useEffect } from 'react';
import '../components/floorList.css';
import axios from 'axios';

function FloorList(){
    useEffect(()=>{
        axios.get('http://10.191.80.109:9090/api/admin/validateToken/?token=280320236')
    .then(resp=>{
        console.log(resp.data)
    },[])
    })
    return(
       
            
        <div className='fList'>
            <h3>Select Floor</h3>
            <div className="floor0">
                
                <button className='floorbtn'>Ground Floor</button>
            </div>
            <div className="floor0">
                
                <button className='floorbtn'>First Floor</button>
            </div>
            <div className="floor0">
                
                <button className='floorbtn'>Second Floor</button>
            </div>
           
        </div>
        
    )
}
export default FloorList;