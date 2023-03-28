import "./forgetPassword.css";
function ForgotPassword(){
    return(
        <div className="main">
        <div className="sub-main">
        <div>
              <h1>Enter your Register Email</h1>
              <div>
                <input type="email" placeholder="abc@gmail.com" className="name"/>
              </div>
             <div className="submit-button">
             <button>Submit</button>
             </div>
            </div>
        </div>
           
          </div>
    );
    
}
export default ForgotPassword;