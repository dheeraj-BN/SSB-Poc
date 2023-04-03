import React from "react";
class ResetPassword extends React.Component{
    constructor() {
        super();
        this.state = {
          input: {},
          errors: {}
        };
         
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
      }
         
      handleChange(event) {
        let input = this.state.input;
        input[event.target.name] = event.target.value;
      
        this.setState({
          input
        });
      }
         
      handleSubmit(event) {
        event.preventDefault();
      
        if(this.validate()){
            console.log(this.state);
      
            let input = {};
            input["password"] = "";
            input["confirm_password"] = "";
            this.setState({input:input});
      
            alert('Password Updated');
        }
      }
      
      validate(){
          let input = this.state.input;
          let errors = {};
          let isValid = true;

      

          
          if(typeof input["password"] !== "undefined"){
            var pattern = new RegExp(/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/)
            if(!pattern.test(input["password"])){
              isValid = false;
              errors["password"] = "Please enter valid password ."
            }
          }
    
    
          if (!input["password"]) {
            isValid = false;
            errors["password"] = "Please enter your password.";
          }
      
          if (!input["confirm_password"]) {
            isValid = false;
            errors["confirm_password"] = "Please enter your confirm password.";
          }
      
          if (typeof input["password"] !== "undefined") {
            if(input["password"].length < 6){
                isValid = false;
                errors["password"] = "Please add at least 6 charachter.";
            }
          }
      
          if (typeof input["password"] !== "undefined" && typeof input["confirm_password"] !== "undefined") {
              
            if (input["password"] !== input["confirm_password"]) {
              isValid = false;
              errors["password"] = "Passwords don't match.";
            }
          }
      
          this.setState({
            errors: errors
          });
      
          return isValid;
      }
         
    render(){
        return(
            <div className="Auth-form-container">
                <form className="Auth-form" onSubmit={this.handleSubmit} >
                    <div className="Auth-form-content">
                        <h3 className="Auth-form-title">Sign In</h3>
                        <div className="form-group mt-3">
                            <label>New Password</label>
                            <input
                            type="password"
                            name="password"
                            value={this.state.input.password}
                            onChange={this.handleChange}
                            className="form-control mt-1"
                            placeholder="Enter password"
                            id="password"
                            />
                            <div className="text-danger">{this.state.errors.password}</div>
                        </div>
                        <div className="form-group mt-3">
                            <label>Re-Enter Password</label>
                            <input
                            type="password"
                            name="confirm_password"
                            value={this.state.input.confirm_password}
                            onChange = {this.handleChange}
                            className="form-control mt-1"
                            placeholder="Enter password"
                            id="confirm_password"
                            />
                            <div className="text-danger">{this.state.errors.password}</div>
                        </div>
                        <div className="d-grid gap-2 mt-3">
                            <button type="submit" className="btn btn-primary">
                            Submit
                            </button>
                        </div>
                    </div>
                </form>
            </div>
                     
        );
    }
    
}
export default ResetPassword;