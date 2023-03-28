import "./ResetPassword.css";
function ResetPassword(){
    return(
        <div className="main">
        <div className="sub-main">
             <div>
              <h1>Reset Password</h1>
                <label> New Password </label>
                <input type="text"  className="name"/>
                <br/>
                <label>Confirm Password </label>
                <input type="text"  className="name"/>
             <div className="submit-button">
             <button>Submit</button>
             </div>
            </div>
        </div>
        </div>
                 
    );
    
}
export default ResetPassword;