import React, {useState }  from "react";
import axios from "axios"
import '../../css/adduser_popup.css'


function AddUser(){
    const [popup,setPop]=useState(false)
    const [empId,setEmpId]=useState(0)
    
    const handleClickOpen=()=>{
        setPop(!popup)
    }
    const closePopup=()=>{
        setPop(false)
    }
function addData(){
    axios.post("http://10.191.80.98:9090/api/admin/addUser/"+empId).then((res)=>{
            if(res.data === "USER ALREDY EXIST"){
                window.location = "/"
            }
            else{
                window.location = "/adduser"
            }
        })

}
    // useEffect(()=>{
    //     axios.post("https://reqres.in/api/users").then((res)=>{
    //         console.log(res.data);
    //     })
    // },[])
    return(
        <div>
            <button onClick={handleClickOpen} className="btn btn-primary">Add User +</button>
            <div>
                {
                    popup&&
                    <div className="main">
                        <div className="popup container">
                            <div className="popup-header" onClick={closePopup}>
                                <div className="leftright"></div>
                                <div className="rightleft"></div>
                                <label className="close">close</label>
                            </div>
                            <form className="alignments" onSubmit={(e)=>e.preventDefault()}>
                                        <label htmlFor="exampleInputEmail1" className="form-label">Enter your Employee Id</label>
                                        <input type="text" className="form-control" id="exampleInputEmail1" onBlur={(e)=>setEmpId(Number(e.target.value))} aria-describedby="emailHelp"/>
                                        <button type="submit" onClick={addData} className="btn btn-primary">Submit</button>
                            </form>
                        </div>
                    </div>
                }
            </div>
        </div>
    )
}
export default AddUser;


// Create header and session 
